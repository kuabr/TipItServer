package de.tipit.server.model.i18n.error;

import de.tipit.server.transfer.access.GeneralError;
import de.tipit.server.transfer.data.ContextTO;

public class NotYetImplemented extends GeneralError {

    private static final long serialVersionUID = -5600028333916606658L;

    private static final String messageDE = "Operation ist noch nicht implementiert.";

    private static final String messageEN = "Operation is not yet implemented.";

    public NotYetImplemented(ContextTO context) {
        super(context, messageDE, messageEN);
    }
}
