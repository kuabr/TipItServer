package de.tipit.server.model.i18n.error;

import de.tipit.server.transfer.data.ContextTO;

public class SessionNotValid extends BaseError {

    private static final long serialVersionUID = -545614762980209753L;

    private static final String messageDE = "Session ist nicht (mehr) gültig. Bitte neu einloggen!";

    private static final String messageEN = "Session is not valid (anymore). Please login again!";

    public SessionNotValid(ContextTO context) {
        super(context, messageDE, messageEN);
    }
}
