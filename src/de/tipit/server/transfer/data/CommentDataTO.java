package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CommentData")
public class CommentDataTO extends CommentIdTO {

    private static final long serialVersionUID = -4125701628724845683L;

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
