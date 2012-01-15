package de.tipit.server.model.i18n.error;

import de.tipit.server.transfer.data.ContextTO;

public class NotYetImplemented extends BaseError {

    private static final long serialVersionUID = -8977838918541312971L;

    private static final String messageDE = "Operation ist noch nicht implementiert.";

    private static final String messageEN = "Operation is not yet implemented.";

    public NotYetImplemented(ContextTO context) {
        super(context, messageDE, messageEN);
    }
}
