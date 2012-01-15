package de.tipit.server.transfer.service;

import java.rmi.RemoteException;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import de.tipit.server.transfer.access.BetResult;
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

@WebService(portName = "BetResultPort", name = "BetResultService")
@SOAPBinding(style = Style.RPC)
public class BetResultService implements BetResult {

    private final BetResult delegate;

    public BetResultService(final BetResult delegate) {
        this.delegate = delegate;
    }

    @WebResult(partName = "tournDescrList")
    @Override
    public TournamentDescrTO[] getOpenParticipatingTournaments(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId) throws RemoteException {
        return delegate.getOpenParticipatingTournaments(context, sessionId);
    }

    @WebResult(partName = "tournDescrList")
    @Override
    public TournamentDescrTO[] getAllOpenTournaments(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId)
            throws RemoteException {
        return delegate.getAllOpenTournaments(context, sessionId);
    }

    @WebResult(partName = "tourn")
    @Override
    public TournamentTO readTournament(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.readTournament(context, sessionId, tournId);
    }

    @WebResult(partName = "bool")
    @Override
    public Boolean hasMissingWinnerBetForTournament(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.hasMissingWinnerBetForTournament(context, sessionId, tournId);
    }

    @WebResult(partName = "winnerBetList")
    @Override
    public WinnerBetTO[] getWinnerBetsForTournament(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.getWinnerBetsForTournament(context, sessionId, tournId);
    }

    @WebResult(partName = "teamNameList")
    @Override
    public TeamNameTO[] getTeamsForTournament(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.getTeamsForTournament(context, sessionId, tournId);
    }

    @WebResult(partName = "gameDataList")
    @Override
    public GameDataResultTO[] getGamesWithMissingBetForTournament(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.getGamesWithMissingBetForTournament(context, sessionId, tournId);
    }

    @WebResult(partName = "gameList")
    @Override
    public GameTO[] getGamesWithPopulatedBetForTournament(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.getGamesWithPopulatedBetForTournament(context, sessionId, tournId);
    }

    @WebResult(partName = "gameList")
    @Override
    public GameTO[] getGamesWithForgottenBetForTournament(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.getGamesWithForgottenBetForTournament(context, sessionId, tournId);
    }

    @WebResult(partName = "gameDataList")
    @Override
    public GameDataResultTO[] getGamesWithMissingResultForTournament(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.getGamesWithMissingResultForTournament(context, sessionId, tournId);
    }

    @WebResult(partName = "gameList")
    @Override
    public GameTO[] getGamesWithPopulatedResultForTournament(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.getGamesWithPopulatedResultForTournament(context, sessionId, tournId);
    }

    @WebResult(partName = "gameDataList")
    @Override
    public GameDataResultTO[] getGamesWithMissingBetForPeriod(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "period") PeriodTO period) throws RemoteException {
        return delegate.getGamesWithMissingBetForPeriod(context, sessionId, period);
    }

    @WebResult(partName = "gameList")
    @Override
    public GameTO[] getGamesWithPopulatedBetForPeriod(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "period") PeriodTO period) throws RemoteException {
        return delegate.getGamesWithPopulatedBetForPeriod(context, sessionId, period);
    }

    @WebResult(partName = "gameList")
    @Override
    public GameTO[] getGamesWithForgottenBetForPeriod(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "period") PeriodTO period) throws RemoteException {
        return delegate.getGamesWithForgottenBetForPeriod(context, sessionId, period);
    }

    @WebResult(partName = "gameDataList")
    @Override
    public GameDataResultTO[] getGamesWithMissingResultForPeriod(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "period") PeriodTO period) throws RemoteException {
        return delegate.getGamesWithMissingResultForPeriod(context, sessionId, period);
    }

    @WebResult(partName = "gameList")
    @Override
    public GameTO[] getGamesWithPopulatedResultForPeriod(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "period") PeriodTO period) throws RemoteException {
        return delegate.getGamesWithPopulatedResultForPeriod(context, sessionId, period);
    }

    @Override
    public void setWinnerBet(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "winnerBetData") WinnerBetDataArgumentTO winnerBetData) throws RemoteException {
        delegate.setWinnerBet(context, sessionId, winnerBetData);
    }

    @Override
    public void setGameBet(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "gameBetData") GameBetDataWithoutGameTO gameBetData, @WebParam(partName = "gameId") GameIdTO gameId) throws RemoteException {
        delegate.setGameBet(context, sessionId, gameBetData, gameId);
    }

    @Override
    public void setWinnerTeam(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournId") TournamentIdTO tournId, @WebParam(partName = "winnerTeamId") TeamIdTO winnerTeamId) throws RemoteException {
        delegate.setWinnerTeam(context, sessionId, tournId, winnerTeamId);
    }

    @Override
    public void delWinnerTeam(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        delegate.delWinnerTeam(context, sessionId, tournId);
    }

    @Override
    public void setGameResult(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "gameResultData") GameResultDataTO gameResultData) throws RemoteException {
        delegate.setGameResult(context, sessionId, gameResultData);
    }

    @Override
    public void delGameResult(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "gameId") GameIdTO gameId) throws RemoteException {
        delegate.delGameResult(context, sessionId, gameId);
    }

    @WebResult(partName = "newCommentId")
    @Override
    public CommentIdTO createOrUpdateCommentForWinnerBet(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "winnerBetId") WinnerBetIdTO winnerBetId,
            @WebParam(partName = "commentData") CommentDataTO commentData) throws RemoteException {
        return delegate.createOrUpdateCommentForWinnerBet(context, sessionId, winnerBetId, commentData);
    }

    @WebResult(partName = "newCommentId")
    @Override
    public CommentIdTO createOrUpdateCommentForWinnerTeam(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "tournId") TournamentIdTO tournId,
            @WebParam(partName = "commentData") CommentDataTO commentData) throws RemoteException {
        return delegate.createOrUpdateCommentForWinnerTeam(context, sessionId, tournId, commentData);
    }

    @WebResult(partName = "newCommentId")
    @Override
    public CommentIdTO createOrUpdateCommentForGameBet(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "gameBetId") GameBetIdTO gameBetId,
            @WebParam(partName = "commentData") CommentDataTO commentData) throws RemoteException {
        return delegate.createOrUpdateCommentForGameBet(context, sessionId, gameBetId, commentData);
    }

    @WebResult(partName = "newCommentId")
    @Override
    public CommentIdTO createOrUpdateCommentForGameResult(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "gameId") GameIdTO gameId,
            @WebParam(partName = "commentData") CommentDataTO commentData) throws RemoteException {
        return delegate.createOrUpdateCommentForGameResult(context, sessionId, gameId, commentData);
    }

    @Override
    public void removeCommentForWinnerBet(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "commentId") CommentIdTO commentId) throws RemoteException {
        delegate.removeCommentForWinnerBet(context, sessionId, commentId);
    }

    @Override
    public void removeCommentForWinnerTeam(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "commentId") CommentIdTO commentId) throws RemoteException {
        delegate.removeCommentForWinnerTeam(context, sessionId, commentId);
    }

    @Override
    public void removeCommentForGameBet(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "commentId") CommentIdTO commentId) throws RemoteException {
        delegate.removeCommentForGameBet(context, sessionId, commentId);
    }

    @Override
    public void removeCommentForGameResult(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "commentId") CommentIdTO commentId) throws RemoteException {
        delegate.removeCommentForGameResult(context, sessionId, commentId);
    }

    @WebResult(partName = "gameDataList")
    @Override
    public GameDataResultTO[] getGamesWithMissingTeamsForTournament(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "tournId") TournamentIdTO tournId) throws RemoteException {
        return delegate.getGamesWithMissingTeamsForTournament(context, sessionId, tournId);
    }

    @Override
    public void updateGame(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "gameData") GameDataArgumentTO gameData) throws RemoteException {
        delegate.updateGame(context, sessionId, gameData);
    }
}
