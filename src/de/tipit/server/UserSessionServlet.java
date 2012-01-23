package de.tipit.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import de.tipit.server.model.work.DatabaseManager;
import de.tipit.server.model.work.TransactionProxy;
import de.tipit.server.transfer.access.InvocationResult;
import de.tipit.server.transfer.access.UserSession;
import de.tipit.server.transfer.access.UserSessionInvocation;

/**
 * Servlet implementation class UserSessionServlet
 */
@WebServlet("/UserSession")
public class UserSessionServlet extends HttpServlet {

    private static final long serialVersionUID = 3974017724843313744L;

    private static final String DATA_PARAMETER_NAME = "data";

    public static final String SESSION_NAME = "UserSession";

    private final UserSession manager;

    public UserSessionServlet() {
        EntityManager em = Persistence.createEntityManagerFactory("JPAManager").createEntityManager();
        this.manager = (UserSession) TransactionProxy.newInstance(new DatabaseManager(em), em);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // return starting message
        PrintWriter out = response.getWriter();
        String head = "<head><title>TipIt-Service for " + SESSION_NAME + "-Handling</title></head>";
        String body = "<body><b>" + SESSION_NAME + "-Service is running ...</b></body>";
        String html = "<html>" + head + body + "</html>";
        out.println(html);
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newLine = System.getProperty("line.separator");

        // read data from request
        Map<String, String[]> paramDict = request.getParameterMap();
        StringBuilder data = new StringBuilder();
        if (paramDict.containsKey(DATA_PARAMETER_NAME)) {
            String[] dataArray = paramDict.get(DATA_PARAMETER_NAME);
            for (int i = 0; i < dataArray.length; i++) {
                String nextLine = dataArray[i];
                if (nextLine != null) {
                    if (i > 0) {
                        data.append(newLine);
                    }
                    data.append(nextLine);
                }
            }
        }
        String text = data.toString();
        System.out.println(text);

        // create invocation object and execute it
        Serializer serializer = new Persister();
        UserSessionInvocation invocation;
        try {
            invocation = serializer.read(UserSessionInvocation.class, text);
        } catch (Exception e) {
            e.printStackTrace();
            return; // TODO
        }
        InvocationResult result = invocation.getTask().execute(manager);
        ByteArrayOutputStream resultOut = new ByteArrayOutputStream();
        try {
            serializer.write(result, resultOut);
        } catch (Exception e) {
            e.printStackTrace();
            return; // TODO
        }
        String resultText = resultOut.toString("UTF-8");

        // add result in response
        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        out.println(resultText);
        out.close();
    }
}
