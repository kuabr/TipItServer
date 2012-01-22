package de.tipit.server.model.i18n.error;

import de.tipit.server.transfer.access.GeneralError;
import de.tipit.server.transfer.data.ContextTO;

public class IllegalArgument extends GeneralError {

    private static final long serialVersionUID = 3333047031287149006L;

    private static final String messageDE = "Falsches oder unerlaubtes Argument übergeben ...";

    private static final String messageEN = "Illegal or inappropriate argument given ...";

    public IllegalArgument(ContextTO context) {
        super(context, messageDE, messageEN);
    }
}
