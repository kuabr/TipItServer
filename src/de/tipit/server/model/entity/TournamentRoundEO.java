package de.tipit.server.model.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TournamentRound")
public class TournamentRoundEO extends EntityRepresentation {

    private Long id;

    private TournamentEO tournament;

    private Integer orderNumber;

    private Integer roundNumber;

    private InternationalShortDescr roundName;

    private Collection<MatchDayEO> matchDays;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("tournament", tournament);
        data.put("orderNumber", orderNumber);
        data.put("roundNumber", roundNumber);
        data.put("roundName", roundName);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "TournamentRound";
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

    @Column(nullable = true, unique = false)
    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Column(nullable = true, unique = false)
    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "descrDE", column = @Column(name = "roundNameDE")),
            @AttributeOverride(name = "descrEN", column = @Column(name = "roundNameEN")) })
    public InternationalShortDescr getRoundName() {
        return roundName;
    }

    public void setRoundName(InternationalShortDescr roundName) {
        this.roundName = roundName;
    }

    @OneToMany(mappedBy = "tournRound", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<MatchDayEO> getMatchDays() {
        return matchDays;
    }

    public void setMatchDays(Collection<MatchDayEO> matchDays) {
        this.matchDays = matchDays;
    }
}
