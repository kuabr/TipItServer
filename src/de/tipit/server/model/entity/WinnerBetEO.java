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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "WinnerBet")
public class WinnerBetEO extends EntityRepresentation {

    private Long id;

    private TournamentEO tournament;

    private TeamEO team;

    private UserEO user;

    private Collection<WinnerBetCommentEO> comments;

    private Date creation;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("tournament", tournament);
        data.put("team", team);
        data.put("user", user);
        data.put("creation", creation);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "WinnerBet";
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
    public TournamentEO getTournament() {
        return tournament;
    }

    public void setTournament(TournamentEO tournament) {
        this.tournament = tournament;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public TeamEO getTeam() {
        return team;
    }

    public void setTeam(TeamEO team) {
        this.team = team;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public UserEO getUser() {
        return user;
    }

    public void setUser(UserEO user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "winnerBet", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<WinnerBetCommentEO> getComments() {
        return comments;
    }

    public void setComments(Collection<WinnerBetCommentEO> comments) {
        this.comments = comments;
    }

    @Column(nullable = false, unique = false)
    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }
}
