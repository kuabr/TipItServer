package de.tipit.server.test;

import java.util.Date;

import de.tipit.server.model.i18n.error.IllegalAccess;
import de.tipit.server.model.i18n.error.IllegalArgument;
import de.tipit.server.model.i18n.error.NotYetImplemented;
import de.tipit.server.model.i18n.error.ObjectNotFound;
import de.tipit.server.model.i18n.error.PasswordIncorrect;
import de.tipit.server.model.i18n.error.RuleBookIsAlreadyFinalized;
import de.tipit.server.model.i18n.error.SessionNotValid;
import de.tipit.server.model.i18n.error.UnknownError;
import de.tipit.server.model.i18n.error.UpdateOfUserNotPossible;
import de.tipit.server.model.i18n.error.UserIsDisabled;
import de.tipit.server.model.i18n.error.UserNotFound;
import de.tipit.server.transfer.data.ContextTO;

public class TestExceptionMessages {

    public static void printExceptionMessages(ContextTO context) {
        System.out.println(new IllegalAccess(context));
        System.out.println(new IllegalArgument(context));
        System.out.println(new NotYetImplemented(context));
        System.out.println(new ObjectNotFound(context, String.class, 4711L));
        System.out.println(new PasswordIncorrect(context));
        System.out.println(new RuleBookIsAlreadyFinalized(context, new Date()));
        System.out.println(new SessionNotValid(context));
        System.out.println(new UnknownError(context));
        System.out.println(new UpdateOfUserNotPossible(context));
        System.out.println(new UserIsDisabled(context, "user", new Date()));
        System.out.println(new UserNotFound(context));
    }

    public static void main(String[] args) {
        ContextTO contextDE = new ContextTO();
        ContextTO contextEN = new ContextTO();

        contextDE.setLanguage(ContextTO.Language.DE);
        contextEN.setLanguage(ContextTO.Language.EN);

        System.out.println("\nDeutsche Messages:\n");
        printExceptionMessages(contextDE);

        System.out.println("\nEnglish messages:\n");
        printExceptionMessages(contextEN);
    }
}
