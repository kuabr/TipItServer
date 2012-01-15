package de.tipit.server.transfer.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RuleBook")
public class RuleBookTO extends RuleBookDataTO {

    private static final long serialVersionUID = -5462769874519589503L;

    private BetCommunityNameTO[] betCommunities;

    private UserNameTO creator;

    private Date created;

    private CommentTO[] comments;

    private Date finalized;

    private Date deleted;

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public CommentTO[] getComments() {
        return comments;
    }

    public void setComments(CommentTO[] comments) {
        this.comments = comments;
    }

    public Date getFinalized() {
        return finalized;
    }

    public void setFinalized(Date finalized) {
        this.finalized = finalized;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
