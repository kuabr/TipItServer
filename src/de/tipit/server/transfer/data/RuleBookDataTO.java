package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RuleBookData")
public class RuleBookDataTO extends RuleBookIdTO {

    private static final long serialVersionUID = -4053876984205443400L;

    private String ruleBookName;

    private String description;

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

    public String getRuleBookName() {
        return ruleBookName;
    }

    public void setRuleBookName(String ruleBookName) {
        this.ruleBookName = ruleBookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPointsToAddForCorrectHomeResult() {
        return pointsToAddForCorrectHomeResult;
    }

    public void setPointsToAddForCorrectHomeResult(Integer pointsToAddForCorrectHomeResult) {
        this.pointsToAddForCorrectHomeResult = pointsToAddForCorrectHomeResult;
    }

    public Integer getPointsToAddForCorrectAwayResult() {
        return pointsToAddForCorrectAwayResult;
    }

    public void setPointsToAddForCorrectAwayResult(Integer pointsToAddForCorrectAwayResult) {
        this.pointsToAddForCorrectAwayResult = pointsToAddForCorrectAwayResult;
    }

    public Integer getPointsToAddForCorrectHomeResultInCaseOfCorrectTrend() {
        return pointsToAddForCorrectHomeResultInCaseOfCorrectTrend;
    }

    public void setPointsToAddForCorrectHomeResultInCaseOfCorrectTrend(Integer pointsToAddForCorrectHomeResultInCaseOfCorrectTrend) {
        this.pointsToAddForCorrectHomeResultInCaseOfCorrectTrend = pointsToAddForCorrectHomeResultInCaseOfCorrectTrend;
    }

    public Integer getPointsToAddForCorrectAwayResultInCaseOfCorrectTrend() {
        return pointsToAddForCorrectAwayResultInCaseOfCorrectTrend;
    }

    public void setPointsToAddForCorrectAwayResultInCaseOfCorrectTrend(Integer pointsToAddForCorrectAwayResultInCaseOfCorrectTrend) {
        this.pointsToAddForCorrectAwayResultInCaseOfCorrectTrend = pointsToAddForCorrectAwayResultInCaseOfCorrectTrend;
    }

    public Integer getPointsToAddForCorrectResult() {
        return pointsToAddForCorrectResult;
    }

    public void setPointsToAddForCorrectResult(Integer pointsToAddForCorrectResult) {
        this.pointsToAddForCorrectResult = pointsToAddForCorrectResult;
    }

    public Integer getPointsToAddForCorrectDifference() {
        return pointsToAddForCorrectDifference;
    }

    public void setPointsToAddForCorrectDifference(Integer pointsToAddForCorrectDifference) {
        this.pointsToAddForCorrectDifference = pointsToAddForCorrectDifference;
    }

    public Integer getPointsToAddForCorrectTrend() {
        return pointsToAddForCorrectTrend;
    }

    public void setPointsToAddForCorrectTrend(Integer pointsToAddForCorrectTrend) {
        this.pointsToAddForCorrectTrend = pointsToAddForCorrectTrend;
    }

    public Integer getPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult() {
        return pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult;
    }

    public void setPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult(Integer pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult) {
        this.pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult = pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectHomeResult;
    }

    public Integer getPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult() {
        return pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult;
    }

    public void setPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult(Integer pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult) {
        this.pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult = pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectAwayResult;
    }

    public Integer getPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference() {
        return pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference;
    }

    public void setPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference(Integer pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference) {
        this.pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference = pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectDifference;
    }

    public Integer getPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend() {
        return pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend;
    }

    public void setPointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend(Integer pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend) {
        this.pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend = pointsToMultiplyWithAbsDiffBetweenAverageAndCorrectTrend;
    }

    public Integer getRoundMultiplierForFirstRound() {
        return roundMultiplierForFirstRound;
    }

    public void setRoundMultiplierForFirstRound(Integer roundMultiplierForFirstRound) {
        this.roundMultiplierForFirstRound = roundMultiplierForFirstRound;
    }

    public Integer getRoundMultiplierAdderPerRound() {
        return roundMultiplierAdderPerRound;
    }

    public void setRoundMultiplierAdderPerRound(Integer roundMultiplierAdderPerRound) {
        this.roundMultiplierAdderPerRound = roundMultiplierAdderPerRound;
    }

    public Integer getRoundMultiplierMultiplierPerRound() {
        return roundMultiplierMultiplierPerRound;
    }

    public void setRoundMultiplierMultiplierPerRound(Integer roundMultiplierMultiplierPerRound) {
        this.roundMultiplierMultiplierPerRound = roundMultiplierMultiplierPerRound;
    }

    public Integer getPointsPerUserToDistribute() {
        return pointsPerUserToDistribute;
    }

    public void setPointsPerUserToDistribute(Integer pointsPerUserToDistribute) {
        this.pointsPerUserToDistribute = pointsPerUserToDistribute;
    }

    public Integer getPointsForCorrectWinnerBet() {
        return pointsForCorrectWinnerBet;
    }

    public void setPointsForCorrectWinnerBet(Integer pointsForCorrectWinnerBet) {
        this.pointsForCorrectWinnerBet = pointsForCorrectWinnerBet;
    }

    public Integer getPointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers() {
        return pointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers;
    }

    public void setPointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers(Integer pointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers) {
        this.pointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers = pointsForCorrectWinnerBetPer100GamesConsideringRoundMultipliers;
    }

    public Integer getPointsForCorrectWinnerBetPer100PointsFromGameBets() {
        return pointsForCorrectWinnerBetPer100PointsFromGameBets;
    }

    public void setPointsForCorrectWinnerBetPer100PointsFromGameBets(Integer pointsForCorrectWinnerBetPer100PointsFromGameBets) {
        this.pointsForCorrectWinnerBetPer100PointsFromGameBets = pointsForCorrectWinnerBetPer100PointsFromGameBets;
    }
}
