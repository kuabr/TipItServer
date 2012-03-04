package de.tipit.server;

import javax.servlet.annotation.WebServlet;

import org.simpleframework.xml.Serializer;

import de.tipit.server.transfer.access.CommunityAdmin;
import de.tipit.server.transfer.access.CommunityAdminInvocation;
import de.tipit.server.transfer.access.InvocationResult;

/**
 * Special servlet implementation.
 */
@WebServlet("/" + CommunityAdminServlet.SESSION_NAME)
public class CommunityAdminServlet extends BaseServlet<CommunityAdmin> {

    private static final long serialVersionUID = -600730790411812579L;

    public static final String SESSION_NAME = "CommunityAdmin";

    @Override
    protected String getSessionName() {
        return SESSION_NAME;
    }

    @Override
    protected InvocationResult executeData(final Serializer serializer, String data) throws Exception {
        return serializer.read(CommunityAdminInvocation.class, data).getTask().execute(manager);
    }
}
