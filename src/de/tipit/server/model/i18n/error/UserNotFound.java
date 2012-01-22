package de.tipit.server.model.i18n.error;

import de.tipit.server.transfer.access.GeneralError;
import de.tipit.server.transfer.data.ContextTO;

public class UserNotFound extends GeneralError {

    private static final long serialVersionUID = 6733834834791347971L;

    private static final String messageDE = "Nutzer nicht gefunden. Ist dieser Nutzer registriert?";

    private static final String messageEN = "User not found. Are you registered?";

    public UserNotFound(ContextTO context) {
        super(context, messageDE, messageEN);
    }
}
