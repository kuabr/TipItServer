package de.tipit.server;

import javax.servlet.annotation.WebServlet;

import org.simpleframework.xml.Serializer;

import de.tipit.server.transfer.access.InvocationResult;
import de.tipit.server.transfer.access.RulesAdmin;
import de.tipit.server.transfer.access.RulesAdminInvocation;

/**
 * Special servlet implementation.
 */
@WebServlet("/" + RulesAdminServlet.SESSION_NAME)
public class RulesAdminServlet extends BaseServlet<RulesAdmin> {

    private static final long serialVersionUID = 6889207611458134532L;

    public static final String SESSION_NAME = "RulesAdmin";

    @Override
    protected String getSessionName() {
        return SESSION_NAME;
    }

    @Override
    protected InvocationResult executeData(final Serializer serializer, String data) throws Exception {
        return serializer.read(RulesAdminInvocation.class, data).getTask().execute(manager);
    }
}
