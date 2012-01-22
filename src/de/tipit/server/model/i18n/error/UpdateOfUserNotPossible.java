package de.tipit.server.model.i18n.error;

import de.tipit.server.transfer.access.GeneralError;
import de.tipit.server.transfer.data.ContextTO;

public class UpdateOfUserNotPossible extends GeneralError {

    private static final long serialVersionUID = 5027326144140544798L;

    private static final String messageDE = "Nutzer kann nicht aktualisiert werden. Interner Fehler?";

    private static final String messageEN = "User cannot be updated. Internal error?";

    public UpdateOfUserNotPossible(ContextTO context) {
        super(context, messageDE, messageEN);
    }
}
