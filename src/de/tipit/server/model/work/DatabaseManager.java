package de.tipit.server.model.work;

import java.rmi.RemoteException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.tipit.server.model.entity.BetCommunityEO;
import de.tipit.server.model.entity.RuleBookEO;
import de.tipit.server.model.entity.SessionEO;
import de.tipit.server.model.entity.UserEO;
import de.tipit.server.model.entity.UserGroupEO;
import de.tipit.server.model.i18n.Translator;
import de.tipit.server.model.i18n.error.NotYetImplemented;
import de.tipit.server.model.i18n.error.ObjectNotFound;
import de.tipit.server.model.i18n.error.PasswordIncorrect;
import de.tipit.server.model.i18n.error.RuleBookIsAlreadyFinalized;
import de.tipit.server.model.i18n.error.UpdateOfUserNotPossible;
import de.tipit.server.model.i18n.error.UserNotFound;
import de.tipit.server.transfer.access.Analysis;
import de.tipit.server.transfer.access.BetResult;
import de.tipit.server.transfer.access.CommunityAdmin;
import de.tipit.server.transfer.access.MetaDataAdmin;
import de.tipit.server.transfer.access.RulesAdmin;
import de.tipit.server.transfer.access.TournamentAdmin;
import de.tipit.server.transfer.access.UserSession;
import de.tipit.server.transfer.data.BetCommunityDataArgumentTO;
import de.tipit.server.transfer.data.BetCommunityIdTO;
import de.tipit.server.transfer.data.BetCommunityNameTO;
import de.tipit.server.transfer.data.BetCommunitySearchDataTO;
import de.tipit.server.transfer.data.BetCommunityTO;
import de.tipit.server.transfer.data.CommentDataTO;
import de.tipit.server.transfer.data.CommentIdTO;
import de.tipit.server.transfer.data.ContextTO;
import de.tipit.server.transfer.data.GameBetDataWithoutGameTO;
import de.tipit.server.transfer.data.GameBetIdTO;
import de.tipit.server.transfer.data.GameDataArgumentTO;
import de.tipit.server.transfer.data.GameDataResultTO;
import de.tipit.server.transfer.data.GameIdTO;
import de.tipit.server.transfer.data.GameResultDataTO;
import de.tipit.server.transfer.data.GameTO;
import de.tipit.server.transfer.data.GameWithPointsTO;
import de.tipit.server.transfer.data.LoginParameterTO;
import de.tipit.server.transfer.data.MatchDayDataArgumentTO;
import de.tipit.server.transfer.data.MatchDayIdTO;
import de.tipit.server.transfer.data.PeriodTO;
import de.tipit.server.transfer.data.RuleBookDataTO;
import de.tipit.server.transfer.data.RuleBookIdTO;
import de.tipit.server.transfer.data.RuleBookNameTO;
import de.tipit.server.transfer.data.RuleBookSearchDataTO;
import de.tipit.server.transfer.data.RuleBookTO;
import de.tipit.server.transfer.data.SessionIdTO;
import de.tipit.server.transfer.data.SessionTO;
import de.tipit.server.transfer.data.SportDataTO;
import de.tipit.server.transfer.data.SportIdTO;
import de.tipit.server.transfer.data.SportNameTO;
import de.tipit.server.transfer.data.SportTO;
import de.tipit.server.transfer.data.TeamDataTO;
import de.tipit.server.transfer.data.TeamIdTO;
import de.tipit.server.transfer.data.TeamNameTO;
import de.tipit.server.transfer.data.TeamSearchDataTO;
import de.tipit.server.transfer.data.TeamTO;
import de.tipit.server.transfer.data.TournamentDataArgumentTO;
import de.tipit.server.transfer.data.TournamentDescrTO;
import de.tipit.server.transfer.data.TournamentIdTO;
import de.tipit.server.transfer.data.TournamentRoundDataArgumentTO;
import de.tipit.server.transfer.data.TournamentRoundIdTO;
import de.tipit.server.transfer.data.TournamentSearchDataTO;
import de.tipit.server.transfer.data.TournamentTO;
import de.tipit.server.transfer.data.TournamentTypeDataArgumentTO;
import de.tipit.server.transfer.data.TournamentTypeIdTO;
import de.tipit.server.transfer.data.TournamentTypeNameTO;
import de.tipit.server.transfer.data.TournamentTypeSearchDataTO;
import de.tipit.server.transfer.data.TournamentTypeTO;
import de.tipit.server.transfer.data.UserAccountTO;
import de.tipit.server.transfer.data.UserContactTO;
import de.tipit.server.transfer.data.UserDataTO;
import de.tipit.server.transfer.data.UserGroupDataTO;
import de.tipit.server.transfer.data.UserGroupIdTO;
import de.tipit.server.transfer.data.UserGroupNameTO;
import de.tipit.server.transfer.data.UserGroupPointsTO;
import de.tipit.server.transfer.data.UserGroupSearchDataTO;
import de.tipit.server.transfer.data.UserGroupTO;
import de.tipit.server.transfer.data.UserIdTO;
import de.tipit.server.transfer.data.UserNameTO;
import de.tipit.server.transfer.data.UserPointsTO;
import de.tipit.server.transfer.data.UserSearchDataTO;
import de.tipit.server.transfer.data.UserTO;
import de.tipit.server.transfer.data.WinnerBetDataArgumentTO;
import de.tipit.server.transfer.data.WinnerBetIdTO;
import de.tipit.server.transfer.data.WinnerBetTO;
import de.tipit.server.utils.AdjustableDate;
import de.tipit.server.utils.ComparableByteArray;

public class DatabaseManager implements Analysis, BetResult, CommunityAdmin, MetaDataAdmin, RulesAdmin, TournamentAdmin, UserSession {

    private final EntityManager entityManager;

    private final SessionManager sessionManager;

    private final PermissionVerifier permVerifier;

    private final DataVerifier dataVerifier;

    private final Translator trans;

    public DatabaseManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.sessionManager = new SessionManager(entityManager);
        this.permVerifier = new PermissionVerifier(entityManager);
        this.dataVerifier = new DataVerifier(entityManager);
        this.trans = new Translator();
    }

    public long getCount(Class<?> eoClass) {
        return (Long) (entityManager.createQuery("select count(o) from " + eoClass.getName() + " o").getSingleResult());
    }

    @Override
    public SessionTO renewSession(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        // save current date and time
        Date currentDate = new Date();

        // read session
        SessionEO session = sessionManager.getSession(context, sessionId, currentDate);
        UserEO user = session.getUser();

        // check permissions
        permVerifier.checkGeneralPermission(context, user);

        // create new session
        SessionEO newSession = sessionManager.replaceSession(context, session, currentDate);

        // return session
        if (newSession != null) {
            SessionTO result = new SessionTO();

            result.setSessionId(newSession.getSessionId());
            result.setUserName(user.getUserName());
            result.setIsAdmin(user.getAdmin());
            result.setIsGuest(user.getGuest());

            return result;
        } else {
            return null;
        }
    }

    @Override
    public SessionTO doLogin(ContextTO context, UserAccountTO userAccount, LoginParameterTO loginParameter) throws RemoteException {
        // check data
        if (userAccount == null) {
            throw new UserNotFound(context);
        }

        // save current date and time
        Date currentDate = new Date();

        // check permissions
        permVerifier.checkGeneralPermission(context);

        // search for user
        UserEO user = null;
        String userName = userAccount.getUserName();
        if (userName != null) {
            Query userQuery = entityManager.createQuery("select user from " + UserEO.class.getName() + " user where user.userName = :name");
            for (Object result : userQuery.setParameter("name", userName).getResultList()) {
                if (result != null) {
                    ComparableByteArray password = new ComparableByteArray(userAccount.getEncryptedPassword());
                    UserEO foundUser = (UserEO) result;

                    if (password.equals(new ComparableByteArray(foundUser.getEncryptedPasswd()))) {
                        user = foundUser;
                        break;
                    } else {
                        throw new PasswordIncorrect(context);
                    }
                }
            }
        }
        if (user == null) {
            throw new UserNotFound(context);
        }

        // "calculate" expiration date
        AdjustableDate expirationDate = null;
        if (loginParameter != null) {
            LoginParameterTO.SessionDuration sd = loginParameter.getSessionDuration();
            if (sd != null) {
                if (!sd.equals(LoginParameterTO.SessionDuration.INFINITE)) {
                    expirationDate = new AdjustableDate(currentDate);
                    expirationDate.add(sd);
                }
            } else {
                expirationDate = new AdjustableDate(currentDate);
                expirationDate.addDay();
            }
        } else {
            expirationDate = new AdjustableDate(currentDate);
            expirationDate.addHour();
        }

        // kill old sessions?
        if (loginParameter != null && loginParameter.getKillOldSessions() != null && loginParameter.getKillOldSessions()) {
            if (user.getSessions() == null) {
                entityManager.refresh(user);
            }
            for (SessionEO oldSession : user.getSessions()) {
                sessionManager.destroySession(context, oldSession, currentDate);
            }
            entityManager.flush();
        }

        // create new session
        SessionEO newSession = sessionManager.createSession(context, user, expirationDate, currentDate);

        // return session
        if (newSession != null) {
            SessionTO result = new SessionTO();

            result.setSessionId(newSession.getSessionId());
            result.setUserName(user.getUserName());
            result.setIsAdmin(user.getAdmin());
            result.setIsGuest(user.getGuest());

            return result;
        } else {
            return null;
        }
    }

    @Override
    public SessionTO doLoginAsGuest(ContextTO context) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void doLogout(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        // save current date and time
        Date currentDate = new Date();

        // read session
        SessionEO session = sessionManager.getSession(context, sessionId, currentDate);
        UserEO user = session.getUser();

        // check permissions
        permVerifier.checkGeneralPermission(context, user);

        // destroy session
        sessionManager.destroySession(context, session, currentDate);
    }

    @Override
    public UserIdTO createUser(ContextTO context, UserDataTO userData) throws RemoteException {
        // check data
        dataVerifier.checkData(context, userData);

        // check "valid" userId
        if (userData.getTechId() != null) {
            throw new UpdateOfUserNotPossible(context);
        }

        // check permissions
        permVerifier.checkGeneralPermission(context);

        // create new user data
        UserEO newUser = new UserEO();
        UserAccountTO userAccount = userData.getAccount();
        UserContactTO userContact = userData.getContact();
        if (userAccount != null) {
            newUser.setUserName(userAccount.getUserName());
            newUser.setEncryptedPasswd(userAccount.getEncryptedPassword());
        }
        if (userContact != null) {
            newUser.setMailAddress(userContact.getMailAddress());
        }
        newUser.setFullPreName(userData.getFullPreName());
        newUser.setFullSurName(userData.getFullSurName());
        newUser.setGender(userData.getGender());
        newUser.setBirthday(userData.getBirthday());
        newUser.setAdmin(getCount(UserEO.class) == 0L);
        newUser.setGuest(false);
        newUser.setCreation(new Date());

        // add new user
        entityManager.persist(newUser);
        entityManager.flush();
        entityManager.refresh(newUser);

        // return new userId
        Long newUserId = newUser.getId();
        if (newUserId != null) {
            UserIdTO result = new UserIdTO();
            result.setTechId(newUserId);
            return result;
        } else {
            return null;
        }
    }

    @Override
    public void updateUser(ContextTO context, SessionIdTO sessionId, UserDataTO userData) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void resetPassword(ContextTO context, UserContactTO userContact) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void setInactive(ContextTO context, SessionIdTO sessionId, Boolean isInactive) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void setDisabled(ContextTO context, SessionIdTO sessionId, UserIdTO userId, Boolean isDisabled) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserTO readOwnUser(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserTO readUser(ContextTO context, SessionIdTO sessionId, UserIdTO userId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TournamentDescrTO[] getModeratedTournaments(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TournamentDescrTO[] getOwnTournaments(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserNameTO[] findUsers(ContextTO context, SessionIdTO sessionId, UserSearchDataTO userSearchData) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TournamentIdTO createOrUpdateTournament(ContextTO context, SessionIdTO sessionId, TournamentDataArgumentTO tournData) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void deleteTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public Boolean isTournamentModerator(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void addModeratorToTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId, UserIdTO modId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void removeModeratorFromTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId, UserIdTO modId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TournamentRoundIdTO createOrUpdateTournamentRound(ContextTO context, SessionIdTO sessionId, TournamentRoundDataArgumentTO tournRoundData)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void deleteTournamentRound(ContextTO context, SessionIdTO sessionId, TournamentRoundIdTO tournRoundId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public MatchDayIdTO createOrUpdateMatchDay(ContextTO context, SessionIdTO sessionId, MatchDayDataArgumentTO matchDayData) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void deleteMatchDay(ContextTO context, SessionIdTO sessionId, MatchDayIdTO matchDayId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameIdTO createOrUpdateGame(ContextTO context, SessionIdTO sessionId, GameDataArgumentTO gameData) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void deleteGame(ContextTO context, SessionIdTO sessionId, GameIdTO gameId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public RuleBookNameTO[] findRuleBooks(ContextTO context, SessionIdTO sessionId, RuleBookSearchDataTO ruleBookSearchData) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public RuleBookNameTO[] getNotFinalRuleBooks(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public RuleBookNameTO[] getOwnRuleBooks(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public Integer calculatePointsForStoredRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookIdTO ruleBookId, Integer homeResult, Integer awayResult,
            Integer averageHomeResultBet, Integer averageAwayResultBet, Integer homeResultBet, Integer awayResultBet) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public Integer calculatePointsForNewRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookDataTO ruleBookData, Integer homeResult, Integer awayResult,
            Integer averageHomeResultBet, Integer averageAwayResultBet, Integer homeResultBet, Integer awayResultBet) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public RuleBookIdTO createOrUpdateRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookDataTO ruleBookData) throws RemoteException {
        // check data
        dataVerifier.checkData(context, ruleBookData);

        // save current date and time
        Date currentDate = new Date();

        // read session
        SessionEO session = sessionManager.getSession(context, sessionId, currentDate);
        UserEO user = session.getUser();

        // check permissions and read rule book (or create new one)
        // check also, if the rule book is already finalized
        RuleBookEO newRuleBook;
        Long existingRuleBookId = ruleBookData.getTechId();
        if (existingRuleBookId != null) {
            newRuleBook = entityManager.find(RuleBookEO.class, existingRuleBookId);
            if (newRuleBook == null) {
                throw new ObjectNotFound(context, RuleBookEO.class, existingRuleBookId);
            }
            permVerifier.checkRuleBookPermission(context, user, newRuleBook);

            // check, if the rule book is already finalized
            if (newRuleBook != null) {
                Date finalizedDate = newRuleBook.getFinalized();
                if (finalizedDate != null) {
                    throw new RuleBookIsAlreadyFinalized(context, finalizedDate);
                }
            }
        } else {
            permVerifier.checkUserGroupPermission(context, user);
            newRuleBook = new RuleBookEO();

            // assign creation data for new rule book
            newRuleBook.setCreator(user);
            newRuleBook.setCreated(currentDate);
            newRuleBook.setFinalized(null);
            newRuleBook.setDeleted(null);
        }

        // assign new rule book data
        newRuleBook.setRuleBookName(trans.translateName(context, ruleBookData.getRuleBookName()));
        newRuleBook.setDescription(trans.translateDescription(context, ruleBookData.getDescription()));
        newRuleBook.setPointsToAddForCorrectHomeResult(ruleBookData.getPointsToAddForCorrectHomeResult());
        newRuleBook.setPointsToAddForCorrectAwayResult(ruleBookData.getPointsToAddForCorrectAwayResult());
        newRuleBook.setPointsToAddForCorrectHomeResultInCaseOfCorrectTrend(ruleBookData.getPointsToAddForCorrectHomeResultInCaseOfCorrectTrend());
        newRuleBook.setPointsToAddForCorrectAwayResultInCaseOfCorrectTrend(ruleBookData.getPointsToAddForCorrectAwayResultInCaseOfCorrectTrend());
        newRuleBook.setPointsToAddForCorrectResult(ruleBookData.getPointsToAddForCorrectResult());
        newRuleBook.setPointsToAddForCorrectDifference(ruleBookData.getPointsToAddForCorrectDifference());
        newRuleBook.setPointsToAddForCorrectTrend(ruleBookData.getPointsToAddForCorrectTrend());
        newRuleBook.setPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult(ruleBookData
                .getPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult());
        newRuleBook.setPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult(ruleBookData
                .getPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult());
        newRuleBook.setPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference(ruleBookData
                .getPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference());
        newRuleBook.setPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend(ruleBookData.getPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend());
        newRuleBook.setRoundMultiplierForFirstRound(ruleBookData.getRoundMultiplierForFirstRound());
        newRuleBook.setRoundMultiplierAdderPerRound(ruleBookData.getRoundMultiplierAdderPerRound());
        newRuleBook.setRoundMultiplierMultiplierPerRound(ruleBookData.getRoundMultiplierMultiplierPerRound());
        newRuleBook.setPointsPerUserToDistribute(ruleBookData.getPointsPerUserToDistribute());
        newRuleBook.setPointsForCorrectWinnerBet(ruleBookData.getPointsForCorrectWinnerBet());
        newRuleBook.setPointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers(ruleBookData
                .getPointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers());
        newRuleBook.setPointsForCorrectWinnerBetPer100PointsFromGameBets(ruleBookData.getPointsForCorrectWinnerBetPer100PointsFromGameBets());

        // add or update rule book
        if (!entityManager.contains(newRuleBook)) {
            entityManager.persist(newRuleBook);
        }
        entityManager.flush();
        entityManager.refresh(newRuleBook);

        // return new userGroupId
        Long newRuleBookId = newRuleBook.getId();
        if (newRuleBookId != null) {
            RuleBookIdTO result = new RuleBookIdTO();
            result.setTechId(newRuleBookId);
            return result;
        } else {
            return null;
        }
    }

    @Override
    public void deleteRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookIdTO ruleBookId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public SportNameTO[] getSports(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TournamentTypeNameTO[] findTournamentTypes(ContextTO context, SessionIdTO sessionId, TournamentTypeSearchDataTO tournTypeSearchData)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TeamNameTO[] findTeams(ContextTO context, SessionIdTO sessionId, TeamSearchDataTO teamSearchData) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TournamentTypeNameTO[] getTournamentTypesForTeams(ContextTO context, SessionIdTO sessionId, TeamIdTO[] teamIdList) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TeamNameTO[] getTeamsForTournamentTypes(ContextTO context, SessionIdTO sessionId, TournamentTypeIdTO[] tournTypeIdList) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public SportTO readSport(ContextTO context, SessionIdTO sessionId, SportIdTO sportId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TournamentTypeTO readTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeIdTO tournTypeId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TeamTO readTeam(ContextTO context, SessionIdTO sessionId, TeamIdTO teamId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public SportIdTO createOrUpdateSport(ContextTO context, SessionIdTO sessionId, SportDataTO sportData) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void deleteSport(ContextTO context, SessionIdTO sessionId, SportIdTO sportId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TournamentTypeIdTO createOrUpdateTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeDataArgumentTO tournTypeData)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void deleteTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeIdTO tournTypeId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TeamIdTO createOrUpdateTeam(ContextTO context, SessionIdTO sessionId, TeamDataTO teamData) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void deleteTeam(ContextTO context, SessionIdTO sessionId, TeamIdTO teamId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void addAllowedTournamentTypesToTeam(ContextTO context, SessionIdTO sessionId, TeamIdTO teamId, TournamentTypeIdTO[] tournTypeIdList)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void removeAllowedTournamentTypesFromTeam(ContextTO context, SessionIdTO sessionId, TeamIdTO teamId, TournamentTypeIdTO[] tournTypeIdList)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void addAllowedTeamsToTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeIdTO tournTypeId, TeamIdTO[] teamIdList)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void removeAllowedTeamsFromTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeIdTO tournTypeId, TeamIdTO[] teamIdList)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public BetCommunityNameTO[] getBetCommunitiesForUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public BetCommunityNameTO[] getBetCommunitiesForUser(ContextTO context, SessionIdTO sessionId, UserIdTO userId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public BetCommunityNameTO[] getModeratingBetCommunities(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public Boolean isBetCommunityModerator(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserGroupNameTO[] findUserGroups(ContextTO context, SessionIdTO sessionId, UserGroupSearchDataTO userGroupSearchData) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserGroupNameTO[] getUserGroupsForUser(ContextTO context, SessionIdTO sessionId, UserIdTO userId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserGroupNameTO[] getParticipatingUserGroups(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserGroupNameTO[] getModeratingUserGroups(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public Boolean isUserGroupModerator(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public RuleBookNameTO[] getAllFinalRuleBooks(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserGroupTO readUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public RuleBookTO readRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookIdTO ruleBookId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public BetCommunityIdTO createOrUpdateBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityDataArgumentTO betCommunityData)
            throws RemoteException {
        // check data
        dataVerifier.checkData(context, betCommunityData);

        // save current date and time
        Date currentDate = new Date();

        // read session
        SessionEO session = sessionManager.getSession(context, sessionId, currentDate);
        UserEO user = session.getUser();

        // check permissions and read bet community (or create new one)
        BetCommunityEO newBetCommunity;
        Long existingBetCommunityId = betCommunityData.getTechId();
        if (existingBetCommunityId != null) {
            newBetCommunity = entityManager.find(BetCommunityEO.class, existingBetCommunityId);
            if (newBetCommunity == null) {
                throw new ObjectNotFound(context, BetCommunityEO.class, existingBetCommunityId);
            }
            permVerifier.checkBetCommunityPermission(context, user, newBetCommunity);
        } else {
            permVerifier.checkBetCommunityPermission(context, user);
            newBetCommunity = new BetCommunityEO();

            // assign foundation data for new bet community
            newBetCommunity.setFounder(user);
            newBetCommunity.setFoundation(currentDate);
            newBetCommunity.setDeleted(null);
        }

        // assign new bet community data
        newBetCommunity.setCommunityName(betCommunityData.getCommunityName());
        newBetCommunity.setDescription(trans.translateDescription(context, betCommunityData.getDescription()));
        if (betCommunityData.getRuleBookId() != null) {
            Long ruleBookId = betCommunityData.getRuleBookId().getTechId();
            RuleBookEO ruleBook = entityManager.find(RuleBookEO.class, ruleBookId);
            if (ruleBook == null) {
                throw new ObjectNotFound(context, RuleBookEO.class, ruleBookId);
            }
            newBetCommunity.setRuleBook(ruleBook);
        } else {
            newBetCommunity.setRuleBook(null);
        }

        // add or update bet community
        if (!entityManager.contains(newBetCommunity)) {
            entityManager.persist(newBetCommunity);
        }
        entityManager.flush();
        if (newBetCommunity.getRuleBook() != null) {
            entityManager.refresh(newBetCommunity.getRuleBook());
        }
        entityManager.refresh(newBetCommunity);

        // add user as moderator
        newBetCommunity.getModerators().add(user);
        entityManager.flush();
        entityManager.refresh(user);

        // return new betCommunityId
        Long newBetCommunityId = newBetCommunity.getId();
        if (newBetCommunityId != null) {
            BetCommunityIdTO result = new BetCommunityIdTO();
            result.setTechId(newBetCommunityId);
            return result;
        } else {
            return null;
        }
    }

    @Override
    public void deleteBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void addUserGroupToBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, UserGroupIdTO userGroupId)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void removeUserGroupFromBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, UserGroupIdTO userGroupId)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void addTournamentToBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, TournamentIdTO tournId)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void removeTournamentFromBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, TournamentIdTO tournId)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void addModeratorToBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, UserIdTO modId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void removeModeratorFromBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, UserIdTO modId)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserGroupIdTO createOrUpdateUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupDataTO userGroupData) throws RemoteException {
        // check data
        dataVerifier.checkData(context, userGroupData);

        // save current date and time
        Date currentDate = new Date();

        // read session
        SessionEO session = sessionManager.getSession(context, sessionId, currentDate);
        UserEO user = session.getUser();

        // check permissions and read user group (or create new one)
        UserGroupEO newUserGroup;
        Long existingUserGroupId = userGroupData.getTechId();
        if (existingUserGroupId != null) {
            newUserGroup = entityManager.find(UserGroupEO.class, existingUserGroupId);
            if (newUserGroup == null) {
                throw new ObjectNotFound(context, UserGroupEO.class, existingUserGroupId);
            }
            permVerifier.checkUserGroupPermission(context, user, newUserGroup);
        } else {
            permVerifier.checkUserGroupPermission(context, user);
            newUserGroup = new UserGroupEO();

            // assign foundation data for new user group
            newUserGroup.setFounder(user);
            newUserGroup.setFoundation(currentDate);
            newUserGroup.setDeleted(null);
        }

        // assign new user group data
        newUserGroup.setGroupName(userGroupData.getGroupName());
        newUserGroup.setDescription(trans.translateDescription(context, userGroupData.getDescription()));

        // add or update user group
        if (!entityManager.contains(newUserGroup)) {
            entityManager.persist(newUserGroup);
        }
        entityManager.flush();
        entityManager.refresh(newUserGroup);

        // add user as moderator and participant
        newUserGroup.getModerators().add(user);
        newUserGroup.getUsers().add(user);
        entityManager.flush();
        entityManager.refresh(user);

        // return new userGroupId
        Long newUserGroupId = newUserGroup.getId();
        if (newUserGroupId != null) {
            UserGroupIdTO result = new UserGroupIdTO();
            result.setTechId(newUserGroupId);
            return result;
        } else {
            return null;
        }
    }

    @Override
    public void deleteUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void addUserToUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId, UserIdTO userId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void removeUserFromUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId, UserIdTO userId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void joinUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void leaveUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void addModeratorToUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId, UserIdTO modId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void removeModeratorFromUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId, UserIdTO modId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TournamentDescrTO[] getOpenParticipatingTournaments(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TournamentDescrTO[] getAllOpenTournaments(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public Boolean hasMissingWinnerBetForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public WinnerBetTO[] getWinnerBetsForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TeamNameTO[] getTeamsForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameDataResultTO[] getGamesWithMissingBetForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameTO[] getGamesWithPopulatedBetForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameTO[] getGamesWithForgottenBetForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameDataResultTO[] getGamesWithMissingResultForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameTO[] getGamesWithPopulatedResultForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameDataResultTO[] getGamesWithMissingBetForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameTO[] getGamesWithPopulatedBetForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameTO[] getGamesWithForgottenBetForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameDataResultTO[] getGamesWithMissingResultForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameTO[] getGamesWithPopulatedResultForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void setWinnerBet(ContextTO context, SessionIdTO sessionId, WinnerBetDataArgumentTO winnerBetData) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void setGameBet(ContextTO context, SessionIdTO sessionId, GameBetDataWithoutGameTO gameBetData, GameIdTO gameId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void setWinnerTeam(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId, TeamIdTO winnerTeamId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void delWinnerTeam(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void setGameResult(ContextTO context, SessionIdTO sessionId, GameResultDataTO gameResultData) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void delGameResult(ContextTO context, SessionIdTO sessionId, GameIdTO gameId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public CommentIdTO createOrUpdateCommentForWinnerBet(ContextTO context, SessionIdTO sessionId, WinnerBetIdTO winnerBetId, CommentDataTO commentData)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public CommentIdTO createOrUpdateCommentForWinnerTeam(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId, CommentDataTO commentData)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public CommentIdTO createOrUpdateCommentForGameBet(ContextTO context, SessionIdTO sessionId, GameBetIdTO gameBetId, CommentDataTO commentData)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public CommentIdTO createOrUpdateCommentForGameResult(ContextTO context, SessionIdTO sessionId, GameIdTO gameId, CommentDataTO commentData)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void removeCommentForWinnerBet(ContextTO context, SessionIdTO sessionId, CommentIdTO commentId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void removeCommentForWinnerTeam(ContextTO context, SessionIdTO sessionId, CommentIdTO commentId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void removeCommentForGameBet(ContextTO context, SessionIdTO sessionId, CommentIdTO commentId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void removeCommentForGameResult(ContextTO context, SessionIdTO sessionId, CommentIdTO commentId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameDataResultTO[] getGamesWithMissingTeamsForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public void updateGame(ContextTO context, SessionIdTO sessionId, GameDataArgumentTO gameData) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public BetCommunityNameTO[] findBetCommunities(ContextTO context, SessionIdTO sessionId, BetCommunitySearchDataTO betCommunitySearchData)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public BetCommunityNameTO[] getParticipatingBetCommunities(ContextTO context, SessionIdTO sessionId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public BetCommunityTO readBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TournamentDescrTO[] findTournaments(ContextTO context, SessionIdTO sessionId, TournamentSearchDataTO tournSearchData) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public TournamentTO readTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameDataResultTO[] getGamesForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameWithPointsTO[] getGamesWithPointsByTournament(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, TournamentIdTO tournId,
            Boolean onlyGamesWithResult) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public GameWithPointsTO[] getGamesWithPointsByPeriod(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, PeriodTO period,
            Boolean onlyGamesWithResult) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserPointsTO[] getTournamentRankingByUser(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, TournamentIdTO tournId,
            Boolean onlyUsersWithBet) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserGroupPointsTO[] getTournamentRankingByUserGroup(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, TournamentIdTO tournId)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserPointsTO[] getTournamentRoundRankingByUser(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId,
            TournamentRoundIdTO tournRoundId, Boolean onlyUsersWithBet) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserGroupPointsTO[] getTournamentRoundRankingByUserGroup(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId,
            TournamentRoundIdTO tournRoundId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserPointsTO[] getMatchDayRankingByUser(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, MatchDayIdTO matchDayId,
            Boolean onlyUsersWithBet) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserGroupPointsTO[] getMatchDayRankingByUserGroup(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, MatchDayIdTO matchDayId)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserPointsTO[] getAllTimeRankingByUser(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, Boolean onlyUsersWithBet)
            throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }

    @Override
    public UserGroupPointsTO[] getAllTimeRankingByUserGroup(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId) throws RemoteException {
        throw new NotYetImplemented(context); // TODO
    }
}
