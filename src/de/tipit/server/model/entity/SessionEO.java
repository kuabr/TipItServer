package de.tipit.server.model.entity;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Session")
public class SessionEO extends EntityRepresentation {

    public static final int MAX_SESSION_ID_LENGTH = 128;

    private String sessionId;

    private UserEO user;

    private Date expiration;

    private Date creation;

    private Date lastUsage;

    private Boolean valid;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("user", user);
        data.put("expiration", expiration);
        data.put("creation", creation);
        data.put("lastUsage", lastUsage);
        data.put("valid", valid);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "Session";
    }

    @Transient
    @Override
    public String getIdAsString() {
        return this.sessionId;
    }

    @Column(length = MAX_SESSION_ID_LENGTH)
    @Id
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, unique = false)
    // it is nullable, because of guest logins
    public UserEO getUser() {
        return user;
    }

    public void setUser(UserEO user) {
        this.user = user;
    }

    @Column(nullable = true, unique = false)
    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    @Column(nullable = false, unique = false)
    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    @Column(nullable = false, unique = false)
    public Date getLastUsage() {
        return lastUsage;
    }

    public void setLastUsage(Date lastUsage) {
        this.lastUsage = lastUsage;
    }

    @Column(nullable = true, unique = false)
    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
