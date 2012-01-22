package de.tipit.server.model.i18n.error;

import de.tipit.server.transfer.access.GeneralError;
import de.tipit.server.transfer.data.ContextTO;

public class ObjectNotFound extends GeneralError {

    private static final long serialVersionUID = 1348237665719684156L;

    private static String calcMessageDE(Class<?> objectClass, Long id) {
        return "Objekt '" + objectClass.getName() + "' mit ID " + id + " nicht gefunden.";
    }

    private static String calcMessageEN(Class<?> objectClass, Long id) {
        return "Object '" + objectClass.getName() + "' with ID " + id + " not found.";
    }

    public ObjectNotFound(ContextTO context, Class<?> objectClass, Long id) {
        super(context, calcMessageDE(objectClass, id), calcMessageEN(objectClass, id));
    }
}
