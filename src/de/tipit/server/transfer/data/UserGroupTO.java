package de.tipit.server.transfer.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserGroup")
public class UserGroupTO extends UserGroupDataTO {

    private static final long serialVersionUID = 2986050412986079405L;

    private BetCommunityNameTO[] betCommunities;

    private UserNameTO[] users;

    private UserNameTO founder;

    private UserNameTO[] moderators;

    private Date foundation;

    private Date deleted;

    public BetCommunityNameTO[] getBetCommunities() {
        return betCommunities;
    }

    public void setBetCommunities(BetCommunityNameTO[] betCommunities) {
        this.betCommunities = betCommunities;
    }

    public UserNameTO[] getUsers() {
        return users;
    }

    public void setUsers(UserNameTO[] users) {
        this.users = users;
    }

    public UserNameTO getFounder() {
        return founder;
    }

    public void setFounder(UserNameTO founder) {
        this.founder = founder;
    }

    public UserNameTO[] getModerators() {
        return moderators;
    }

    public void setModerators(UserNameTO[] moderators) {
        this.moderators = moderators;
    }

    public Date getFoundation() {
        return foundation;
    }

    public void setFoundation(Date foundation) {
        this.foundation = foundation;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
