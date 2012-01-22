package de.tipit.server.model.i18n.error;

import de.tipit.server.transfer.access.GeneralError;
import de.tipit.server.transfer.data.ContextTO;

public class IllegalAccess extends GeneralError {

    private static final long serialVersionUID = -6040856591048917672L;

    private static final String messageDE = "Kein Zugriff auf die Definition einer spezifizierten Klasse, Feld, Methode oder Konstruktor ...";

    private static final String messageEN = "No access to the definition of a specified class, field, method or constructor ...";

    public IllegalAccess(ContextTO context) {
        super(context, messageDE, messageEN);
    }
}
