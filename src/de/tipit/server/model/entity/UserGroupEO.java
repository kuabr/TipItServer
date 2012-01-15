package de.tipit.server.model.entity;

import java.util.Date;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "UserGroup")
public class UserGroupEO extends EntityRepresentation {

    public static final int MAX_USER_GROUP_NAME_LENGTH = InternationalName.MAX_NAME_LENGTH;

    private Long id;

    private String groupName;

    private InternationalDescription description;

    private Collection<BetCommunityEO> betCommunities;

    private Collection<UserEO> users;

    private UserEO founder;

    private Collection<UserEO> moderators;

    private Date foundation;

    private Date deleted;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("groupName", groupName);
        data.put("description", description);
        data.put("founder", founder);
        data.put("foundation", foundation);
        data.put("deleted", deleted);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "UserGroup";
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

    @Column(nullable = false, unique = true, length = MAX_USER_GROUP_NAME_LENGTH)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Embedded
    public InternationalDescription getDescription() {
        return description;
    }

    public void setDescription(InternationalDescription description) {
        this.description = description;
    }

    @ManyToMany(mappedBy = "userGroups", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<BetCommunityEO> getBetCommunities() {
        return betCommunities;
    }

    public void setBetCommunities(Collection<BetCommunityEO> betCommunities) {
        this.betCommunities = betCommunities;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "UserGroupParticipant")
    public Collection<UserEO> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserEO> users) {
        this.users = users;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public UserEO getFounder() {
        return founder;
    }

    public void setFounder(UserEO founder) {
        this.founder = founder;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "UserGroupModerator")
    public Collection<UserEO> getModerators() {
        return moderators;
    }

    public void setModerators(Collection<UserEO> moderators) {
        this.moderators = moderators;
    }

    @Column(nullable = false, unique = false)
    public Date getFoundation() {
        return foundation;
    }

    public void setFoundation(Date foundation) {
        this.foundation = foundation;
    }

    @Column(nullable = true, unique = false)
    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
