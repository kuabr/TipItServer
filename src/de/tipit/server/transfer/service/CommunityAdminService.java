package de.tipit.server.transfer.service;

import java.rmi.RemoteException;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import de.tipit.server.transfer.access.CommunityAdmin;
import de.tipit.server.transfer.data.BetCommunityDataArgumentTO;
import de.tipit.server.transfer.data.BetCommunityIdTO;
import de.tipit.server.transfer.data.BetCommunityNameTO;
import de.tipit.server.transfer.data.BetCommunitySearchDataTO;
import de.tipit.server.transfer.data.BetCommunityTO;
import de.tipit.server.transfer.data.ContextTO;
import de.tipit.server.transfer.data.RuleBookIdTO;
import de.tipit.server.transfer.data.RuleBookNameTO;
import de.tipit.server.transfer.data.RuleBookTO;
import de.tipit.server.transfer.data.SessionIdTO;
import de.tipit.server.transfer.data.TournamentIdTO;
import de.tipit.server.transfer.data.UserGroupDataTO;
import de.tipit.server.transfer.data.UserGroupIdTO;
import de.tipit.server.transfer.data.UserGroupNameTO;
import de.tipit.server.transfer.data.UserGroupSearchDataTO;
import de.tipit.server.transfer.data.UserGroupTO;
import de.tipit.server.transfer.data.UserIdTO;

@WebService(portName = "CommunityAdminPort", name = "CommunityAdminService")
@SOAPBinding(style = Style.RPC)
public class CommunityAdminService implements CommunityAdmin {

    private final CommunityAdmin delegate;

    public CommunityAdminService(final CommunityAdmin delegate) {
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
    public BetCommunityNameTO[] getBetCommunitiesForUserGroup(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "userGroupId") UserGroupIdTO userGroupId) throws RemoteException {
        return delegate.getBetCommunitiesForUserGroup(context, sessionId, userGroupId);
    }

    @WebResult(partName = "betCommunityNameList")
    @Override
    public BetCommunityNameTO[] getBetCommunitiesForUser(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "userId") UserIdTO userId) throws RemoteException {
        return delegate.getBetCommunitiesForUser(context, sessionId, userId);
    }

    @WebResult(partName = "betCommunityNameList")
    @Override
    public BetCommunityNameTO[] getParticipatingBetCommunities(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId) throws RemoteException {
        return delegate.getParticipatingBetCommunities(context, sessionId);
    }

    @WebResult(partName = "betCommunityNameList")
    @Override
    public BetCommunityNameTO[] getModeratingBetCommunities(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId) throws RemoteException {
        return delegate.getModeratingBetCommunities(context, sessionId);
    }

    @WebResult(partName = "bool")
    @Override
    public Boolean isBetCommunityModerator(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId) throws RemoteException {
        return delegate.isBetCommunityModerator(context, sessionId, betCommunityId);
    }

    @WebResult(partName = "userGroupNameList")
    @Override
    public UserGroupNameTO[] findUserGroups(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userGroupSearchData") UserGroupSearchDataTO userGroupSearchData) throws RemoteException {
        return delegate.findUserGroups(context, sessionId, userGroupSearchData);
    }

    @WebResult(partName = "userGroupNameList")
    @Override
    public UserGroupNameTO[] getUserGroupsForUser(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userId") UserIdTO userId) throws RemoteException {
        return delegate.getUserGroupsForUser(context, sessionId, userId);
    }

    @WebResult(partName = "userGroupNameList")
    @Override
    public UserGroupNameTO[] getParticipatingUserGroups(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId) throws RemoteException {
        return delegate.getParticipatingUserGroups(context, sessionId);
    }

    @WebResult(partName = "userGroupNameList")
    @Override
    public UserGroupNameTO[] getModeratingUserGroups(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId)
            throws RemoteException {
        return delegate.getModeratingUserGroups(context, sessionId);
    }

    @WebResult(partName = "bool")
    @Override
    public Boolean isUserGroupModerator(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userGroupId") UserGroupIdTO userGroupId) throws RemoteException {
        return delegate.isUserGroupModerator(context, sessionId, userGroupId);
    }

    @WebResult(partName = "ruleBookNameList")
    @Override
    public RuleBookNameTO[] getAllFinalRuleBooks(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId)
            throws RemoteException {
        return delegate.getAllFinalRuleBooks(context, sessionId);
    }

    @WebResult(partName = "betCommunity")
    @Override
    public BetCommunityTO readBetCommunity(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId) throws RemoteException {
        return delegate.readBetCommunity(context, sessionId, betCommunityId);
    }

    @WebResult(partName = "userGroup")
    @Override
    public UserGroupTO readUserGroup(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userGroupId") UserGroupIdTO userGroupId) throws RemoteException {
        return delegate.readUserGroup(context, sessionId, userGroupId);
    }

    @WebResult(partName = "ruleBook")
    @Override
    public RuleBookTO readRuleBook(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "ruleBookId") RuleBookIdTO ruleBookId) throws RemoteException {
        return delegate.readRuleBook(context, sessionId, ruleBookId);
    }

    @WebResult(partName = "newBetCommunityId")
    @Override
    public BetCommunityIdTO createOrUpdateBetCommunity(@WebParam(partName = "context") ContextTO context,
            @WebParam(partName = "sessionId") SessionIdTO sessionId, @WebParam(partName = "betCommunityData") BetCommunityDataArgumentTO betCommunityData)
            throws RemoteException {
        return delegate.createOrUpdateBetCommunity(context, sessionId, betCommunityData);
    }

    @Override
    public void deleteBetCommunity(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId) throws RemoteException {
        delegate.deleteBetCommunity(context, sessionId, betCommunityId);
    }

    @Override
    public void addUserGroupToBetCommunity(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId, @WebParam(partName = "userGroupId") UserGroupIdTO userGroupId)
            throws RemoteException {
        delegate.addUserGroupToBetCommunity(context, sessionId, betCommunityId, userGroupId);
    }

    @Override
    public void removeUserGroupFromBetCommunity(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId, @WebParam(partName = "userGroupId") UserGroupIdTO userGroupId)
            throws RemoteException {
        delegate.removeUserGroupFromBetCommunity(context, sessionId, betCommunityId, userGroupId);
    }

    @Override
    public void addTournamentToBetCommunity(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId, @WebParam(partName = "tournId") TournamentIdTO tournId)
            throws RemoteException {
        delegate.addTournamentToBetCommunity(context, sessionId, betCommunityId, tournId);
    }

    @Override
    public void removeTournamentFromBetCommunity(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId, @WebParam(partName = "tournId") TournamentIdTO tournId)
            throws RemoteException {
        delegate.removeTournamentFromBetCommunity(context, sessionId, betCommunityId, tournId);
    }

    @Override
    public void addModeratorToBetCommunity(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId, @WebParam(partName = "modId") UserIdTO modId) throws RemoteException {
        delegate.addModeratorToBetCommunity(context, sessionId, betCommunityId, modId);
    }

    @Override
    public void removeModeratorFromBetCommunity(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "betCommunityId") BetCommunityIdTO betCommunityId, @WebParam(partName = "modId") UserIdTO modId) throws RemoteException {
        delegate.removeModeratorFromBetCommunity(context, sessionId, betCommunityId, modId);
    }

    @WebResult(partName = "newUserGroupId")
    @Override
    public UserGroupIdTO createOrUpdateUserGroup(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userGroupData") UserGroupDataTO userGroupData) throws RemoteException {
        return delegate.createOrUpdateUserGroup(context, sessionId, userGroupData);
    }

    @Override
    public void deleteUserGroup(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userGroupId") UserGroupIdTO userGroupId) throws RemoteException {
        delegate.deleteUserGroup(context, sessionId, userGroupId);
    }

    @Override
    public void addUserToUserGroup(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userGroupId") UserGroupIdTO userGroupId, @WebParam(partName = "userId") UserIdTO userId) throws RemoteException {
        delegate.addUserToUserGroup(context, sessionId, userGroupId, userId);
    }

    @Override
    public void removeUserFromUserGroup(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userGroupId") UserGroupIdTO userGroupId, @WebParam(partName = "userId") UserIdTO userId) throws RemoteException {
        delegate.removeUserFromUserGroup(context, sessionId, userGroupId, userId);
    }

    @Override
    public void joinUserGroup(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userGroupId") UserGroupIdTO userGroupId) throws RemoteException {
        delegate.joinUserGroup(context, sessionId, userGroupId);
    }

    @Override
    public void leaveUserGroup(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userGroupId") UserGroupIdTO userGroupId) throws RemoteException {
        delegate.leaveUserGroup(context, sessionId, userGroupId);
    }

    @Override
    public void addModeratorToUserGroup(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userGroupId") UserGroupIdTO userGroupId, @WebParam(partName = "modId") UserIdTO modId) throws RemoteException {
        delegate.addModeratorToUserGroup(context, sessionId, userGroupId, modId);
    }

    @Override
    public void removeModeratorFromUserGroup(@WebParam(partName = "context") ContextTO context, @WebParam(partName = "sessionId") SessionIdTO sessionId,
            @WebParam(partName = "userGroupId") UserGroupIdTO userGroupId, @WebParam(partName = "modId") UserIdTO modId) throws RemoteException {
        delegate.removeModeratorFromUserGroup(context, sessionId, userGroupId, modId);
    }
}
