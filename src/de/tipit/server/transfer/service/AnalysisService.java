package de.tipit.server.transfer.service;

import java.rmi.RemoteException;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import de.tipit.server.transfer.access.Analysis;
import de.tipit.server.transfer.data.BetCommunityIdTO;
import de.tipit.server.transfer.data.BetCommunityNameTO;
import de.tipit.server.transfer.data.BetCommunitySearchDataTO;
import de.tipit.server.transfer.data.BetCommunityTO;
import de.tipit.server.transfer.data.ContextTO;
import de.tipit.server.transfer.data.GameDataResultTO;
import de.tipit.server.transfer.data.GameWithPointsTO;
import de.tipit.server.transfer.data.MatchDayIdTO;
import de.tipit.server.transfer.data.PeriodTO;
import de.tipit.server.transfer.data.SessionIdTO;
import de.tipit.server.transfer.data.TournamentDescrTO;
import de.tipit.server.transfer.data.TournamentIdTO;
import de.tipit.server.transfer.data.TournamentRoundIdTO;
import de.tipit.server.transfer.data.TournamentSearchDataTO;
import de.tipit.server.transfer.data.TournamentTO;
import de.tipit.server.transfer.data.UserGroupPointsTO;
import de.tipit.server.transfer.data.UserPointsTO;

@WebService(portName = "AnalysisPort", name = "AnalysisService")
@SOAPBinding(style = Style.RPC)
public class AnalysisService implements Analysis {

    private final Analysis delegate;

    public AnalysisService(final Analysis delegate) {
        this.delegate = delegate;
    }

    @WebResult(partName = "betCommunityNameList")
    @Override
    public BetCommunityNameTO[] findBetCommunities(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "betCommunitySearchData") BetCommunitySearchDataTO betCommunitySearchData) throws RemoteException {
        return delegate.findBetCommunities(context, sessionId, betCommunitySearchData);
    }

    @WebResult(partName = "betCommunityNameList")
    @Override
    public BetCommunityNameTO[] getParticipatingBetCommunities(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId) throws RemoteException {
        return delegate.getParticipatingBetCommunities(context, sessionId);
    }

    @WebResult(partName = "betCommunity")
    @Override
    public BetCommunityTO readBetCommunity(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId) throws RemoteException {
        return delegate.readBetCommunity(context, sessionId, betCommunityId);
    }

    @WebResult(partName = "tournDescrList")
    @Override
    public TournamentDescrTO[] findTournaments(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournSearchData") TournamentSearchDataTO tournSearchData) throws RemoteException {
        return delegate.findTournaments(context, sessionId, tournSearchData);
    }

    @WebResult(partName = "tourn")
    @Override
    public TournamentTO readTournament(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.readTournament(context, sessionId, tournId);
    }

    @WebResult(partName = "gameDataList")
    @Override
    public GameDataResultTO[] getGamesForTournament(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.getGamesForTournament(context, sessionId, tournId);
    }

    @WebResult(partName = "gameWithPointsList")
    @Override
    public GameWithPointsTO[] getGamesWithPointsByTournament(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId,
            @WebParam(partName = "tournId") TournamentIdTO tournId, @WebParam(partName = "onlyGamesWithResult") Boolean onlyGamesWithResult)
            throws RemoteException {
        return delegate.getGamesWithPointsByTournament(context, sessionId, betCommunityId, tournId, onlyGamesWithResult);
    }

    @WebResult(partName = "gameWithPointsList")
    @Override
    public GameWithPointsTO[] getGamesWithPointsByPeriod(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId,
            @WebParam(partName = "period") PeriodTO period, @WebParam(partName = "onlyGamesWithResult") Boolean onlyGamesWithResult) throws RemoteException {
        return delegate.getGamesWithPointsByPeriod(context, sessionId, betCommunityId, period, onlyGamesWithResult);
    }

    @WebResult(partName = "userPointsList")
    @Override
    public UserPointsTO[] getTournamentRankingByUser(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId,
            @WebParam(partName = "tournId") TournamentIdTO tournId, @WebParam(partName = "onlyUsersWithBet") Boolean onlyUsersWithBet) throws RemoteException {
        return delegate.getTournamentRankingByUser(context, sessionId, betCommunityId, tournId, onlyUsersWithBet);
    }

    @WebResult(partName = "userGroupPointsList")
    @Override
    public UserGroupPointsTO[] getTournamentRankingByUserGroup(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId,
            @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.getTournamentRankingByUserGroup(context, sessionId, betCommunityId, tournId);
    }

    @WebResult(partName = "userPointsList")
    @Override
    public UserPointsTO[] getTournamentRoundRankingByUser(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId,
            @WebParam(partName = "tournRoundId") TournamentRoundIdTO tournRoundId, @WebParam(partName = "onlyUsersWithBet") Boolean onlyUsersWithBet)
            throws RemoteException {
        return delegate.getTournamentRoundRankingByUser(context, sessionId, betCommunityId, tournRoundId, onlyUsersWithBet);
    }

    @WebResult(partName = "userGroupPointsList")
    @Override
    public UserGroupPointsTO[] getTournamentRoundRankingByUserGroup(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId,
            @WebParam(partName = "tournRoundId") TournamentRoundIdTO tournRoundId) throws RemoteException {
        return delegate.getTournamentRoundRankingByUserGroup(context, sessionId, betCommunityId, tournRoundId);
    }

    @WebResult(partName = "userPointsList")
    @Override
    public UserPointsTO[] getMatchDayRankingByUser(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId, @WebParam(partName = "matchDayId") MatchDayIdTO matchDayId,
            @WebParam(partName = "onlyUsersWithBet") Boolean onlyUsersWithBet) throws RemoteException {
        return delegate.getMatchDayRankingByUser(context, sessionId, betCommunityId, matchDayId, onlyUsersWithBet);
    }

    @WebResult(partName = "userGroupPointsList")
    @Override
    public UserGroupPointsTO[] getMatchDayRankingByUserGroup(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId,
            @WebParam(partName = "matchDayId") MatchDayIdTO matchDayId) throws RemoteException {
        return delegate.getMatchDayRankingByUserGroup(context, sessionId, betCommunityId, matchDayId);
    }

    @WebResult(partName = "userPointsList")
    @Override
    public UserPointsTO[] getAllTimeRankingByUser(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId, @WebParam(partName = "onlyUsersWithBet") Boolean onlyUsersWithBet)
            throws RemoteException {
        return delegate.getAllTimeRankingByUser(context, sessionId, betCommunityId, onlyUsersWithBet);
    }

    @WebResult(partName = "userGroupPoints")
    @Override
    public UserGroupPointsTO[] getAllTimeRankingByUserGroup(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId)
            throws RemoteException {
        return delegate.getAllTimeRankingByUserGroup(context, sessionId, betCommunityId);
    }
}
