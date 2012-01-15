package de.tipit.server.transfer.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LoginParameter")
public class LoginParameterTO implements Serializable {

    private static final long serialVersionUID = 4075222129187220153L;

    public static enum SessionDuration {
        HOUR, DAY, WEEK, MONTH, YEAR, INFINITE
    }

    private SessionDuration sessionDuration;

    private Boolean killOldSessions;

    public SessionDuration getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(SessionDuration sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    public Boolean getKillOldSessions() {
        return killOldSessions;
    }

    public void setKillOldSessions(Boolean killOldSessions) {
        this.killOldSessions = killOldSessions;
    }
}
