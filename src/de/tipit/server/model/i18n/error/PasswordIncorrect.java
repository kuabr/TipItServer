package de.tipit.server.model.i18n.error;

import de.tipit.server.transfer.data.ContextTO;

public class PasswordIncorrect extends BaseError {

    private static final long serialVersionUID = -6857832366168457852L;

    private static final String messageDE = "Password ist nicht korrekt. Bitte erneut versuchen oder neues Passwort anfordern!";

    private static final String messageEN = "Password is incorrect. Please try again or request a new password!";

    public PasswordIncorrect(ContextTO context) {
        super(context, messageDE, messageEN);
    }
}
