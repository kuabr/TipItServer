package de.tipit.server.transfer.service;

import java.rmi.RemoteException;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import de.tipit.server.transfer.access.MetaDataAdmin;
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

@WebService(portName = "MetaDataAdminPort", name = "MetaDataAdminService")
@SOAPBinding(style = Style.RPC)
public class MetaDataAdminService implements MetaDataAdmin {

    private final MetaDataAdmin delegate;

    public MetaDataAdminService(final MetaDataAdmin delegate) {
        this.delegate = delegate;
    }

    @WebResult(partName = "sportNameList")
    @Override
    public SportNameTO[] getSports(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId)
            throws RemoteException {
        return delegate.getSports(context, sessionId);
    }

    @WebResult(partName = "tournTypeNameList")
    @Override
    public TournamentTypeNameTO[] findTournamentTypes(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "tournTypeSearchData") TournamentTypeSearchDataTO tournTypeSearchData)
            throws RemoteException {
        return delegate.findTournamentTypes(context, sessionId, tournTypeSearchData);
    }

    @WebResult(partName = "teamNameList")
    @Override
    public TeamNameTO[] findTeams(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "teamSearchData") TeamSearchDataTO teamSearchData) throws RemoteException {
        return delegate.findTeams(context, sessionId, teamSearchData);
    }

    @WebResult(partName = "tournTypeNameList")
    @Override
    public TournamentTypeNameTO[] getTournamentTypesForTeams(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "teamIdList") TeamIdTO[] teamIdList) throws RemoteException {
        return delegate.getTournamentTypesForTeams(context, sessionId, teamIdList);
    }

    @WebResult(partName = "teamNameList")
    @Override
    public TeamNameTO[] getTeamsForTournamentTypes(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournTypeIdList") TournamentTypeIdTO[] tournTypeIdList) throws RemoteException {
        return delegate.getTeamsForTournamentTypes(context, sessionId, tournTypeIdList);
    }

    @WebResult(partName = "sport")
    @Override
    public SportTO readSport(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "sportId") SportIdTO sportId) throws RemoteException {
        return delegate.readSport(context, sessionId, sportId);
    }

    @WebResult(partName = "tournType")
    @Override
    public TournamentTypeTO readTournamentType(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournTypeId") TournamentTypeIdTO tournTypeId) throws RemoteException {
        return delegate.readTournamentType(context, sessionId, tournTypeId);
    }

    @WebResult(partName = "team")
    @Override
    public TeamTO readTeam(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "teamId") TeamIdTO teamId) throws RemoteException {
        return delegate.readTeam(context, sessionId, teamId);
    }

    @WebResult(partName = "newSportId")
    @Override
    public SportIdTO createOrUpdateSport(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "sportData") SportDataTO sportData) throws RemoteException {
        return delegate.createOrUpdateSport(context, sessionId, sportData);
    }

    @Override
    public void deleteSport(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "sportId") SportIdTO sportId) throws RemoteException {
        delegate.deleteSport(context, sessionId, sportId);
    }

    @WebResult(partName = "newTournamentTypeId")
    @Override
    public TournamentTypeIdTO createOrUpdateTournamentType(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "tournTypeData") TournamentTypeDataArgumentTO tournTypeData)
            throws RemoteException {
        return delegate.createOrUpdateTournamentType(context, sessionId, tournTypeData);
    }

    @Override
    public void deleteTournamentType(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournTypeId") TournamentTypeIdTO tournTypeId) throws RemoteException {
        delegate.deleteTournamentType(context, sessionId, tournTypeId);
    }

    @WebResult(partName = "newTeamId")
    @Override
    public TeamIdTO createOrUpdateTeam(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "teamData") TeamDataTO teamData) throws RemoteException {
        return delegate.createOrUpdateTeam(context, sessionId, teamData);
    }

    @Override
    public void deleteTeam(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "teamId") TeamIdTO teamId) throws RemoteException {
        delegate.deleteTeam(context, sessionId, teamId);
    }

    @Override
    public void addAllowedTournamentTypesToTeam(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "teamId") TeamIdTO teamId, @WebParam(partName = "tournTypeIdList") TournamentTypeIdTO[] tournTypeIdList)
            throws RemoteException {
        delegate.addAllowedTournamentTypesToTeam(context, sessionId, teamId, tournTypeIdList);
    }

    @Override
    public void removeAllowedTournamentTypesFromTeam(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "teamId") TeamIdTO teamId,
            @WebParam(partName = "tournTypeIdList") TournamentTypeIdTO[] tournTypeIdList) throws RemoteException {
        delegate.removeAllowedTournamentTypesFromTeam(context, sessionId, teamId, tournTypeIdList);
    }

    @Override
    public void addAllowedTeamsToTournamentType(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournTypeId") TournamentTypeIdTO tournTypeId, @WebParam(partName = "teamIdList") TeamIdTO[] teamIdList)
            throws RemoteException {
        delegate.addAllowedTeamsToTournamentType(context, sessionId, tournTypeId, teamIdList);
    }

    @Override
    public void removeAllowedTeamsFromTournamentType(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "tournTypeId") TournamentTypeIdTO tournTypeId,
            @WebParam(partName = "teamIdList") TeamIdTO[] teamIdList) throws RemoteException {
        delegate.removeAllowedTeamsFromTournamentType(context, sessionId, tournTypeId, teamIdList);
    }
}
