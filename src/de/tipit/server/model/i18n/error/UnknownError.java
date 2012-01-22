package de.tipit.server.model.i18n.error;

import de.tipit.server.transfer.access.GeneralError;
import de.tipit.server.transfer.data.ContextTO;

public class UnknownError extends GeneralError {

    private static final long serialVersionUID = -6474347925387008666L;

    private static final String messageDE = "Unbekannter Fehler ...";

    private static final String messageEN = "Unknown error ...";

    public UnknownError(ContextTO context) {
        super(context, messageDE, messageEN);
    }
}
