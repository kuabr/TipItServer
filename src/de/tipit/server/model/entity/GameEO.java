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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Game")
public class GameEO extends EntityRepresentation {

    private Long id;

    private Integer gameNumber;

    private MatchDayEO matchDay;

    private Date matchPoint;

    private InternationalShortDescr homeTeamPlaceHolder;

    private InternationalShortDescr awayTeamPlaceHolder;

    private TeamEO homeTeam;

    private TeamEO awayTeam;

    private Integer averageHomeResultBet;

    private Integer averageAwayResultBet;

    private Collection<GameBetEO> bets;

    private GameResultEO result;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("gameNumber", gameNumber);
        data.put("matchDay", matchDay);
        data.put("matchPoint", matchPoint);
        data.put("homeTeamPlaceHolder", homeTeamPlaceHolder);
        data.put("awayTeamPlaceHolder", awayTeamPlaceHolder);
        data.put("homeTeam", homeTeam);
        data.put("awayTeam", awayTeam);
        data.put("averageHomeResultBet", averageHomeResultBet);
        data.put("averageAwayResultBet", averageAwayResultBet);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "Game";
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

    @Column(nullable = true, unique = false)
    public Integer getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(Integer gameNumber) {
        this.gameNumber = gameNumber;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public MatchDayEO getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(MatchDayEO matchDay) {
        this.matchDay = matchDay;
    }

    @Column(nullable = false, unique = false)
    public Date getMatchPoint() {
        return matchPoint;
    }

    public void setMatchPoint(Date matchPoint) {
        this.matchPoint = matchPoint;
    }

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "descrDE", column = @Column(name = "homeTeamPlaceHolderDE")),
            @AttributeOverride(name = "descrEN", column = @Column(name = "homeTeamPlaceHolderEN")) })
    public InternationalShortDescr getHomeTeamPlaceHolder() {
        return homeTeamPlaceHolder;
    }

    public void setHomeTeamPlaceHolder(InternationalShortDescr homeTeamPlaceHolder) {
        this.homeTeamPlaceHolder = homeTeamPlaceHolder;
    }

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "descrDE", column = @Column(name = "awayTeamPlaceHolderDE")),
            @AttributeOverride(name = "descrEN", column = @Column(name = "awayTeamPlaceHolderEN")) })
    public InternationalShortDescr getAwayTeamPlaceHolder() {
        return awayTeamPlaceHolder;
    }

    public void setAwayTeamPlaceHolder(InternationalShortDescr awayTeamPlaceHolder) {
        this.awayTeamPlaceHolder = awayTeamPlaceHolder;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public TeamEO getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamEO homeTeam) {
        this.homeTeam = homeTeam;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public TeamEO getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(TeamEO awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Column(nullable = true, unique = false)
    public Integer getAverageHomeResultBet() {
        return averageHomeResultBet;
    }

    public void setAverageHomeResultBet(Integer averageHomeResultBet) {
        this.averageHomeResultBet = averageHomeResultBet;
    }

    @Column(nullable = true, unique = false)
    public Integer getAverageAwayResultBet() {
        return averageAwayResultBet;
    }

    public void setAverageAwayResultBet(Integer averageAwayResultBet) {
        this.averageAwayResultBet = averageAwayResultBet;
    }

    @OneToMany(mappedBy = "game", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<GameBetEO> getBets() {
        return bets;
    }

    public void setBets(Collection<GameBetEO> bets) {
        this.bets = bets;
    }

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public GameResultEO getResult() {
        return result;
    }

    public void setResult(GameResultEO result) {
        this.result = result;
    }
}
