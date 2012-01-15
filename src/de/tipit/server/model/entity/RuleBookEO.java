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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "RuleBook")
public class RuleBookEO extends EntityRepresentation {

    private Long id;

    private InternationalName ruleBookName;

    private InternationalDescription description;

    private Integer pointsToAddForCorrectHomeResult;

    private Integer pointsToAddForCorrectAwayResult;

    private Integer pointsToAddForCorrectHomeResultInCaseOfCorrectTrend;

    private Integer pointsToAddForCorrectAwayResultInCaseOfCorrectTrend;

    private Integer pointsToAddForCorrectResult;

    private Integer pointsToAddForCorrectDifference;

    private Integer pointsToAddForCorrectTrend;

    private Integer pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult;

    private Integer pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult;

    private Integer pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference;

    private Integer pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend;

    private Integer roundMultiplierForFirstRound;

    private Integer roundMultiplierAdderPerRound;

    private Integer roundMultiplierMultiplierPerRound;

    private Integer pointsPerUserToDistribute;

    private Integer pointsForCorrectWinnerBet;

    private Integer pointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers;

    private Integer pointsForCorrectWinnerBetPer100PointsFromGameBets;

    private Collection<BetCommunityEO> betCommunities;

    private UserEO creator;

    private Date created;

    private Collection<RuleBookCommentEO> comments;

    private Date finalized;

    private Date deleted;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("ruleBookName", ruleBookName);
        data.put("description", description);
        data.put("pointsToAddForCorrectHomeResult", pointsToAddForCorrectHomeResult);
        data.put("pointsToAddForCorrectAwayResult", pointsToAddForCorrectAwayResult);
        data.put("pointsToAddForCorrectHomeResultInCaseOfCorrectTrend", pointsToAddForCorrectHomeResultInCaseOfCorrectTrend);
        data.put("pointsToAddForCorrectAwayResultInCaseOfCorrectTrend", pointsToAddForCorrectAwayResultInCaseOfCorrectTrend);
        data.put("pointsToAddForCorrectResult", pointsToAddForCorrectResult);
        data.put("pointsToAddForCorrectDifference", pointsToAddForCorrectDifference);
        data.put("pointsToAddForCorrectTrend", pointsToAddForCorrectTrend);
        data.put("pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult", pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult);
        data.put("pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult", pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult);
        data.put("pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference", pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference);
        data.put("pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend", pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend);
        data.put("roundMultiplierForFirstRound", roundMultiplierForFirstRound);
        data.put("roundMultiplierAdderPerRound", roundMultiplierAdderPerRound);
        data.put("roundMultiplierMultiplierPerRound", roundMultiplierMultiplierPerRound);
        data.put("pointsPerUserToDistribute", pointsPerUserToDistribute);
        data.put("pointsForCorrectWinnerBet", pointsForCorrectWinnerBet);
        data.put("pointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers", pointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers);
        data.put("pointsForCorrectWinnerBetPer100PointsFromGameBets", pointsForCorrectWinnerBetPer100PointsFromGameBets);
        data.put("creator", creator);
        data.put("created", created);
        data.put("finalized", finalized);
        data.put("deleted", deleted);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "RuleBook";
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

    @Embedded
    public InternationalName getRuleBookName() {
        return ruleBookName;
    }

    public void setRuleBookName(InternationalName ruleBookName) {
        this.ruleBookName = ruleBookName;
    }

    @Embedded
    public InternationalDescription getDescription() {
        return description;
    }

    public void setDescription(InternationalDescription description) {
        this.description = description;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsToAddForCorrectHomeResult() {
        return pointsToAddForCorrectHomeResult;
    }

    public void setPointsToAddForCorrectHomeResult(Integer pointsToAddForCorrectHomeResult) {
        this.pointsToAddForCorrectHomeResult = pointsToAddForCorrectHomeResult;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsToAddForCorrectAwayResult() {
        return pointsToAddForCorrectAwayResult;
    }

    public void setPointsToAddForCorrectAwayResult(Integer pointsToAddForCorrectAwayResult) {
        this.pointsToAddForCorrectAwayResult = pointsToAddForCorrectAwayResult;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsToAddForCorrectHomeResultInCaseOfCorrectTrend() {
        return pointsToAddForCorrectHomeResultInCaseOfCorrectTrend;
    }

    public void setPointsToAddForCorrectHomeResultInCaseOfCorrectTrend(Integer pointsToAddForCorrectHomeResultInCaseOfCorrectTrend) {
        this.pointsToAddForCorrectHomeResultInCaseOfCorrectTrend = pointsToAddForCorrectHomeResultInCaseOfCorrectTrend;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsToAddForCorrectAwayResultInCaseOfCorrectTrend() {
        return pointsToAddForCorrectAwayResultInCaseOfCorrectTrend;
    }

    public void setPointsToAddForCorrectAwayResultInCaseOfCorrectTrend(Integer pointsToAddForCorrectAwayResultInCaseOfCorrectTrend) {
        this.pointsToAddForCorrectAwayResultInCaseOfCorrectTrend = pointsToAddForCorrectAwayResultInCaseOfCorrectTrend;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsToAddForCorrectResult() {
        return pointsToAddForCorrectResult;
    }

    public void setPointsToAddForCorrectResult(Integer pointsToAddForCorrectResult) {
        this.pointsToAddForCorrectResult = pointsToAddForCorrectResult;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsToAddForCorrectDifference() {
        return pointsToAddForCorrectDifference;
    }

    public void setPointsToAddForCorrectDifference(Integer pointsToAddForCorrectDifference) {
        this.pointsToAddForCorrectDifference = pointsToAddForCorrectDifference;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsToAddForCorrectTrend() {
        return pointsToAddForCorrectTrend;
    }

    public void setPointsToAddForCorrectTrend(Integer pointsToAddForCorrectTrend) {
        this.pointsToAddForCorrectTrend = pointsToAddForCorrectTrend;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult() {
        return pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult;
    }

    public void setPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult(Integer pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult) {
        this.pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult = pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult() {
        return pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult;
    }

    public void setPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult(Integer pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult) {
        this.pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult = pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference() {
        return pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference;
    }

    public void setPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference(Integer pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference) {
        this.pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference = pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend() {
        return pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend;
    }

    public void setPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend(Integer pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend) {
        this.pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend = pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend;
    }

    @Column(nullable = true, unique = false)
    public Integer getRoundMultiplierForFirstRound() {
        return roundMultiplierForFirstRound;
    }

    public void setRoundMultiplierForFirstRound(Integer roundMultiplierForFirstRound) {
        this.roundMultiplierForFirstRound = roundMultiplierForFirstRound;
    }

    @Column(nullable = true, unique = false)
    public Integer getRoundMultiplierAdderPerRound() {
        return roundMultiplierAdderPerRound;
    }

    public void setRoundMultiplierAdderPerRound(Integer roundMultiplierAdderPerRound) {
        this.roundMultiplierAdderPerRound = roundMultiplierAdderPerRound;
    }

    @Column(nullable = true, unique = false)
    public Integer getRoundMultiplierMultiplierPerRound() {
        return roundMultiplierMultiplierPerRound;
    }

    public void setRoundMultiplierMultiplierPerRound(Integer roundMultiplierMultiplierPerRound) {
        this.roundMultiplierMultiplierPerRound = roundMultiplierMultiplierPerRound;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsPerUserToDistribute() {
        return pointsPerUserToDistribute;
    }

    public void setPointsPerUserToDistribute(Integer pointsPerUserToDistribute) {
        this.pointsPerUserToDistribute = pointsPerUserToDistribute;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsForCorrectWinnerBet() {
        return pointsForCorrectWinnerBet;
    }

    public void setPointsForCorrectWinnerBet(Integer pointsForCorrectWinnerBet) {
        this.pointsForCorrectWinnerBet = pointsForCorrectWinnerBet;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers() {
        return pointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers;
    }

    public void setPointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers(Integer pointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers) {
        this.pointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers = pointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers;
    }

    @Column(nullable = true, unique = false)
    public Integer getPointsForCorrectWinnerBetPer100PointsFromGameBets() {
        return pointsForCorrectWinnerBetPer100PointsFromGameBets;
    }

    public void setPointsForCorrectWinnerBetPer100PointsFromGameBets(Integer pointsForCorrectWinnerBetPer100PointsFromGameBets) {
        this.pointsForCorrectWinnerBetPer100PointsFromGameBets = pointsForCorrectWinnerBetPer100PointsFromGameBets;
    }

    @OneToMany(mappedBy = "ruleBook", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
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

    @Column(nullable = false, unique = false)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @OneToMany(mappedBy = "ruleBook", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<RuleBookCommentEO> getComments() {
        return comments;
    }

    public void setComments(Collection<RuleBookCommentEO> comments) {
        this.comments = comments;
    }

    @Column(nullable = true, unique = false)
    public Date getFinalized() {
        return finalized;
    }

    public void setFinalized(Date finalized) {
        this.finalized = finalized;
    }

    @Column(nullable = true, unique = false)
    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
