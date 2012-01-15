package de.tipit.server.transfer.service;

import java.rmi.RemoteException;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import de.tipit.server.transfer.access.RulesAdmin;
import de.tipit.server.transfer.data.ContextTO;
import de.tipit.server.transfer.data.RuleBookDataTO;
import de.tipit.server.transfer.data.RuleBookIdTO;
import de.tipit.server.transfer.data.RuleBookNameTO;
import de.tipit.server.transfer.data.RuleBookSearchDataTO;
import de.tipit.server.transfer.data.RuleBookTO;
import de.tipit.server.transfer.data.SessionIdTO;

@WebService(portName = "RulesAdminPort", name = "RulesAdminService")
@SOAPBinding(style = Style.RPC)
public class RulesAdminService implements RulesAdmin {

    private final RulesAdmin delegate;

    public RulesAdminService(final RulesAdmin delegate) {
        this.delegate = delegate;
    }

    @WebResult(partName = "ruleBookNameList")
    @Override
    public RuleBookNameTO[] findRuleBooks(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "ruleBookSearchData") RuleBookSearchDataTO ruleBookSearchData) throws RemoteException {
        return delegate.findRuleBooks(context, sessionId, ruleBookSearchData);
    }

    @WebResult(partName = "ruleBookNameList")
    @Override
    public RuleBookNameTO[] getNotFinalRuleBooks(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId)
            throws RemoteException {
        return delegate.getNotFinalRuleBooks(context, sessionId);
    }

    @WebResult(partName = "ruleBookNameList")
    @Override
    public RuleBookNameTO[] getOwnRuleBooks(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId)
            throws RemoteException {
        return delegate.getOwnRuleBooks(context, sessionId);
    }

    @WebResult(partName = "ruleBook")
    @Override
    public RuleBookTO readRuleBook(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "ruleBookId") RuleBookIdTO ruleBookId) throws RemoteException {
        return delegate.readRuleBook(context, sessionId, ruleBookId);
    }

    @WebResult(partName = "int")
    @Override
    public Integer calculatePointsForStoredRuleBook(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "ruleBookId") RuleBookIdTO ruleBookId, @WebParam(partName = "homeResult") Integer homeResult,
            @WebParam(partName = "awayResult") Integer awayResult, @WebParam(partName = "averageHomeResultBet") Integer averageHomeResultBet,
            @WebParam(partName = "averageAwayResultBet") Integer averageAwayResultBet, @WebParam(partName = "homeResultBet") Integer homeResultBet,
            @WebParam(partName = "awayResultBet") Integer awayResultBet) throws RemoteException {
        return delegate.calculatePointsForStoredRuleBook(context, sessionId, ruleBookId, homeResult, awayResult, averageHomeResultBet, averageAwayResultBet,
                homeResultBet, awayResultBet);
    }

    @WebResult(partName = "int")
    @Override
    public Integer calculatePointsForNewRuleBook(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "ruleBookData") RuleBookDataTO ruleBookData, @WebParam(partName = "homeResult") Integer homeResult,
            @WebParam(partName = "awayResult") Integer awayResult, @WebParam(partName = "averageHomeResultBet") Integer averageHomeResultBet,
            @WebParam(partName = "averageAwayResultBet") Integer averageAwayResultBet, @WebParam(partName = "homeResultBet") Integer homeResultBet,
            @WebParam(partName = "awayResultBet") Integer awayResultBet) throws RemoteException {
        return delegate.calculatePointsForNewRuleBook(context, sessionId, ruleBookData, homeResult, awayResult, averageHomeResultBet, averageAwayResultBet,
                homeResultBet, awayResultBet);
    }

    @WebResult(partName = "newRuleBookId")
    @Override
    public RuleBookIdTO createOrUpdateRuleBook(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "ruleBookData") RuleBookDataTO ruleBookData) throws RemoteException {
        return delegate.createOrUpdateRuleBook(context, sessionId, ruleBookData);
    }

    @Override
    public void deleteRuleBook(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "ruleBookId") RuleBookIdTO ruleBookId) throws RemoteException {
        delegate.deleteRuleBook(context, sessionId, ruleBookId);
    }
}
