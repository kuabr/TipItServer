package de.tipit.server;

import javax.servlet.annotation.WebServlet;

import org.simpleframework.xml.Serializer;

import de.tipit.server.transfer.access.InvocationResult;
import de.tipit.server.transfer.access.MetaDataAdmin;
import de.tipit.server.transfer.access.MetaDataAdminInvocation;

/**
 * Special servlet implementation.
 */
@WebServlet("/" + MetaDataAdminServlet.SESSION_NAME)
public class MetaDataAdminServlet extends BaseServlet<MetaDataAdmin> {

    private static final long serialVersionUID = 6309333876429317410L;

    public static final String SESSION_NAME = "MetaDataAdmin";

    @Override
    protected String getSessionName() {
        return SESSION_NAME;
    }

    @Override
    protected InvocationResult executeData(final Serializer serializer, String data) throws Exception {
        return serializer.read(MetaDataAdminInvocation.class, data).getTask().execute(manager);
    }
}
