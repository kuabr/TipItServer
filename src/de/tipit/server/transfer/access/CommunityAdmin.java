package de.tipit.server.transfer.access;

import java.rmi.RemoteException;

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

public interface CommunityAdmin {

    BetCommunityNameTO[] findBetCommunities(ContextTO context, SessionIdTO sessionId, BetCommunitySearchDataTO betCommunitySearchData) throws RemoteException;

    BetCommunityNameTO[] getBetCommunitiesForUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws RemoteException;

    BetCommunityNameTO[] getBetCommunitiesForUser(ContextTO context, SessionIdTO sessionId, UserIdTO userId) throws RemoteException;

    BetCommunityNameTO[] getParticipatingBetCommunities(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    BetCommunityNameTO[] getModeratingBetCommunities(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    Boolean isBetCommunityModerator(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId) throws RemoteException;

    UserGroupNameTO[] findUserGroups(ContextTO context, SessionIdTO sessionId, UserGroupSearchDataTO userGroupSearchData) throws RemoteException;

    UserGroupNameTO[] getUserGroupsForUser(ContextTO context, SessionIdTO sessionId, UserIdTO userId) throws RemoteException;

    UserGroupNameTO[] getParticipatingUserGroups(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    UserGroupNameTO[] getModeratingUserGroups(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    Boolean isUserGroupModerator(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws RemoteException;

    RuleBookNameTO[] getAllFinalRuleBooks(ContextTO context, SessionIdTO sessionId) throws RemoteException;

    BetCommunityTO readBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId) throws RemoteException;

    UserGroupTO readUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws RemoteException;

    RuleBookTO readRuleBook(ContextTO context, SessionIdTO sessionId, RuleBookIdTO ruleBookId) throws RemoteException;

    BetCommunityIdTO createOrUpdateBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityDataArgumentTO betCommunityData) throws RemoteException;

    void deleteBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId) throws RemoteException;

    void addUserGroupToBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, UserGroupIdTO userGroupId)
            throws RemoteException;

    void removeUserGroupFromBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, UserGroupIdTO userGroupId)
            throws RemoteException;

    void addTournamentToBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, TournamentIdTO tournId) throws RemoteException;

    void removeTournamentFromBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, TournamentIdTO tournId)
            throws RemoteException;

    void addModeratorToBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, UserIdTO modId) throws RemoteException;

    void removeModeratorFromBetCommunity(ContextTO context, SessionIdTO sessionId, BetCommunityIdTO betCommunityId, UserIdTO modId) throws RemoteException;

    UserGroupIdTO createOrUpdateUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupDataTO userGroupData) throws RemoteException;

    void deleteUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws RemoteException;

    void addUserToUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId, UserIdTO userId) throws RemoteException;

    void removeUserFromUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId, UserIdTO userId) throws RemoteException;

    void joinUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws RemoteException;

    void leaveUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId) throws RemoteException;

    void addModeratorToUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId, UserIdTO modId) throws RemoteException;

    void removeModeratorFromUserGroup(ContextTO context, SessionIdTO sessionId, UserGroupIdTO userGroupId, UserIdTO modId) throws RemoteException;
}
