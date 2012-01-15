package de.tipit.server.transfer.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WinnerBet")
public class WinnerBetTO extends WinnerBetDataResultTO {

    private static final long serialVersionUID = -863261802138248218L;

    private UserNameTO user;

    private CommentTO[] comments;

    private Date creation;

    public UserNameTO getUser() {
        return user;
    }

    public void setUser(UserNameTO user) {
        this.user = user;
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
}
