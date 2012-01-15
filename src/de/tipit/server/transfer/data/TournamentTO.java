package de.tipit.server.transfer.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Tournament")
public class TournamentTO extends TournamentDataResultTO {

    private static final long serialVersionUID = 6700309862326688385L;

    private TournamentRoundDataArgumentTO[] tournRounds;

    private BetCommunityNameTO[] betCommunities;

    private UserNameTO creator;

    private UserNameTO[] moderators;

    private CommentTO[] comments;

    private Date creation;

    private Date deleted;

    public TournamentRoundDataArgumentTO[] getTournRounds() {
        return tournRounds;
    }

    public void setTournRounds(TournamentRoundDataArgumentTO[] tournRounds) {
        this.tournRounds = tournRounds;
    }

    public BetCommunityNameTO[] getBetCommunities() {
        return betCommunities;
    }

    public void setBetCommunities(BetCommunityNameTO[] betCommunities) {
        this.betCommunities = betCommunities;
    }

    public UserNameTO getCreator() {
        return creator;
    }

    public void setCreator(UserNameTO creator) {
        this.creator = creator;
    }

    public UserNameTO[] getModerators() {
        return moderators;
    }

    public void setModerators(UserNameTO[] moderators) {
        this.moderators = moderators;
    }

    public CommentTO[] getComments() {
        return comments;
    }

    public void setComments(CommentTO[] comments) {
        this.comments = comments;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
