package de.tipit.server.model.entity;

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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Team")
public class TeamEO extends EntityRepresentation {

    public static final int MAX_TEAM_ABBREV_LENGTH = 3;

    private Long id;

    private String teamAbbrev;

    private InternationalName teamName;

    private Collection<TournamentTypeEO> allowedTournTypes;

    private Collection<GameEO> homeGames;

    private Collection<GameEO> awayGames;

    private Collection<TournamentEO> wonTournaments;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("teamAbbrev", teamAbbrev);
        data.put("teamName", teamName);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "Team";
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

    @Column(nullable = false, unique = true, length = MAX_TEAM_ABBREV_LENGTH)
    public String getTeamAbbrev() {
        return teamAbbrev;
    }

    public void setTeamAbbrev(String teamAbbrev) {
        this.teamAbbrev = teamAbbrev;
    }

    @Embedded
    public InternationalName getTeamName() {
        return teamName;
    }

    public void setTeamName(InternationalName teamName) {
        this.teamName = teamName;
    }

    @ManyToMany(mappedBy = "allowedTeams", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<TournamentTypeEO> getAllowedTournTypes() {
        return allowedTournTypes;
    }

    public void setAllowedTournTypes(Collection<TournamentTypeEO> allowedTournTypes) {
        this.allowedTournTypes = allowedTournTypes;
    }

    @OneToMany(mappedBy = "homeTeam", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<GameEO> getHomeGames() {
        return homeGames;
    }

    public void setHomeGames(Collection<GameEO> homeGames) {
        this.homeGames = homeGames;
    }

    @OneToMany(mappedBy = "awayTeam", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<GameEO> getAwayGames() {
        return awayGames;
    }

    public void setAwayGames(Collection<GameEO> awayGames) {
        this.awayGames = awayGames;
    }

    @OneToMany(mappedBy = "winnerTeam", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<TournamentEO> getWonTournaments() {
        return wonTournaments;
    }

    public void setWonTournaments(Collection<TournamentEO> wonTournaments) {
        this.wonTournaments = wonTournaments;
    }
}
