package de.tipit.server.transfer.access;

import java.rmi.RemoteException;

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
import de.tipit.server.transfer.data.PeriodTO;
import de.tipit.server.transfer.data.SessionIdTO;
import de.tipit.server.transfer.data.TeamIdTO;
import de.tipit.server.transfer.data.TeamNameTO;
import de.tipit.server.transfer.data.TournamentDescrTO;
import de.tipit.server.transfer.data.TournamentIdTO;
import de.tipit.server.transfer.data.TournamentTO;
import de.tipit.server.transfer.data.WinnerBetDataArgumentTO;
import de.tipit.server.transfer.data.WinnerBetIdTO;
import de.tipit.server.transfer.data.WinnerBetTO;

public interface BetResult {

    TournamentDescrTO[] getOpenParticipatingTournaments(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    TournamentDescrTO[] getAllOpenTournaments(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    TournamentTO readTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    Boolean hasMissingWinnerBetForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    WinnerBetTO[] getWinnerBetsForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    TeamNameTO[] getTeamsForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    GameDataResultTO[] getGamesWithMissingBetForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    GameTO[] getGamesWithPopulatedBetForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    GameTO[] getGamesWithForgottenBetForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    GameDataResultTO[] getGamesWithMissingResultForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    GameTO[] getGamesWithPopulatedResultForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    GameDataResultTO[] getGamesWithMissingBetForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws RemoteException;

    GameTO[] getGamesWithPopulatedBetForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws RemoteException;

    GameTO[] getGamesWithForgottenBetForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws RemoteException;

    GameDataResultTO[] getGamesWithMissingResultForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws RemoteException;

    GameTO[] getGamesWithPopulatedResultForPeriod(ContextTO context, SessionIdTO sessionId, PeriodTO period) throws RemoteException;

    void setWinnerBet(ContextTO context, SessionIdTO sessionId, WinnerBetDataArgumentTO winnerBetData) throws RemoteException;

    void setGameBet(ContextTO context, SessionIdTO sessionId, GameBetDataWithoutGameTO gameBetData, GameIdTO gameId) throws RemoteException;

    void setWinnerTeam(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId, TeamIdTO winnerTeamId) throws RemoteException;

    void delWinnerTeam(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    void setGameResult(ContextTO context, SessionIdTO sessionId, GameResultDataTO gameResultData) throws RemoteException;

    void delGameResult(ContextTO context, SessionIdTO sessionId, GameIdTO gameId) throws RemoteException;

    CommentIdTO createOrUpdateCommentForWinnerBet(ContextTO context, SessionIdTO sessionId, WinnerBetIdTO winnerBetId, CommentDataTO commentData)
            throws RemoteException;

    CommentIdTO createOrUpdateCommentForWinnerTeam(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId, CommentDataTO commentData)
            throws RemoteException;

    CommentIdTO createOrUpdateCommentForGameBet(ContextTO context, SessionIdTO sessionId, GameBetIdTO gameBetId, CommentDataTO commentData)
            throws RemoteException;

    CommentIdTO createOrUpdateCommentForGameResult(ContextTO context, SessionIdTO sessionId, GameIdTO gameId, CommentDataTO commentData) throws RemoteException;

    void removeCommentForWinnerBet(ContextTO context, SessionIdTO sessionId, CommentIdTO commentId) throws RemoteException;

    void removeCommentForWinnerTeam(ContextTO context, SessionIdTO sessionId, CommentIdTO commentId) throws RemoteException;

    void removeCommentForGameBet(ContextTO context, SessionIdTO sessionId, CommentIdTO commentId) throws RemoteException;

    void removeCommentForGameResult(ContextTO context, SessionIdTO sessionId, CommentIdTO commentId) throws RemoteException;

    GameDataResultTO[] getGamesWithMissingTeamsForTournament(ContextTO context, SessionIdTO sessionId, TournamentIdTO tournId) throws RemoteException;

    void updateGame(ContextTO context, SessionIdTO sessionId, GameDataArgumentTO gameData) throws RemoteException;
}
