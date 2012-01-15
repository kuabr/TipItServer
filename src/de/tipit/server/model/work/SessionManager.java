package de.tipit.server.model.work;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import de.tipit.server.model.entity.SessionEO;
import de.tipit.server.model.entity.UserEO;
import de.tipit.server.model.i18n.error.BaseError;
import de.tipit.server.model.i18n.error.SessionNotValid;
import de.tipit.server.model.i18n.error.UserIsDisabled;
import de.tipit.server.transfer.data.ContextTO;
import de.tipit.server.transfer.data.SessionIdTO;

public class SessionManager {

    private final EntityManager entityManager;

    private static final String SESSION_ID_CHARACTERS = "#$%&'()*+,-./0123456789:;=?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz{|}~";

    private static final int MIN_SKIPPING_PROBABILITY = 10;

    private static final int MAX_SKIPPING_PROBABILITY = 99;

    private static final Random RANDOM_GENERATOR = new Random(new Date().getTime());

    /**
     * Internal method to "calculate" a new unique sessionId.
     * 
     * @return new sessionId
     */
    private String generateNewSessionId() {
        while (true) {
            StringBuilder newSessionIdBuilder = new StringBuilder();
            int skippingProbability = RANDOM_GENERATOR.nextInt(MAX_SKIPPING_PROBABILITY - MIN_SKIPPING_PROBABILITY + 1) + MIN_SKIPPING_PROBABILITY;
            for (int i = 0; i < SessionEO.MAX_SESSION_ID_LENGTH; i++) {
                if (RANDOM_GENERATOR.nextInt(skippingProbability) >= MIN_SKIPPING_PROBABILITY - 1) {
                    newSessionIdBuilder.append(SESSION_ID_CHARACTERS.charAt(RANDOM_GENERATOR.nextInt(SESSION_ID_CHARACTERS.length())));
                }
            }

            String newSessionId = newSessionIdBuilder.toString();
            if (entityManager.find(SessionEO.class, newSessionId) == null) {
                return newSessionId;
            }
        }
    }

    /**
     * Checks, if the given session is valid.
     * 
     * @param session
     *            session object
     * @param currentDate
     *            current date
     * @return 'true', if valid
     */
    private static boolean isValidSession(SessionEO session, Date currentDate) {
        if (session != null && session.getValid() != null && session.getValid()) {
            Date expirationDate = session.getExpiration();
            if (expirationDate != null) {
                return expirationDate.compareTo(currentDate) > 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Removes old sessions (for a user).
     * 
     * @param user
     *            user object
     * @param currentDate
     *            current date
     */
    private void removeOldSessions(UserEO user, Date currentDate) {
        if (user != null && user.getSessions() != null) {
            // determine latest date
            Date latestDate = null;
            for (SessionEO userSession : user.getSessions()) {
                Date lastUsageDate = userSession.getLastUsage();
                if (lastUsageDate != null) {
                    if (latestDate == null || latestDate.compareTo(lastUsageDate) < 0) {
                        latestDate = lastUsageDate;
                    }
                }
            }

            // determine old sessions to remove
            List<SessionEO> sessionsToRemove = new ArrayList<SessionEO>();
            for (SessionEO userSession : user.getSessions()) {
                if (latestDate != null && userSession.getLastUsage() != null && userSession.getLastUsage().compareTo(latestDate) == 0) {
                    continue;
                }

                if (!isValidSession(userSession, currentDate)) {
                    sessionsToRemove.add(userSession);
                }
            }

            // remove old sessions
            if (sessionsToRemove.size() > 0) {
                for (SessionEO session : sessionsToRemove) {
                    user.getSessions().remove(session);
                    entityManager.remove(session);
                }
                entityManager.flush();
            }
        }
    }

    /**
     * Standard constructor.
     * 
     * @param entityManager
     *            to access database stuff
     */
    public SessionManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Creates a new session for a user. Old sessions, which are not valid
     * anymore, are destroyed.
     * 
     * @param context
     *            the context
     * @param user
     *            user object
     * @param expirationDate
     *            expiration date
     * @param currentDate
     *            current date
     * @return new sessionId
     * @throws BaseError
     */
    public SessionEO createSession(ContextTO context, UserEO user, Date expirationDate, Date currentDate) throws BaseError {
        if (user != null) {
            removeOldSessions(user, currentDate);

            if (user.getDisabled() != null) {
                throw new UserIsDisabled(context, user.getUserName(), user.getDisabled());
            }

            SessionEO newSession = new SessionEO();
            newSession.setSessionId(generateNewSessionId());
            newSession.setUser(user);
            newSession.setExpiration(expirationDate);
            newSession.setCreation(currentDate);
            newSession.setLastUsage(currentDate);
            newSession.setValid(true);

            entityManager.persist(newSession);
            entityManager.flush();

            entityManager.refresh(newSession);
            entityManager.refresh(user);

            if (!isValidSession(newSession, currentDate)) {
                throw new SessionNotValid(context);
            } else {
                return newSession;
            }
        } else {
            return null;
        }
    }

    /**
     * Creates a new session as replacement for an old session. The old session
     * will be disabled.
     * 
     * @param context
     *            the context
     * @param oldSession
     *            old session
     * @param currentDate
     *            current date
     * @return new sessionId
     * @throws BaseError
     */
    public SessionEO replaceSession(ContextTO context, SessionEO oldSession, Date currentDate) throws BaseError {
        if (oldSession != null) {
            UserEO user = oldSession.getUser();

            if (user != null) {
                removeOldSessions(user, currentDate);

                if (user.getDisabled() != null) {
                    throw new UserIsDisabled(context, user.getUserName(), user.getDisabled());
                }

                SessionEO newSession = new SessionEO();
                newSession.setSessionId(generateNewSessionId());
                newSession.setUser(user);
                newSession.setExpiration(oldSession.getExpiration());
                newSession.setCreation(currentDate);
                newSession.setLastUsage(currentDate);
                newSession.setValid(oldSession.getValid());

                oldSession.setValid(false); // disable old session

                entityManager.persist(newSession);
                entityManager.flush();

                entityManager.refresh(newSession);
                entityManager.refresh(user);

                if (!isValidSession(newSession, currentDate)) {
                    throw new SessionNotValid(context);
                } else {
                    return newSession;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Destroys an existing session.
     * 
     * @param context
     *            the context
     * @param session
     *            session object
     * @param currentDate
     *            current date
     * @throws BaseError
     */
    public void destroySession(ContextTO context, SessionEO session, Date currentDate) throws BaseError {
        if (session != null) {
            session.setLastUsage(currentDate);
            session.setValid(false);
            entityManager.flush();
        }
    }

    /**
     * Destroys an existing session.
     * 
     * @param context
     *            the context
     * @param sessionId
     *            sessionId
     * @param currentDate
     *            current date
     * @throws BaseError
     */
    public void destroySession(ContextTO context, SessionIdTO sessionId, Date currentDate) throws BaseError {
        SessionEO session = entityManager.find(SessionEO.class, sessionId.getSessionId());
        destroySession(context, session, currentDate);
    }

    /**
     * Gives a session object for a given sessionId.
     * 
     * @param context
     *            the context
     * @param sessionId
     *            sessionId
     * @param currentDate
     *            current date
     * @return the user object
     * @throws BaseError
     */
    public SessionEO getSession(ContextTO context, SessionIdTO sessionId, Date currentDate) throws BaseError {
        if (sessionId != null && sessionId.getSessionId() != null) {
            SessionEO session = entityManager.find(SessionEO.class, sessionId.getSessionId());
            if (session != null) {
                if (session.getUser() != null) {
                    if (!isValidSession(session, currentDate)) {
                        throw new SessionNotValid(context);
                    } else if (session.getUser().getDisabled() != null) {
                        throw new UserIsDisabled(context, session.getUser().getUserName(), session.getUser().getDisabled());
                    } else {
                        session.setLastUsage(currentDate);
                        entityManager.flush();
                        return session;
                    }
                } else {
                    // internal error?
                    entityManager.remove(session);
                    entityManager.flush();
                }
            }
        }

        throw new SessionNotValid(context);
    }
}
