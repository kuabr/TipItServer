package de.tipit.server.transfer.access;

import java.rmi.RemoteException;

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

public interface Analysis {

    BetCommunityNameTO[] findBetCommunities(ContextTO context, SessionIdTO sessionId, BetCommunitySearchDataTO betCommunitySearchData) throws RemoteException;

    BetCommunityNameTO[] getParticipatingBetCommunities(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    BetCommunityTO readBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId) throws RemoteException;

    TournamentDescrTO[] findTournaments(ContextTO context, SessionIdTO sessionId, TournamentSearchDataTO tournSearchData) throws RemoteException;

    TournamentTO readTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    GameDataResultTO[] getGamesForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    GameWithPointsTO[] getGamesWithPointsByTournament(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, TournamentIdTO tournId,
            Boolean onlyGamesWithResult) throws RemoteException;

    GameWithPointsTO[] getGamesWithPointsByPeriod(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, PeriodTO period,
            Boolean onlyGamesWithResult) throws RemoteException;

    UserPointsTO[] getTournamentRankingByUser(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, TournamentIdTO tournId,
            Boolean onlyUsersWithBet) throws RemoteException;

    UserGroupPointsTO[] getTournamentRankingByUserGroup(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, TournamentIdTO tournId)
            throws RemoteException;

    UserPointsTO[] getTournamentRoundRankingByUser(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, TournamentRoundIdTO tournRoundId,
            Boolean onlyUsersWithBet) throws RemoteException;

    UserGroupPointsTO[] getTournamentRoundRankingByUserGroup(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId,
            TournamentRoundIdTO tournRoundId) throws RemoteException;

    UserPointsTO[] getMatchDayRankingByUser(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, MatchDayIdTO matchDayId,
            Boolean onlyUsersWithBet) throws RemoteException;

    UserGroupPointsTO[] getMatchDayRankingByUserGroup(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, MatchDayIdTO matchDayId)
            throws RemoteException;

    UserPointsTO[] getAllTimeRankingByUser(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, Boolean onlyUsersWithBet)
            throws RemoteException;

    UserGroupPointsTO[] getAllTimeRankingByUserGroup(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId) throws RemoteException;
}
