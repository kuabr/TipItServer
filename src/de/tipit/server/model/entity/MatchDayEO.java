package de.tipit.server.model.entity;

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
@Table(name = "MatchDay")
public class MatchDayEO extends EntityRepresentation {

    private Long id;

    private TournamentRoundEO tournRound;

    private Integer day;

    private Collection<GameEO> games;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("tournRound", tournRound);
        data.put("day", day);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "MatchDay";
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
    public TournamentRoundEO getTournRound() {
        return tournRound;
    }

    public void setTournRound(TournamentRoundEO tournRound) {
        this.tournRound = tournRound;
    }

    @Column(nullable = false, unique = false)
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @OneToMany(mappedBy = "matchDay", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<GameEO> getGames() {
        return games;
    }

    public void setGames(Collection<GameEO> games) {
        this.games = games;
    }
}
