package de.tipit.server.transfer.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GameResult")
public class GameResultTO extends GameResultDataTO {

    private static final long serialVersionUID = -4404947602970363084L;

    private CommentTO[] comments;

    private UserNameTO creator;

    private Date creation;

    public CommentTO[] getComments() {
        return comments;
    }

    public void setComments(CommentTO[] comments) {
        this.comments = comments;
    }

    public UserNameTO getCreator() {
        return creator;
    }

    public void setCreator(UserNameTO creator) {
        this.creator = creator;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }
}
