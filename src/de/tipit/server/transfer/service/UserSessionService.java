package de.tipit.server.transfer.service;

import java.rmi.RemoteException;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import de.tipit.server.transfer.access.UserSession;
import de.tipit.server.transfer.data.ContextTO;
import de.tipit.server.transfer.data.LoginParameterTO;
import de.tipit.server.transfer.data.SessionIdTO;
import de.tipit.server.transfer.data.SessionTO;
import de.tipit.server.transfer.data.UserAccountTO;
import de.tipit.server.transfer.data.UserContactTO;
import de.tipit.server.transfer.data.UserDataTO;
import de.tipit.server.transfer.data.UserIdTO;
import de.tipit.server.transfer.data.UserNameTO;
import de.tipit.server.transfer.data.UserSearchDataTO;
import de.tipit.server.transfer.data.UserTO;

@WebService(portName = "UserSessionPort", name = "UserSessionService")
@SOAPBinding(style = Style.RPC)
public class UserSessionService implements UserSession {

    private final UserSession delegate;

    public UserSessionService(final UserSession delegate) {
        this.delegate = delegate;
    }

    @WebResult(partName = "session")
    @Override
    public SessionTO renewSession(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId)
            throws RemoteException {
        return delegate.renewSession(context, sessionId);
    }

    @WebResult(partName = "session")
    @Override
    public SessionTO doLogin(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "userAccount") UserAccountTO userAccount,
            @WebParam(partName = "loginParameter") LoginParameterTO loginParameter) throws RemoteException {
        return delegate.doLogin(context, userAccount, loginParameter);
    }

    @WebResult(partName = "session")
    @Override
    public SessionTO doLoginAsGuest(@WebParam(partName = "context") ContextTO context) throws RemoteException {
        return delegate.doLoginAsGuest(context);
    }

    @Override
    public void doLogout(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId) throws RemoteException {
        delegate.doLogout(context, sessionId);
    }

    @WebResult(partName = "newUserId")
    @Override
    public UserIdTO createUser(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "userData") UserDataTO userData) throws RemoteException {
        return delegate.createUser(context, userData);
    }

    @Override
    public void updateUser(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userData") UserDataTO userData) throws RemoteException {
        delegate.updateUser(context, sessionId, userData);
    }

    @Override
    public void resetPassword(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "userContact") UserContactTO userContact)
            throws RemoteException {
        delegate.resetPassword(context, userContact);
    }

    @WebResult(partName = "userNameList")
    @Override
    public UserNameTO[] findUsers(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userSearchData") UserSearchDataTO userSearchData) throws RemoteException {
        return delegate.findUsers(context, sessionId, userSearchData);
    }

    @Override
    public void setInactive(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "isInactive") Boolean isInactive) throws RemoteException {
        delegate.setInactive(context, sessionId, isInactive);
    }

    @Override
    public void setDisabled(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userId") UserIdTO userId, @WebParam(partName = "isDisabled") Boolean isDisabled) throws RemoteException {
        delegate.setDisabled(context, sessionId, userId, isDisabled);
    }

    @WebResult(partName = "user")
    @Override
    public UserTO readOwnUser(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId)
            throws RemoteException {
        return delegate.readOwnUser(context, sessionId);
    }

    @WebResult(partName = "user")
    @Override
    public UserTO readUser(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userId") UserIdTO userId) throws RemoteException {
        return delegate.readUser(context, sessionId, userId);
    }
}
