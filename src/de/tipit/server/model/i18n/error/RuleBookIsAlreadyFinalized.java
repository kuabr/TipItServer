package de.tipit.server.model.i18n.error;

import java.util.Date;

import de.tipit.server.model.i18n.DateFormatter;
import de.tipit.server.transfer.access.GeneralError;
import de.tipit.server.transfer.data.ContextTO;

public class RuleBookIsAlreadyFinalized extends GeneralError {

    private static final long serialVersionUID = -2619953243887754842L;

    private static String calcMessageDE(Date finalizedDate) {
        return "Das Regelbuch kann nicht aktualisiert werden, da es bereits am " + DateFormatter.getDateDE(finalizedDate) + " finalisiert wurde!";
    }

    private static String calcMessageEN(Date finalizedDate) {
        return "The rule book cannot be updated, because it was already finalized at " + DateFormatter.getDateEN(finalizedDate) + "!";
    }

    public RuleBookIsAlreadyFinalized(ContextTO context, Date finalizedDate) {
        super(context, calcMessageDE(finalizedDate), calcMessageEN(finalizedDate));
    }
}
