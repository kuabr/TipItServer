package de.tipit.server.transfer.access;

import java.rmi.RemoteException;

import de.tipit.server.transfer.data.ContextTO;
import de.tipit.server.transfer.data.RuleBookDataTO;
import de.tipit.server.transfer.data.RuleBookIdTO;
import de.tipit.server.transfer.data.RuleBookNameTO;
import de.tipit.server.transfer.data.RuleBookSearchDataTO;
import de.tipit.server.transfer.data.RuleBookTO;
import de.tipit.server.transfer.data.SessionIdTO;

public interface RulesAdmin {

    RuleBookNameTO[] findRuleBooks(ContextTO context, SessionIdTO sessionId, RuleBookSearchDataTO ruleBookSearchData) throws RemoteException;

    RuleBookNameTO[] getNotFinalRuleBooks(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    RuleBookNameTO[] getOwnRuleBooks(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    RuleBookTO readRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookIdTO ruleBookId) throws RemoteException;

    Integer calculatePointsForStoredRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookIdTO ruleBookId, Integer homeResult, Integer awayResult,
            Integer averageHomeResultBet, Integer averageAwayResultBet, Integer homeResultBet, Integer awayResultBet) throws RemoteException;

    Integer calculatePointsForNewRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookDataTO ruleBookData, Integer homeResult, Integer awayResult,
            Integer averageHomeResultBet, Integer averageAwayResultBet, Integer homeResultBet, Integer awayResultBet) throws RemoteException;

    RuleBookIdTO createOrUpdateRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookDataTO ruleBookData) throws RemoteException;

    void deleteRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookIdTO ruleBookId) throws RemoteException;
}
