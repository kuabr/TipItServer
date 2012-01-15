package de.tipit.server.model.entity;

import java.util.Date;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Tournament")
public class TournamentEO extends EntityRepresentation {

    private Long id;

    private TournamentTypeEO tournType;

    private InternationalShortDescr tournSaison;

    private TeamEO winnerTeam;

    private Collection<TournamentRoundEO> tournRounds;

    private Collection<BetCommunityEO> betCommunities;

    private UserEO creator;

    private Collection<UserEO> moderators;

    private Collection<WinnerTeamCommentEO> comments;

    private Date creation;

    private Date deleted;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("tournType", tournType);
        data.put("tournSaison", tournSaison);
        data.put("winnerTeam", winnerTeam);
        data.put("creator", creator);
        data.put("creation", creation);
        data.put("deleted", deleted);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "Tournament";
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

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public TournamentTypeEO getTournType() {
        return tournType;
    }

    public void setTournType(TournamentTypeEO tournType) {
        this.tournType = tournType;
    }

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "descrDE", column = @Column(name = "tournSaisonDE")),
            @AttributeOverride(name = "descrEN", column = @Column(name = "tournSaisonEN")) })
    public InternationalShortDescr getTournSaison() {
        return tournSaison;
    }

    public void setTournSaison(InternationalShortDescr tournSaison) {
        this.tournSaison = tournSaison;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, unique = false)
    public TeamEO getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(TeamEO winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    @OneToMany(mappedBy = "tournament", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<TournamentRoundEO> getTournRounds() {
        return tournRounds;
    }

    public void setTournRounds(Collection<TournamentRoundEO> tournRounds) {
        this.tournRounds = tournRounds;
    }

    @ManyToMany(mappedBy = "tournaments", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<BetCommunityEO> getBetCommunities() {
        return betCommunities;
    }

    public void setBetCommunities(Collection<BetCommunityEO> betCommunities) {
        this.betCommunities = betCommunities;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public UserEO getCreator() {
        return creator;
    }

    public void setCreator(UserEO creator) {
        this.creator = creator;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "TournamentModerator")
    public Collection<UserEO> getModerators() {
        return moderators;
    }

    public void setModerators(Collection<UserEO> moderators) {
        this.moderators = moderators;
    }

    @OneToMany(mappedBy = "tournamentWithWinnerTeam", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<WinnerTeamCommentEO> getComments() {
        return comments;
    }

    public void setComments(Collection<WinnerTeamCommentEO> comments) {
        this.comments = comments;
    }

    @Column(nullable = false, unique = false)
    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    @Column(nullable = true, unique = false)
    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
