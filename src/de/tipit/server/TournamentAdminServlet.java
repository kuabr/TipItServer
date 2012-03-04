package de.tipit.server;

import javax.servlet.annotation.WebServlet;

import org.simpleframework.xml.Serializer;

import de.tipit.server.transfer.access.InvocationResult;
import de.tipit.server.transfer.access.TournamentAdmin;
import de.tipit.server.transfer.access.TournamentAdminInvocation;

/**
 * Special servlet implementation.
 */
@WebServlet("/" + TournamentAdminServlet.SESSION_NAME)
public class TournamentAdminServlet extends BaseServlet<TournamentAdmin> {

    private static final long serialVersionUID = 3455669574868446184L;

    public static final String SESSION_NAME = "TournamentAdmin";

    @Override
    protected String getSessionName() {
        return SESSION_NAME;
    }

    @Override
    protected InvocationResult executeData(final Serializer serializer, String data) throws Exception {
        return serializer.read(TournamentAdminInvocation.class, data).getTask().execute(manager);
    }
}
