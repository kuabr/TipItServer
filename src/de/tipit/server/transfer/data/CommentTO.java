package de.tipit.server.transfer.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import de.tipit.server.transfer.data.ContextTO.Language;

@XmlRootElement(name = "Comment")
public class CommentTO extends CommentDataTO {

    private static final long serialVersionUID = 9103472494108150940L;

    private UserNameTO user;

    private Language lang;

    private Date creation;

    private Date modified;

    private Date deleted;

    public UserNameTO getUser() {
        return user;
    }

    public void setUser(UserNameTO user) {
        this.user = user;
    }

    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
