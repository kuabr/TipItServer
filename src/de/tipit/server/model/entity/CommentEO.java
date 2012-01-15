package de.tipit.server.model.entity;

import java.util.Date;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import de.tipit.server.transfer.data.ContextTO.Language;

@Entity
@Table(name = "Comment")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CommentEO extends EntityRepresentation {

    public static final int MAX_COMMENT_LENGTH = InternationalDescription.MAX_DESCRIPTION_LENGTH;

    private Long id;

    private String comment;

    private UserEO user;

    private Language lang;

    private Date creation;

    private Date modified;

    private Date deleted;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("comment", comment);
        data.put("user", user);
        data.put("lang", lang);
        data.put("creation", creation);
        data.put("modified", modified);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "Comment";
    }

    @Transient
    @Override
    public String getIdAsString() {
        return this.id.toString();
    }

    @Column
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = false, length = MAX_COMMENT_LENGTH)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public UserEO getUser() {
        return user;
    }

    public void setUser(UserEO user) {
        this.user = user;
    }

    @Column(nullable = false, unique = false)
    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    @Column(nullable = false, unique = false)
    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    @Column(nullable = false, unique = false)
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Column(nullable = false, unique = false)
    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
