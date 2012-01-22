package de.tipit.server.model.i18n.error;

import de.tipit.server.transfer.access.GeneralError;
import de.tipit.server.transfer.data.ContextTO;

public class SessionNotValid extends GeneralError {

    private static final long serialVersionUID = 126629413941408114L;

    private static final String messageDE = "Session ist nicht (mehr) gültig. Bitte neu einloggen!";

    private static final String messageEN = "Session is not valid (anymore). Please login again!";

    public SessionNotValid(ContextTO context) {
        super(context, messageDE, messageEN);
    }
}
