package de.tipit.server.transfer.access;

import java.rmi.RemoteException;

import de.tipit.server.transfer.data.ContextTO;
import de.tipit.server.transfer.data.SessionIdTO;
import de.tipit.server.transfer.data.LoginParameterTO;
import de.tipit.server.transfer.data.SessionTO;
import de.tipit.server.transfer.data.UserAccountTO;
import de.tipit.server.transfer.data.UserContactTO;
import de.tipit.server.transfer.data.UserDataTO;
import de.tipit.server.transfer.data.UserIdTO;
import de.tipit.server.transfer.data.UserNameTO;
import de.tipit.server.transfer.data.UserSearchDataTO;
import de.tipit.server.transfer.data.UserTO;

public interface UserSession {

    SessionTO renewSession(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    SessionTO doLogin(ContextTO context, UserAccountTO userAccount, LoginParameterTO loginParameter) throws RemoteException;

    SessionTO doLoginAsGuest(ContextTO context) throws RemoteException;

    void doLogout(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    UserIdTO createUser(ContextTO context, UserDataTO userData) throws RemoteException;

    void updateUser(ContextTO context, SessionIdTO sessionId, UserDataTO userData) throws RemoteException;

    void resetPassword(ContextTO context, UserContactTO userContact) throws RemoteException;

    UserNameTO[] findUsers(ContextTO context, SessionIdTO sessionId, UserSearchDataTO userSearchData) throws RemoteException;

    void setInactive(ContextTO context, SessionIdTO sessionId, Boolean isInactive) throws RemoteException;

    void setDisabled(ContextTO context, SessionIdTO sessionId, UserIdTO userId, Boolean isDisabled) throws RemoteException;

    UserTO readOwnUser(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    UserTO readUser(ContextTO context, SessionIdTO sessionId, UserIdTO userId) throws RemoteException;
}
