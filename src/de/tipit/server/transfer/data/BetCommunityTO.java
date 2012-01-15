package de.tipit.server.transfer.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BetCommunity")
public class BetCommunityTO extends BetCommunityDataResultTO {

    private static final long serialVersionUID = -216845832609559670L;

    private UserGroupNameTO[] userGroups;

    private TournamentDescrTO[] tournaments;

    private UserNameTO founder;

    private UserNameTO[] moderators;

    private Date foundation;

    private Date deleted;

    public UserGroupNameTO[] getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(UserGroupNameTO[] userGroups) {
        this.userGroups = userGroups;
    }

    public TournamentDescrTO[] getTournaments() {
        return tournaments;
    }

    public void setTournaments(TournamentDescrTO[] tournaments) {
        this.tournaments = tournaments;
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
