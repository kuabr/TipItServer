package de.tipit.server;

import javax.servlet.annotation.WebServlet;

import org.simpleframework.xml.Serializer;

import de.tipit.server.transfer.access.Analysis;
import de.tipit.server.transfer.access.AnalysisInvocation;
import de.tipit.server.transfer.access.InvocationResult;

/**
 * Special servlet implementation.
 */
@WebServlet("/" + AnalysisServlet.SESSION_NAME)
public class AnalysisServlet extends BaseServlet<Analysis> {

    private static final long serialVersionUID = 1437893710925700326L;

    public static final String SESSION_NAME = "Analysis";

    @Override
    protected String getSessionName() {
        return SESSION_NAME;
    }

    @Override
    protected InvocationResult executeData(final Serializer serializer, String data) throws Exception {
        return serializer.read(AnalysisInvocation.class, data).getTask().execute(manager);
    }
}
