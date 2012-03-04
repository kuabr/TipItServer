package de.tipit.server;

import javax.servlet.annotation.WebServlet;

import org.simpleframework.xml.Serializer;

import de.tipit.server.transfer.access.InvocationResult;
import de.tipit.server.transfer.access.UserSession;
import de.tipit.server.transfer.access.UserSessionInvocation;

/**
 * Special servlet implementation.
 */
@WebServlet("/" + UserSessionServlet.SESSION_NAME)
public class UserSessionServlet extends BaseServlet<UserSession> {

    private static final long serialVersionUID = -6328302085229470606L;

    public static final String SESSION_NAME = "UserSession";

    @Override
    protected String getSessionName() {
        return SESSION_NAME;
    }

    @Override
    protected InvocationResult executeData(final Serializer serializer, String data) throws Exception {
        return serializer.read(UserSessionInvocation.class, data).getTask().execute(manager);
    }
}
