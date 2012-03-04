package de.tipit.server.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.tipit.server.model.work.DatabaseManager;
import de.tipit.server.transfer.access.GeneralError;
import de.tipit.server.transfer.data.BetCommunityDataArgumentTO;
import de.tipit.server.transfer.data.BetCommunityIdTO;
import de.tipit.server.transfer.data.ContextTO;
import de.tipit.server.transfer.data.LoginParameterTO;
import de.tipit.server.transfer.data.RuleBookDataTO;
import de.tipit.server.transfer.data.RuleBookIdTO;
import de.tipit.server.transfer.data.SessionTO;
import de.tipit.server.transfer.data.UserAccountTO;
import de.tipit.server.transfer.data.UserContactTO;
import de.tipit.server.transfer.data.UserDataTO;
import de.tipit.server.transfer.data.UserGroupDataTO;
import de.tipit.server.transfer.data.UserGroupIdTO;
import de.tipit.server.transfer.data.UserIdTO;

public class TestDatabaseManager {

    final private EntityManager emgr;

    final public DatabaseManager dbm;

    public TestDatabaseManager() {
        this.emgr = Persistence.createEntityManagerFactory("JPAManager").createEntityManager();
        this.dbm = DatabaseManager.createInstance(emgr);
    }

    public void printDatabase() {
        Query allObjects = emgr.createQuery("select o from java.lang.Object o");
        for (Object o : allObjects.getResultList()) {
            System.out.println(">>> " + o.toString());
        }
    }

    public EntityTransaction getTransaction() {
        return emgr.getTransaction();
    }

    @SuppressWarnings("deprecation")
    public UserIdTO createSimpleUser(ContextTO context, String userName) throws GeneralError {
        UserDataTO userData = new UserDataTO();
        UserAccountTO userAccount = new UserAccountTO();
        UserContactTO userContact = new UserContactTO();
        Date birthday = new Date(1970, 10, 25);

        userAccount.setUserName(userName);
        userAccount.setPassword("1234");

        userContact.setMailAddress("a.b@c.de");

        userData.setAccount(userAccount);
        userData.setContact(userContact);
        userData.setFullPreName("Hans");
        userData.setFullSurName("Fred");
        userData.setGender(UserDataTO.Gender.MALE);
        userData.setBirthday(birthday);

        return dbm.createUser(context, userData);
    }

    public UserGroupIdTO createTestUserGroup(ContextTO context, SessionTO session) throws GeneralError {
        UserGroupDataTO userGroupData = new UserGroupDataTO();

        userGroupData.setGroupName("Team AM");
        userGroupData.setDescription("bla bla bla ...");

        return dbm.createOrUpdateUserGroup(context, session, userGroupData);
    }

    public RuleBookIdTO createStandardRuleBook(ContextTO context, SessionTO session) throws GeneralError {
        RuleBookDataTO ruleBookData = new RuleBookDataTO();

        ruleBookData.setRuleBookName("Standard");
        ruleBookData.setDescription(null);
        ruleBookData.setPointsToAddForCorrectHomeResultInCaseOfCorrectTrend(1);
        ruleBookData.setPointsToAddForCorrectAwayResultInCaseOfCorrectTrend(1);
        ruleBookData.setPointsToAddForCorrectDifference(1);
        ruleBookData.setPointsToAddForCorrectTrend(3);
        ruleBookData.setPointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers(100);

        return dbm.createOrUpdateRuleBook(context, session, ruleBookData);
    }

    public BetCommunityIdTO createTestBetCommunity(ContextTO context, SessionTO session, RuleBookIdTO ruleBookId) throws GeneralError {
        BetCommunityDataArgumentTO betCommunityData = new BetCommunityDataArgumentTO();

        betCommunityData.setCommunityName("Champions EM Betters");
        betCommunityData.setDescription("AM-Tipp-Gemeinschaft");
        betCommunityData.setRuleBookId(ruleBookId);

        return dbm.createOrUpdateBetCommunity(context, session, betCommunityData);
    }

    public static void main(String[] args) throws GeneralError {
        TestDatabaseManager test = new TestDatabaseManager();

        // create context
        ContextTO context = new ContextTO();
        context.setLanguage(ContextTO.Language.EN);

        // print database content and start transaction
        System.out.println("PRINT DATABASE");
        test.printDatabase();
        EntityTransaction trans = test.getTransaction();
        trans.begin();

        // create user
        System.out.println("CREATE USER");
        UserIdTO newUserId = test.createSimpleUser(context, "test");
        System.out.println("User with ID " + newUserId.getTechId() + " created.");

        // login
        System.out.println("LOGIN");
        UserAccountTO account = new UserAccountTO();
        account.setUserName("test");
        account.setPassword("1234");
        LoginParameterTO loginParam = new LoginParameterTO();
        loginParam.setKillOldSessions(true);
        loginParam.setSessionDuration(LoginParameterTO.SessionDuration.MONTH);
        SessionTO session = test.dbm.doLogin(context, account, loginParam);
        System.out.println("SESSION: " + session);

        // renew session (only for testing)
        System.out.println("RENEW SESSION");
        session = test.dbm.renewSession(context, session);
        System.out.println("SESSION: " + session);

        // create user group
        UserGroupIdTO newUserGroupId = test.createTestUserGroup(context, session);
        System.out.println("UserGroup with ID " + newUserGroupId.getTechId() + " created.");

        // create standard rule book
        RuleBookIdTO newRuleBookId = test.createStandardRuleBook(context, session);
        System.out.println("RuleBook with ID " + newRuleBookId.getTechId() + " created.");

        // create bet community
        BetCommunityIdTO newBetCommunityId = test.createTestBetCommunity(context, session, null);
        System.out.println("BetCommunity with ID " + newBetCommunityId.getTechId() + " created.");

        // logout
        System.out.println("LOGOUT");
        test.dbm.doLogout(context, session);

        // stop transaction and print database content
        trans.commit();
        System.out.println("PRINT DATABASE");
        test.printDatabase();
    }
}
