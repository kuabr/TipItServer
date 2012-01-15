package de.tipit.server.transfer.access;

import java.rmi.RemoteException;

import de.tipit.server.transfer.data.ContextTO;
import de.tipit.server.transfer.data.SessionIdTO;
import de.tipit.server.transfer.data.SportDataTO;
import de.tipit.server.transfer.data.SportIdTO;
import de.tipit.server.transfer.data.SportNameTO;
import de.tipit.server.transfer.data.SportTO;
import de.tipit.server.transfer.data.TeamDataTO;
import de.tipit.server.transfer.data.TeamIdTO;
import de.tipit.server.transfer.data.TeamNameTO;
import de.tipit.server.transfer.data.TeamSearchDataTO;
import de.tipit.server.transfer.data.TeamTO;
import de.tipit.server.transfer.data.TournamentTypeDataArgumentTO;
import de.tipit.server.transfer.data.TournamentTypeIdTO;
import de.tipit.server.transfer.data.TournamentTypeNameTO;
import de.tipit.server.transfer.data.TournamentTypeSearchDataTO;
import de.tipit.server.transfer.data.TournamentTypeTO;

public interface MetaDataAdmin {

    SportNameTO[] getSports(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    TournamentTypeNameTO[] findTournamentTypes(ContextTO context, SessionIdTO sessionId, TournamentTypeSearchDataTO tournTypeSearchData) throws RemoteException;

    TeamNameTO[] findTeams(ContextTO context, SessionIdTO sessionId, TeamSearchDataTO teamSearchData) throws RemoteException;

    TournamentTypeNameTO[] getTournamentTypesForTeams(ContextTO context, SessionIdTO sessionId, TeamIdTO[] teamIdList) throws RemoteException;

    TeamNameTO[] getTeamsForTournamentTypes(ContextTO context, SessionIdTO sessionId, TournamentTypeIdTO[] tournTypeIdList) throws RemoteException;

    SportTO readSport(ContextTO context, SessionIdTO sessionId, SportIdTO sportId) throws RemoteException;

    TournamentTypeTO readTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeIdTO tournTypeId) throws RemoteException;

    TeamTO readTeam(ContextTO context, SessionIdTO sessionId, TeamIdTO teamId) throws RemoteException;

    SportIdTO createOrUpdateSport(ContextTO context, SessionIdTO sessionId, SportDataTO sportData) throws RemoteException;

    void deleteSport(ContextTO context, SessionIdTO sessionId, SportIdTO sportId) throws RemoteException;

    TournamentTypeIdTO createOrUpdateTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeDataArgumentTO tournTypeData)
            throws RemoteException;

    void deleteTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeIdTO tournTypeId) throws RemoteException;

    TeamIdTO createOrUpdateTeam(ContextTO context, SessionIdTO sessionId, TeamDataTO teamData) throws RemoteException;

    void deleteTeam(ContextTO context, SessionIdTO sessionId, TeamIdTO teamId) throws RemoteException;

    void addAllowedTournamentTypesToTeam(ContextTO context, SessionIdTO sessionId, TeamIdTO teamId, TournamentTypeIdTO[] tournTypeIdList)
            throws RemoteException;

    void removeAllowedTournamentTypesFromTeam(ContextTO context, SessionIdTO sessionId, TeamIdTO teamId, TournamentTypeIdTO[] tournTypeIdList)
            throws RemoteException;

    void addAllowedTeamsToTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeIdTO tournTypeId, TeamIdTO[] teamIdList)
            throws RemoteException;

    void removeAllowedTeamsFromTournamentType(ContextTO context, SessionIdTO sessionId, TournamentTypeIdTO tournTypeId, TeamIdTO[] teamIdList)
            throws RemoteException;
}
