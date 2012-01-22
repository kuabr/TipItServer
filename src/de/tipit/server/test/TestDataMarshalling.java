package de.tipit.server.test;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import de.tipit.server.transfer.data.SessionTO;
import de.tipit.server.transfer.data.UserAccountTO;
import de.tipit.server.transfer.data.UserContactTO;
import de.tipit.server.transfer.data.UserDataTO;

public class TestDataMarshalling {

    public static void main(String[] args) throws Exception {
        UserDataTO userData = new UserDataTO();
        UserAccountTO userAccount = new UserAccountTO();
        UserContactTO userContact = new UserContactTO();

        userAccount.setUserName("test");
        userAccount.setPassword("1234");
        userContact.setMailAddress("b.jause@t-online.de");

        userData.setTechId(4711L);
        userData.setAccount(userAccount);
        userData.setContact(userContact);
        userData.setFullPreName("Hans");
        userData.setFullSurName("Fred");
        userData.setGender(UserDataTO.Gender.MALE);
        userData.setBirthday(new Date());

        Serializer serializer = new Persister();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        serializer.write(userData, out);
        System.out.println(out.toString("UTF-8"));

        System.out.println();
        out = new ByteArrayOutputStream(); // create new object

        SessionTO session = new SessionTO();
        session.setSessionId("adj&lt;kl&jklsfda\"<>jdljksdlf%");
        session.setUserName("JustMe");
        session.setIsAdmin(true);
        session.setIsGuest(false);

        serializer.write(session, out);
        String sessionText = out.toString("UTF-8");
        System.out.println(sessionText);

        SessionTO newSession = serializer.read(SessionTO.class, sessionText);
        System.out.println("\nSESSION-ID: " + newSession.getSessionId());
    }
}
