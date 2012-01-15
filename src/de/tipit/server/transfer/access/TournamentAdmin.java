package de.tipit.server.transfer.access;

import java.rmi.RemoteException;

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

public interface TournamentAdmin {

    SportNameTO[] getSports(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    TournamentDescrTO[] findTournaments(ContextTO context, SessionIdTO sessionId, TournamentSearchDataTO tournSearchData) throws RemoteException;

    TournamentDescrTO[] getModeratedTournaments(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    TournamentDescrTO[] getOwnTournaments(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    TeamNameTO[] getTeamsForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    TournamentTO readTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    UserNameTO[] findUsers(ContextTO context, SessionIdTO sessionId, UserSearchDataTO userSearchData) throws RemoteException;

    TournamentIdTO createOrUpdateTournament(ContextTO context, SessionIdTO sessionId, TournamentDataArgumentTO tournData) throws RemoteException;

    void deleteTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    Boolean isTournamentModerator(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    void addModeratorToTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId, UserIdTO modId) throws RemoteException;

    void removeModeratorFromTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId, UserIdTO modId) throws RemoteException;

    TournamentRoundIdTO createOrUpdateTournamentRound(ContextTO context, SessionIdTO sessionId, TournamentRoundDataArgumentTO tournRoundData)
            throws RemoteException;

    void deleteTournamentRound(ContextTO context, SessionIdTO sessionId, TournamentRoundIdTO tournRoundId) throws RemoteException;

    MatchDayIdTO createOrUpdateMatchDay(ContextTO context, SessionIdTO sessionId, MatchDayDataArgumentTO matchDayData) throws RemoteException;

    void deleteMatchDay(ContextTO context, SessionIdTO sessionId, MatchDayIdTO matchDayId) throws RemoteException;

    GameIdTO createOrUpdateGame(ContextTO context, SessionIdTO sessionId, GameDataArgumentTO gameData) throws RemoteException;

    void deleteGame(ContextTO context, SessionIdTO sessionId, GameIdTO gameId) throws RemoteException;
}
