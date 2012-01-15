package de.tipit.server.transfer.service;

import java.rmi.RemoteException;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import de.tipit.server.transfer.access.TournamentAdmin;
import de.tipit.server.transfer.data.ContextTO;
import de.tipit.server.transfer.data.GameDataArgumentTO;
import de.tipit.server.transfer.data.GameIdTO;
import de.tipit.server.transfer.data.MatchDayDataArgumentTO;
import de.tipit.server.transfer.data.MatchDayIdTO;
import de.tipit.server.transfer.data.SessionIdTO;
import de.tipit.server.transfer.data.SportNameTO;
import de.tipit.server.transfer.data.TeamNameTO;
import de.tipit.server.transfer.data.TournamentDataArgumentTO;
import de.tipit.server.transfer.data.TournamentDescrTO;
import de.tipit.server.transfer.data.TournamentIdTO;
import de.tipit.server.transfer.data.TournamentRoundDataArgumentTO;
import de.tipit.server.transfer.data.TournamentRoundIdTO;
import de.tipit.server.transfer.data.TournamentSearchDataTO;
import de.tipit.server.transfer.data.TournamentTO;
import de.tipit.server.transfer.data.UserIdTO;
import de.tipit.server.transfer.data.UserNameTO;
import de.tipit.server.transfer.data.UserSearchDataTO;

@WebService(portName = "TournamentAdminPort", name = "TournamentAdminService")
@SOAPBinding(style = Style.RPC)
public class TournamentAdminService implements TournamentAdmin {

    private final TournamentAdmin delegate;

    public TournamentAdminService(final TournamentAdmin delegate) {
        this.delegate = delegate;
    }

    @WebResult(partName = "sportNameList")
    @Override
    public SportNameTO[] getSports(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId)
            throws RemoteException {
        return delegate.getSports(context, sessionId);
    }

    @WebResult(partName = "tournDescrList")
    @Override
    public TournamentDescrTO[] findTournaments(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournSearchData") TournamentSearchDataTO tournSearchData) throws RemoteException {
        return delegate.findTournaments(context, sessionId, tournSearchData);
    }

    @WebResult(partName = "tournDescrList")
    @Override
    public TournamentDescrTO[] getModeratedTournaments(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId) throws RemoteException {
        return delegate.getModeratedTournaments(context, sessionId);
    }

    @WebResult(partName = "tournDescrList")
    @Override
    public TournamentDescrTO[] getOwnTournaments(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId)
            throws RemoteException {
        return delegate.getOwnTournaments(context, sessionId);
    }

    @WebResult(partName = "teamNameList")
    @Override
    public TeamNameTO[] getTeamsForTournament(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.getTeamsForTournament(context, sessionId, tournId);
    }

    @WebResult(partName = "tourn")
    @Override
    public TournamentTO readTournament(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.readTournament(context, sessionId, tournId);
    }

    @WebResult(partName = "userNameList")
    @Override
    public UserNameTO[] findUsers(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userSearchData") UserSearchDataTO userSearchData) throws RemoteException {
        return delegate.findUsers(context, sessionId, userSearchData);
    }

    @WebResult(partName = "newTournamentId")
    @Override
    public TournamentIdTO createOrUpdateTournament(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournData") TournamentDataArgumentTO tournData) throws RemoteException {
        return delegate.createOrUpdateTournament(context, sessionId, tournData);
    }

    @Override
    public void deleteTournament(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        delegate.deleteTournament(context, sessionId, tournId);
    }

    @WebResult(partName = "bool")
    @Override
    public Boolean isTournamentModerator(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.isTournamentModerator(context, sessionId, tournId);
    }

    @Override
    public void addModeratorToTournament(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournId") TournamentIdTO tournId, @WebParam(partName = "modId") UserIdTO modId) throws RemoteException {
        delegate.addModeratorToTournament(context, sessionId, tournId, modId);
    }

    @Override
    public void removeModeratorFromTournament(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournId") TournamentIdTO tournId, @WebParam(partName = "modId") UserIdTO modId) throws RemoteException {
        delegate.removeModeratorFromTournament(context, sessionId, tournId, modId);
    }

    @WebResult(partName = "newTournamentRoundId")
    @Override
    public TournamentRoundIdTO createOrUpdateTournamentRound(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "tournRoundData") TournamentRoundDataArgumentTO tournRoundData)
            throws RemoteException {
        return delegate.createOrUpdateTournamentRound(context, sessionId, tournRoundData);
    }

    @Override
    public void deleteTournamentRound(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournRoundId") TournamentRoundIdTO tournRoundId) throws RemoteException {
        delegate.deleteTournamentRound(context, sessionId, tournRoundId);
    }

    @WebResult(partName = "newMatchDayId")
    @Override
    public MatchDayIdTO createOrUpdateMatchDay(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "matchDayData") MatchDayDataArgumentTO matchDayData) throws RemoteException {
        return delegate.createOrUpdateMatchDay(context, sessionId, matchDayData);
    }

    @Override
    public void deleteMatchDay(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "matchDayId") MatchDayIdTO matchDayId) throws RemoteException {
        delegate.deleteMatchDay(context, sessionId, matchDayId);
    }

    @WebResult(partName = "newGameId")
    @Override
    public GameIdTO createOrUpdateGame(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "gameData") GameDataArgumentTO gameData) throws RemoteException {
        return delegate.createOrUpdateGame(context, sessionId, gameData);
    }

    @Override
    public void deleteGame(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "gameId") GameIdTO gameId) throws RemoteException {
        delegate.deleteGame(context, sessionId, gameId);
    }
}
