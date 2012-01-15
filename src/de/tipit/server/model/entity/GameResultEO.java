package de.tipit.server.model.entity;

import java.util.Date;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import de.tipit.server.transfer.data.GameResultDataTO.CountingResultType;

@Entity
@Table(name = "GameResult")
public class GameResultEO extends EntityRepresentation {

    private Long gameId;

    private GameEO game;

    private Integer countingHomeResult;

    private Integer countingAwayResult;

    private CountingResultType countingResultType;

    private Integer homeResultInPenaltyShootOut;

    private Integer awayResultInPenaltyShootOut;

    private Collection<GameResultCommentEO> comments;

    private UserEO creator;

    private Date creation;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("gameId", gameId);
        data.put("countingHomeResult", countingHomeResult);
        data.put("countingAwayResult", countingAwayResult);
        data.put("countingResultType", countingResultType);
        data.put("homeResultInPenaltyShootOut", homeResultInPenaltyShootOut);
        data.put("awayResultInPenaltyShootOut", awayResultInPenaltyShootOut);
        data.put("creator", creator);
        data.put("creation", creation);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "GameResult";
    }

    @Transient
    @Override
    public String getIdAsString() {
        return this.gameId.toString();
    }

    @Column
    @Id
    // 'gameId' is not generated !!!
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    @OneToOne(mappedBy = "result", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public GameEO getGame() {
        return game;
    }

    public void setGame(GameEO game) {
        this.game = game;
    }

    @Column(nullable = false, unique = false)
    public Integer getCountingHomeResult() {
        return countingHomeResult;
    }

    public void setCountingHomeResult(Integer countingHomeResult) {
        this.countingHomeResult = countingHomeResult;
    }

    @Column(nullable = false, unique = false)
    public Integer getCountingAwayResult() {
        return countingAwayResult;
    }

    public void setCountingAwayResult(Integer countingAwayResult) {
        this.countingAwayResult = countingAwayResult;
    }

    @Column(nullable = false, unique = false)
    public CountingResultType getCountingResultType() {
        return countingResultType;
    }

    public void setCountingResultType(CountingResultType countingResultType) {
        this.countingResultType = countingResultType;
    }

    @Column(nullable = true, unique = false)
    public Integer getHomeResultInPenaltyShootOut() {
        return homeResultInPenaltyShootOut;
    }

    public void setHomeResultInPenaltyShootOut(Integer homeResultInPenaltyShootOut) {
        this.homeResultInPenaltyShootOut = homeResultInPenaltyShootOut;
    }

    @Column(nullable = true, unique = false)
    public Integer getAwayResultInPenaltyShootOut() {
        return awayResultInPenaltyShootOut;
    }

    public void setAwayResultInPenaltyShootOut(Integer awayResultInPenaltyShootOut) {
        this.awayResultInPenaltyShootOut = awayResultInPenaltyShootOut;
    }

    @OneToMany(mappedBy = "gameResult", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<GameResultCommentEO> getComments() {
        return comments;
    }

    public void setComments(Collection<GameResultCommentEO> comments) {
        this.comments = comments;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public UserEO getCreator() {
        return creator;
    }

    public void setCreator(UserEO creator) {
        this.creator = creator;
    }

    @Column(nullable = false, unique = false)
    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }
}
