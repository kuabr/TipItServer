package de.tipit.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import de.tipit.server.model.work.DatabaseManager;
import de.tipit.server.model.work.TransactionProxy;
import de.tipit.server.transfer.access.InvocationResult;

/**
 * Base servlet implementation.
 */
@SuppressWarnings("serial")
public abstract class BaseServlet<SESSION_T> extends HttpServlet {

    protected static final String DATA_PARAMETER_NAME = "data";

    protected abstract String getSessionName();

    protected abstract InvocationResult executeData(final Serializer serializer, String data) throws Exception;

    protected final SESSION_T manager;

    @SuppressWarnings("unchecked")
    public BaseServlet() {
        EntityManager em = Persistence.createEntityManagerFactory("JPAManager").createEntityManager();
        this.manager = (SESSION_T) TransactionProxy.newInstance(DatabaseManager.createInstance(em), em);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // return starting message
        PrintWriter out = response.getWriter();
        String head = "<head><title>TipIt-Service for " + this.getSessionName() + "-Handling</title></head>";
        String body = "<body><b>" + this.getSessionName() + "-Service is running ...</b></body>";
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
        InvocationResult result;
        try {
            result = this.executeData(serializer, text);
        } catch (Throwable exc) {
            exc.printStackTrace(); // for debugging
            result = new InvocationResult(exc);
        }
        ByteArrayOutputStream resultOut = new ByteArrayOutputStream();
        try {
            serializer.write(result, resultOut);
        } catch (Throwable exc) {
            exc.printStackTrace(); // for debugging
            return; // leave method due to error
        }
        String resultText = resultOut.toString("UTF-8");

        // add result in response
        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        out.println(resultText);
        out.close();
    }
}
