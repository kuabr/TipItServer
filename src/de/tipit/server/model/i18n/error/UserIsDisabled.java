package de.tipit.server.model.i18n.error;

import java.util.Date;

import de.tipit.server.model.i18n.DateFormatter;
import de.tipit.server.transfer.data.ContextTO;

public class UserIsDisabled extends BaseError {

    private static final long serialVersionUID = -2090736643797611718L;

    private static String calcMessageDE(String userName, Date disabledDate) {
        return "User '" + userName + "' kann seit dem " + DateFormatter.getDateDE(disabledDate) + " ab " + DateFormatter.getTimeDE(disabledDate)
                + " Uhr nicht mehr benutzt werden. Er ist gesperrt! Bitte an den Administrator wenden ...";
    }

    private static String calcMessageEN(String userName, Date disabledDate) {
        return "User '" + userName + "' cannot be used since " + DateFormatter.getDateEN(disabledDate) + " at " + DateFormatter.getTimeEN(disabledDate)
                + " oClock. He/she is locked! Please contact the administrator ...";
    }

    public UserIsDisabled(ContextTO context, String userName, Date disabledDate) {
        super(context, calcMessageDE(userName, disabledDate), calcMessageEN(userName, disabledDate));
    }
}
