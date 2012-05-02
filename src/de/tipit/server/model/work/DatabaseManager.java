package de.tipit.server.model.work;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import de.tipit.server.model.data.EM_2012;
import de.tipit.server.model.data.TestUser;
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
import de.tipit.server.transfer.access.GeneralError;
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
import de.tipit.server.transfer.data.MatchDayDescrTO;
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

    private DatabaseManager(final EntityManager entityManager, final DatabaseCharger... chargers) {
        this.entityManager = entityManager;
        this.sessionManager = new SessionManager(entityManager);
        this.permVerifier = new PermissionVerifier(entityManager);
        this.dataVerifier = new DataVerifier(entityManager);
        this.trans = new Translator();

        // create initial data, if the database is empty
        if (getCount(UserEO.class) == 0L) {
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            try {
                for (int i = 0; i < chargers.length; i++) {
                    chargers[i].add(this.entityManager);
                }
            } catch (RuntimeException exc) {
                trans.rollback();
                throw exc;
            }
            trans.commit();
        }
    }

    public static DatabaseManager createInstance(final EntityManager entityManager) {
        return new DatabaseManager(entityManager, new TestUser(), new EM_2012());
    }

    public long getCount(Class<?> eoClass) {
        return (Long) (entityManager.createQuery("select count(o) from " + eoClass.getName() + " o").getSingleResult());
    }

    @Override
    public SessionTO renewSession(ContextTO context, SessionIdTO sessionId) throws GeneralError {
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
    public SessionTO doLogin(ContextTO context, UserAccountTO userAccount, LoginParameterTO loginParameter) throws GeneralError {
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
    public SessionTO doLoginAsGuest(ContextTO context) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void doLogout(ContextTO context, SessionIdTO sessionId) throws GeneralError {
        // save current date and time
        Date currentDate = new Date();

        // read session
        SessionEO session = sessionManager.getSession(context, sessionId, currentDate);
        UserEO user = session.getUser();

        // check permissions
        permVerifier.checkGeneralPermission(context, user);

        // destroy session
        sessionManager.destroySession(context, session, currentDate);

        // no result given
        return null;
    }

    @Override
    public UserIdTO createUser(ContextTO context, UserDataTO userData) throws GeneralError {
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
    public Void updateUser(ContextTO context, SessionIdTO sessionId, UserDataTO userData) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void resetPassword(ContextTO context, UserContactTO userContact) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void setInactive(ContextTO context, SessionIdTO sessionId, Boolean isInactive) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void setDisabled(ContextTO context, SessionIdTO sessionId, UserIdTO userId, Boolean isDisabled) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public UserTO readOwnUser(ContextTO context, SessionIdTO sessionId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public UserTO readUser(ContextTO context, SessionIdTO sessionId, UserIdTO userId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<TournamentDescrTO> getModeratedTournaments(ContextTO context, SessionIdTO sessionId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<TournamentDescrTO> getOwnTournaments(ContextTO context, SessionIdTO sessionId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<UserNameTO> findUsers(ContextTO context, SessionIdTO sessionId, UserSearchDataTO userSearchData) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public TournamentIdTO createOrUpdateTournament(ContextTO context, SessionIdTO sessionId, TournamentDataArgumentTO tournData) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void deleteTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Boolean isTournamentModerator(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void addModeratorToTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId, UserIdTO modId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void removeModeratorFromTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId, UserIdTO modId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public TournamentRoundIdTO createOrUpdateTournamentRound(ContextTO context, SessionIdTO sessionId, TournamentRoundDataArgumentTO tournRoundData)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void deleteTournamentRound(ContextTO context, SessionIdTO sessionId, TournamentRoundIdTO tournRoundId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public MatchDayIdTO createOrUpdateMatchDay(ContextTO context, SessionIdTO sessionId, MatchDayDataArgumentTO matchDayData) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void deleteMatchDay(ContextTO context, SessionIdTO sessionId, MatchDayIdTO matchDayId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public GameIdTO createOrUpdateGame(ContextTO context, SessionIdTO sessionId, GameDataArgumentTO gameData) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void deleteGame(ContextTO context, SessionIdTO sessionId, GameIdTO gameId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<RuleBookNameTO> findRuleBooks(ContextTO context, SessionIdTO sessionId, RuleBookSearchDataTO ruleBookSearchData) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<RuleBookNameTO> getNotFinalRuleBooks(ContextTO context, SessionIdTO sessionId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<RuleBookNameTO> getOwnRuleBooks(ContextTO context, SessionIdTO sessionId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Integer calculatePointsForStoredRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookIdTO ruleBookId, Integer homeResult, Integer awayResult,
            Integer averageHomeResultBet, Integer averageAwayResultBet, Integer homeResultBet, Integer awayResultBet) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Integer calculatePointsForNewRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookDataTO ruleBookData, Integer homeResult, Integer awayResult,
            Integer averageHomeResultBet, Integer averageAwayResultBet, Integer homeResultBet, Integer awayResultBet) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public RuleBookIdTO createOrUpdateRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookDataTO ruleBookData) throws GeneralError {
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
    public Void deleteRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookIdTO ruleBookId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<SportNameTO> getSports(ContextTO context, SessionIdTO sessionId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<TournamentTypeNameTO> findTournamentTypes(ContextTO context, SessionIdTO sessionId, TournamentTypeSearchDataTO tournTypeSearchData)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<TeamNameTO> findTeams(ContextTO context, SessionIdTO sessionId, TeamSearchDataTO teamSearchData) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<TournamentTypeNameTO> getTournamentTypesForTeams(ContextTO context, SessionIdTO sessionId, List<TeamIdTO> teamIdList) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<TeamNameTO> getTeamsForTournamentTypes(ContextTO context, SessionIdTO sessionId, List<TournamentTypeIdTO> tournTypeIdList) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public SportTO readSport(ContextTO context, SessionIdTO sessionId, SportIdTO sportId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public TournamentTypeTO readTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeIdTO tournTypeId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public TeamTO readTeam(ContextTO context, SessionIdTO sessionId, TeamIdTO teamId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public SportIdTO createOrUpdateSport(ContextTO context, SessionIdTO sessionId, SportDataTO sportData) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void deleteSport(ContextTO context, SessionIdTO sessionId, SportIdTO sportId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public TournamentTypeIdTO createOrUpdateTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeDataArgumentTO tournTypeData)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void deleteTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeIdTO tournTypeId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public TeamIdTO createOrUpdateTeam(ContextTO context, SessionIdTO sessionId, TeamDataTO teamData) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void deleteTeam(ContextTO context, SessionIdTO sessionId, TeamIdTO teamId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void addAllowedTournamentTypesToTeam(ContextTO context, SessionIdTO sessionId, TeamIdTO teamId, List<TournamentTypeIdTO> tournTypeIdList)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void removeAllowedTournamentTypesFromTeam(ContextTO context, SessionIdTO sessionId, TeamIdTO teamId, List<TournamentTypeIdTO> tournTypeIdList)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void addAllowedTeamsToTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeIdTO tournTypeId, List<TeamIdTO> teamIdList)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void removeAllowedTeamsFromTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeIdTO tournTypeId, List<TeamIdTO> teamIdList)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<BetCommunityNameTO> getBetCommunitiesForUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<BetCommunityNameTO> getBetCommunitiesForUser(ContextTO context, SessionIdTO sessionId, UserIdTO userId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<BetCommunityNameTO> getModeratingBetCommunities(ContextTO context, SessionIdTO sessionId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Boolean isBetCommunityModerator(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<UserGroupNameTO> findUserGroups(ContextTO context, SessionIdTO sessionId, UserGroupSearchDataTO userGroupSearchData) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<UserGroupNameTO> getUserGroupsForUser(ContextTO context, SessionIdTO sessionId, UserIdTO userId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<UserGroupNameTO> getParticipatingUserGroups(ContextTO context, SessionIdTO sessionId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<UserGroupNameTO> getModeratingUserGroups(ContextTO context, SessionIdTO sessionId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Boolean isUserGroupModerator(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<RuleBookNameTO> getAllFinalRuleBooks(ContextTO context, SessionIdTO sessionId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public UserGroupTO readUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public RuleBookTO readRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookIdTO ruleBookId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public BetCommunityIdTO createOrUpdateBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityDataArgumentTO betCommunityData)
            throws GeneralError {
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
    public Void deleteBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void addUserGroupToBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, UserGroupIdTO userGroupId)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void removeUserGroupFromBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, UserGroupIdTO userGroupId)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void addTournamentToBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, TournamentIdTO tournId)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void removeTournamentFromBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, TournamentIdTO tournId)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void addModeratorToBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, UserIdTO modId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void removeModeratorFromBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, UserIdTO modId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public UserGroupIdTO createOrUpdateUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupDataTO userGroupData) throws GeneralError {
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
    public Void deleteUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void addUserToUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId, UserIdTO userId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void removeUserFromUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId, UserIdTO userId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void joinUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void leaveUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void addModeratorToUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId, UserIdTO modId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void removeModeratorFromUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId, UserIdTO modId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<TournamentDescrTO> getOpenParticipatingTournaments(ContextTO context, SessionIdTO sessionId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<TournamentDescrTO> getAllOpenTournaments(ContextTO context, SessionIdTO sessionId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Boolean hasMissingWinnerBetForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<WinnerBetTO> getWinnerBetsForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<TeamNameTO> getTeamsForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<GameDataResultTO> getGamesWithMissingBetForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<GameTO> getGamesWithPopulatedBetForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<GameTO> getGamesWithForgottenBetForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<GameDataResultTO> getGamesWithMissingResultForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<GameTO> getGamesWithPopulatedResultForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<GameDataResultTO> getGamesWithMissingBetForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws GeneralError {
        List<GameDataResultTO> result = new ArrayList<GameDataResultTO>();

        GameDataResultTO d1 = new GameDataResultTO();
        GameDataResultTO d2 = new GameDataResultTO();
        GameDataResultTO d3 = new GameDataResultTO();
        GameDataResultTO d4 = new GameDataResultTO();
        GameDataResultTO d5 = new GameDataResultTO();
        GameDataResultTO d6 = new GameDataResultTO();
        GameDataResultTO d7 = new GameDataResultTO();
        GameDataResultTO d8 = new GameDataResultTO();
        GameDataResultTO d9 = new GameDataResultTO();

        MatchDayDescrTO m1 = new MatchDayDescrTO();
        MatchDayDescrTO m2 = new MatchDayDescrTO();
        MatchDayDescrTO m3 = new MatchDayDescrTO();
        MatchDayDescrTO m4 = new MatchDayDescrTO();
        MatchDayDescrTO m5 = new MatchDayDescrTO();
        MatchDayDescrTO m6 = new MatchDayDescrTO();

        TeamNameTO t1 = new TeamNameTO();
        TeamNameTO t2 = new TeamNameTO();
        TeamNameTO t3 = new TeamNameTO();
        TeamNameTO t4 = new TeamNameTO();
        TeamNameTO t5 = new TeamNameTO();
        TeamNameTO t6 = new TeamNameTO();

        m1.setTechId(11L);
        m1.setDisplayDescr("1. Spieltag / EM 2012");

        m2.setTechId(22L);
        m2.setDisplayDescr("2. Spieltag / EM 2012");

        m3.setTechId(33L);
        m3.setDisplayDescr("3. Spieltag / EM 2012");

        m4.setTechId(44L);
        m4.setDisplayDescr("4. Spieltag / EM 2012");

        m5.setTechId(55L);
        m5.setDisplayDescr("5. Spieltag / EM 2012");

        m6.setTechId(66L);
        m6.setDisplayDescr("6. Spieltag / EM 2012");

        t1.setTechId(51L);
        t1.setDisplayName("Deutschland");
        t1.setShortDisplayName("");

        t2.setTechId(52L);
        t2.setDisplayName("England");
        t2.setShortDisplayName("");

        t3.setTechId(53L);
        t3.setDisplayName("Frankreich");
        t3.setShortDisplayName("");

        t4.setTechId(54L);
        t4.setDisplayName("Italien");
        t4.setShortDisplayName("");

        t5.setTechId(55L);
        t5.setDisplayName("Spanien");
        t5.setShortDisplayName("");

        t6.setTechId(56L);
        t6.setDisplayName("Ukraine");
        t6.setShortDisplayName("");

        d1.setTechId(61L);
        d1.setMatchDayDescr(m1);
        d1.setGameNumber(101);
        d1.setMatchPoint(new Date());
        d1.setHomeTeamName(t1);
        d1.setAwayTeamName(t2);

        d2.setTechId(62L);
        d2.setMatchDayDescr(m1);
        d2.setGameNumber(102);
        d2.setMatchPoint(new Date());
        d2.setHomeTeamName(t3);
        d2.setAwayTeamName(t4);

        d3.setTechId(63L);
        d3.setMatchDayDescr(m1);
        d3.setGameNumber(103);
        d3.setMatchPoint(new Date());
        d3.setHomeTeamName(t5);
        d3.setAwayTeamName(t6);

        d4.setTechId(64L);
        d4.setMatchDayDescr(m2);
        d4.setGameNumber(104);
        d4.setMatchPoint(new Date());
        d4.setHomeTeamName(t2);
        d4.setAwayTeamName(t5);

        d5.setTechId(65L);
        d5.setMatchDayDescr(m3);
        d5.setGameNumber(105);
        d5.setMatchPoint(new Date());
        d5.setHomeTeamName(t4);
        d5.setAwayTeamName(t1);

        d6.setTechId(66L);
        d6.setMatchDayDescr(m4);
        d6.setGameNumber(106);
        d6.setMatchPoint(new Date());
        d6.setHomeTeamName(t6);
        d6.setAwayTeamName(t3);

        d7.setTechId(67L);
        d7.setMatchDayDescr(m5);
        d7.setGameNumber(106);
        d7.setMatchPoint(new Date());
        d7.setHomeTeamName(t6);
        d7.setAwayTeamName(t3);

        d8.setTechId(68L);
        d8.setMatchDayDescr(m6);
        d8.setGameNumber(106);
        d8.setMatchPoint(new Date());
        d8.setHomeTeamName(t6);
        d8.setAwayTeamName(t3);

        d9.setTechId(69L);
        d9.setMatchDayDescr(m6);
        d9.setGameNumber(106);
        d9.setMatchPoint(new Date());
        d9.setHomeTeamName(t6);
        d9.setAwayTeamName(t3);

        result.add(d1);
        result.add(d2);
        result.add(d3);
        result.add(d4);
        result.add(d5);
        result.add(d6);
        result.add(d7);
        result.add(d8);
        result.add(d9);

        return result; // TODO
    }

    @Override
    public List<GameTO> getGamesWithPopulatedBetForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<GameTO> getGamesWithForgottenBetForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<GameDataResultTO> getGamesWithMissingResultForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<GameTO> getGamesWithPopulatedResultForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void setWinnerBet(ContextTO context, SessionIdTO sessionId, WinnerBetDataArgumentTO winnerBetData) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void setGameBet(ContextTO context, SessionIdTO sessionId, GameBetDataWithoutGameTO gameBetData, GameIdTO gameId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void setWinnerTeam(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId, TeamIdTO winnerTeamId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void delWinnerTeam(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void setGameResult(ContextTO context, SessionIdTO sessionId, GameResultDataTO gameResultData) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void delGameResult(ContextTO context, SessionIdTO sessionId, GameIdTO gameId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public CommentIdTO createOrUpdateCommentForWinnerBet(ContextTO context, SessionIdTO sessionId, WinnerBetIdTO winnerBetId, CommentDataTO commentData)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public CommentIdTO createOrUpdateCommentForWinnerTeam(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId, CommentDataTO commentData)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public CommentIdTO createOrUpdateCommentForGameBet(ContextTO context, SessionIdTO sessionId, GameBetIdTO gameBetId, CommentDataTO commentData)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public CommentIdTO createOrUpdateCommentForGameResult(ContextTO context, SessionIdTO sessionId, GameIdTO gameId, CommentDataTO commentData)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void removeCommentForWinnerBet(ContextTO context, SessionIdTO sessionId, CommentIdTO commentId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void removeCommentForWinnerTeam(ContextTO context, SessionIdTO sessionId, CommentIdTO commentId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void removeCommentForGameBet(ContextTO context, SessionIdTO sessionId, CommentIdTO commentId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void removeCommentForGameResult(ContextTO context, SessionIdTO sessionId, CommentIdTO commentId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<GameDataResultTO> getGamesWithMissingTeamsForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public Void updateGame(ContextTO context, SessionIdTO sessionId, GameDataArgumentTO gameData) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<BetCommunityNameTO> findBetCommunities(ContextTO context, SessionIdTO sessionId, BetCommunitySearchDataTO betCommunitySearchData)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<BetCommunityNameTO> getParticipatingBetCommunities(ContextTO context, SessionIdTO sessionId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public BetCommunityTO readBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<TournamentDescrTO> findTournaments(ContextTO context, SessionIdTO sessionId, TournamentSearchDataTO tournSearchData) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public TournamentTO readTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<GameDataResultTO> getGamesForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<GameWithPointsTO> getGamesWithPointsByTournament(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId,
            TournamentIdTO tournId, Boolean onlyGamesWithResult) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<GameWithPointsTO> getGamesWithPointsByPeriod(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, PeriodTO period,
            Boolean onlyGamesWithResult) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<UserPointsTO> getTournamentRankingByUser(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, TournamentIdTO tournId,
            Boolean onlyUsersWithBet) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<UserGroupPointsTO> getTournamentRankingByUserGroup(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId,
            TournamentIdTO tournId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<UserPointsTO> getTournamentRoundRankingByUser(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId,
            TournamentRoundIdTO tournRoundId, Boolean onlyUsersWithBet) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<UserGroupPointsTO> getTournamentRoundRankingByUserGroup(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId,
            TournamentRoundIdTO tournRoundId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<UserPointsTO> getMatchDayRankingByUser(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, MatchDayIdTO matchDayId,
            Boolean onlyUsersWithBet) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<UserGroupPointsTO> getMatchDayRankingByUserGroup(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId,
            MatchDayIdTO matchDayId) throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<UserPointsTO> getAllTimeRankingByUser(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, Boolean onlyUsersWithBet)
            throws GeneralError {
        throw new NotYetImplemented(context);
    }

    @Override
    public List<UserGroupPointsTO> getAllTimeRankingByUserGroup(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId) throws GeneralError {
        throw new NotYetImplemented(context);
    }
}
