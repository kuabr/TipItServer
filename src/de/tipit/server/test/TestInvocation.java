package de.tipit.server.test;

import java.io.ByteArrayOutputStream;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import de.tipit.server.model.work.DatabaseManager;
import de.tipit.server.model.work.TransactionProxy;
import de.tipit.server.transfer.access.InvocationResult;
import de.tipit.server.transfer.access.UserSession;
import de.tipit.server.transfer.access.UserSessionInvocation;
import de.tipit.server.transfer.access.bet_result.DelWinnerTeam;
import de.tipit.server.transfer.access.user_session.CreateUser;
import de.tipit.server.transfer.data.ContextTO;
import de.tipit.server.transfer.data.UserAccountTO;
import de.tipit.server.transfer.data.UserContactTO;
import de.tipit.server.transfer.data.UserDataTO;

public class TestInvocation {

    public static void main(String[] args) throws Exception {
        EntityManager emgr = Persistence.createEntityManagerFactory("JPAManager").createEntityManager();
        UserSession dbm = (UserSession) TransactionProxy.newInstance(new DatabaseManager(emgr), emgr);
        Serializer serializer = new Persister();

        ContextTO context = new ContextTO();
        context.setLanguage(ContextTO.Language.DE);

        UserAccountTO account = new UserAccountTO();
        account.setUserName("jupp");
        account.setPassword("1234");

        UserContactTO contact = new UserContactTO();
        contact.setMailAddress("a.b@t.de");

        UserDataTO userData = new UserDataTO();
        userData.setAccount(account);
        userData.setContact(contact);
        userData.setFullPreName("Hans");
        userData.setFullSurName("Fred");
        userData.setGender(UserDataTO.Gender.MALE);

        CreateUser createUser = new CreateUser();
        createUser.setContext(context);
        createUser.setUserData(userData);

        UserSessionInvocation invocation = new UserSessionInvocation(createUser);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        serializer.write(invocation, out);
        String sendData = out.toString("UTF-8");
        System.out.println(sendData);
        System.out.println();

        UserSessionInvocation recInv = serializer.read(UserSessionInvocation.class, sendData);
        InvocationResult result = recInv.getTask().execute(dbm);
        ByteArrayOutputStream out2 = new ByteArrayOutputStream();

        serializer.write(result, out2);
        String sendData2 = out2.toString("UTF-8");
        System.out.println();
        System.out.println(sendData2);
        System.out.println();

        InvocationResult result2 = serializer.read(InvocationResult.class, sendData2);
        if (result2.getError() != null) {
            System.out.println("ERROR: " + result2.getError().getDisplayMessage());
        } else {
            CreateUser.Result data = (CreateUser.Result) result2.getData();
            System.out.println("User-ID: " + data.getUserId().getTechId());
        }

        // check serialization of "no result"
        InvocationResult result3 = new InvocationResult(new DelWinnerTeam.Result());
        System.out.println();
        serializer.write(result3, System.out);
    }
}
