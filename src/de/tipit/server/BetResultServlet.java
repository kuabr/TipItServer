package de.tipit.server;

import javax.servlet.annotation.WebServlet;

import org.simpleframework.xml.Serializer;

import de.tipit.server.transfer.access.BetResult;
import de.tipit.server.transfer.access.BetResultInvocation;
import de.tipit.server.transfer.access.InvocationResult;

/**
 * Special servlet implementation.
 */
@WebServlet("/" + BetResultServlet.SESSION_NAME)
public class BetResultServlet extends BaseServlet<BetResult> {

    private static final long serialVersionUID = -4998794779210288421L;

    public static final String SESSION_NAME = "BetResult";

    @Override
    protected String getSessionName() {
        return SESSION_NAME;
    }

    @Override
    protected InvocationResult executeData(final Serializer serializer, String data) throws Exception {
        return serializer.read(BetResultInvocation.class, data).getTask().execute(manager);
    }
}
