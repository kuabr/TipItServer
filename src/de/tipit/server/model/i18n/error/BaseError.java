package de.tipit.server.model.i18n.error;

import java.rmi.RemoteException;

import de.tipit.server.transfer.data.ContextTO;

public class BaseError extends RemoteException {

    private static final long serialVersionUID = 2003082596724436133L;

    private static String calcMessage(ContextTO context, String messageDE, String messageEN) {
        if (context != null) {
            if (context.getLanguage() == ContextTO.Language.DE) {
                return messageDE;
            }

            if (context.getLanguage() == ContextTO.Language.EN) {
                return messageEN;
            }
        }

        return "";
    }

    public BaseError(ContextTO context, String messageDE, String messageEN) {
        super(calcMessage(context, messageDE, messageEN));
    }
}
