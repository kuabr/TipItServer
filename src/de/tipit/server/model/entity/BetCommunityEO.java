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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "BetCommunity")
public class BetCommunityEO extends EntityRepresentation {

    public static final int MAX_COMMUNITY_NAME_LENGTH = InternationalName.MAX_NAME_LENGTH;

    private Long id;

    private String communityName;

    private InternationalDescription description;

    private Collection<UserGroupEO> userGroups;

    private RuleBookEO ruleBook;

    private Collection<TournamentEO> tournaments;

    private UserEO founder;

    private Collection<UserEO> moderators;

    private Date foundation;

    private Date deleted;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("communityName", communityName);
        data.put("description", description);
        data.put("ruleBook", ruleBook);
        data.put("founder", founder);
        data.put("foundation", foundation);
        data.put("deleted", deleted);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "BetCommunity";
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

    @Column(nullable = false, unique = true, length = MAX_COMMUNITY_NAME_LENGTH)
    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    @Embedded
    public InternationalDescription getDescription() {
        return description;
    }

    public void setDescription(InternationalDescription description) {
        this.description = description;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "BetCommunityParticipant")
    public Collection<UserGroupEO> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Collection<UserGroupEO> userGroups) {
        this.userGroups = userGroups;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, unique = false)
    public RuleBookEO getRuleBook() {
        return ruleBook;
    }

    public void setRuleBook(RuleBookEO ruleBook) {
        this.ruleBook = ruleBook;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "BetCommunityTournament")
    public Collection<TournamentEO> getTournaments() {
        return tournaments;
    }

    public void setTournaments(Collection<TournamentEO> tournaments) {
        this.tournaments = tournaments;
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
    @JoinTable(name = "BetCommunityModerator")
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
