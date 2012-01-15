package de.tipit.server.model.entity;

import java.util.Date;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import de.tipit.server.transfer.data.UserDataTO.Gender;

@Entity
@Table(name = "User")
public class UserEO extends EntityRepresentation {

    public static final int MAX_FULL_NAME_LENGTH = InternationalName.MAX_NAME_LENGTH;

    public static final int MAX_USER_NAME_LENGTH = 12;

    public static final int MAX_MAIL_ADDRESS_LENGTH = 64;

    private Long id;

    private String userName;

    private byte[] encryptedPasswd;

    private String mailAddress;

    private String fullPreName;

    private String fullSurName;

    private Gender gender;

    private Date birthday;

    private Collection<BetCommunityEO> moderatedBetCommunities;

    private Collection<UserGroupEO> moderatedUserGroups;

    private Collection<TournamentEO> moderatedTournaments;

    private Boolean admin;

    private Boolean guest;

    private Collection<UserGroupEO> userGroups;

    private Collection<SessionEO> sessions;

    private Date creation;

    private Date inactive;

    private Date disabled;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("userName", userName);
        data.put("encryptedPasswd", encryptedPasswd);
        data.put("mailAddress", mailAddress);
        data.put("fullPreName", fullPreName);
        data.put("fullSurName", fullSurName);
        data.put("gender", gender);
        data.put("birthday", birthday);
        data.put("admin", admin);
        data.put("guest", guest);
        data.put("creation", creation);
        data.put("inactive", inactive);
        data.put("disabled", disabled);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "User";
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

    @Column(nullable = false, unique = true, length = MAX_USER_NAME_LENGTH)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(nullable = false, unique = false)
    public byte[] getEncryptedPasswd() {
        return encryptedPasswd;
    }

    public void setEncryptedPasswd(byte[] encryptedPasswd) {
        this.encryptedPasswd = encryptedPasswd;
    }

    @Column(nullable = false, unique = true, length = MAX_MAIL_ADDRESS_LENGTH)
    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @Column(nullable = true, unique = false, length = MAX_FULL_NAME_LENGTH)
    public String getFullPreName() {
        return fullPreName;
    }

    public void setFullPreName(String fullPreName) {
        this.fullPreName = fullPreName;
    }

    @Column(nullable = true, unique = false, length = MAX_FULL_NAME_LENGTH)
    public String getFullSurName() {
        return fullSurName;
    }

    public void setFullSurName(String fullSurName) {
        this.fullSurName = fullSurName;
    }

    @Column(nullable = true, unique = false)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(nullable = true, unique = false)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @ManyToMany(mappedBy = "moderators", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<BetCommunityEO> getModeratedBetCommunities() {
        return moderatedBetCommunities;
    }

    public void setModeratedBetCommunities(Collection<BetCommunityEO> moderatedBetCommunities) {
        this.moderatedBetCommunities = moderatedBetCommunities;
    }

    @ManyToMany(mappedBy = "moderators", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<UserGroupEO> getModeratedUserGroups() {
        return moderatedUserGroups;
    }

    public void setModeratedUserGroups(Collection<UserGroupEO> moderatedUserGroups) {
        this.moderatedUserGroups = moderatedUserGroups;
    }

    @ManyToMany(mappedBy = "moderators", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<TournamentEO> getModeratedTournaments() {
        return moderatedTournaments;
    }

    public void setModeratedTournaments(Collection<TournamentEO> moderatedTournaments) {
        this.moderatedTournaments = moderatedTournaments;
    }

    @Column(nullable = true, unique = false)
    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Column(nullable = true, unique = false)
    public Boolean getGuest() {
        return guest;
    }

    public void setGuest(Boolean guest) {
        this.guest = guest;
    }

    @ManyToMany(mappedBy = "users", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<UserGroupEO> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Collection<UserGroupEO> userGroups) {
        this.userGroups = userGroups;
    }

    @OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<SessionEO> getSessions() {
        return sessions;
    }

    public void setSessions(Collection<SessionEO> sessions) {
        this.sessions = sessions;
    }

    @Column(nullable = false, unique = false)
    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    @Column(nullable = true, unique = false)
    public Date getInactive() {
        return inactive;
    }

    public void setInactive(Date inactive) {
        this.inactive = inactive;
    }

    @Column(nullable = true, unique = false)
    public Date getDisabled() {
        return disabled;
    }

    public void setDisabled(Date disabled) {
        this.disabled = disabled;
    }
}
