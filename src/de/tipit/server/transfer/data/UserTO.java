package de.tipit.server.transfer.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
public class UserTO extends UserDataTO {

    private static final long serialVersionUID = 6082457314575564102L;

    private BetCommunityNameTO[] betCommunitiesMod;

    private UserGroupNameTO[] userGroupsMod;

    private TournamentDescrTO[] tournamentsMod;

    private Boolean isAdmin;

    private Boolean isGuest;

    private UserGroupNameTO[] groups;

    private SessionIdTO[] sessions;

    private Date creation;

    private Date inactive;

    private Date disabled;

    public BetCommunityNameTO[] getBetCommunitiesMod() {
        return betCommunitiesMod;
    }

    public void setBetCommunitiesMod(BetCommunityNameTO[] betCommunitiesMod) {
        this.betCommunitiesMod = betCommunitiesMod;
    }

    public UserGroupNameTO[] getUserGroupsMod() {
        return userGroupsMod;
    }

    public void setUserGroupsMod(UserGroupNameTO[] userGroupsMod) {
        this.userGroupsMod = userGroupsMod;
    }

    public TournamentDescrTO[] getTournamentsMod() {
        return tournamentsMod;
    }

    public void setTournamentsMod(TournamentDescrTO[] tournamentsMod) {
        this.tournamentsMod = tournamentsMod;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsGuest() {
        return isGuest;
    }

    public void setIsGuest(Boolean isGuest) {
        this.isGuest = isGuest;
    }

    public UserGroupNameTO[] getGroups() {
        return groups;
    }

    public void setGroups(UserGroupNameTO[] groups) {
        this.groups = groups;
    }

    public SessionIdTO[] getSessions() {
        return sessions;
    }

    public void setSessions(SessionIdTO[] sessions) {
        this.sessions = sessions;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getInactive() {
        return inactive;
    }

    public void setInactive(Date inactive) {
        this.inactive = inactive;
    }

    public Date getDisabled() {
        return disabled;
    }

    public void setDisabled(Date disabled) {
        this.disabled = disabled;
    }
}
