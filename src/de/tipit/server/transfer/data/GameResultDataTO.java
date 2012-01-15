package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GameResultData")
public class GameResultDataTO extends GameIdTO {

    private static final long serialVersionUID = -416497808243676159L;

    public static enum CountingResultType {
        AFTER_REGULAR_TIME, AFTER_EXTRA_TIME
    }

    private Integer countingHomeResult;

    private Integer countingAwayResult;

    private CountingResultType countingResultType;

    private Integer homeResultInPenaltyShootOut;

    private Integer awayResultInPenaltyShootOut;

    public Integer getCountingHomeResult() {
        return countingHomeResult;
    }

    public void setCountingHomeResult(Integer countingHomeResult) {
        this.countingHomeResult = countingHomeResult;
    }

    public Integer getCountingAwayResult() {
        return countingAwayResult;
    }

    public void setCountingAwayResult(Integer countingAwayResult) {
        this.countingAwayResult = countingAwayResult;
    }

    public CountingResultType getCountingResultType() {
        return countingResultType;
    }

    public void setCountingResultType(CountingResultType countingResultType) {
        this.countingResultType = countingResultType;
    }

    public Integer getHomeResultInPenaltyShootOut() {
        return homeResultInPenaltyShootOut;
    }

    public void setHomeResultInPenaltyShootOut(Integer homeResultInPenaltyShootOut) {
        this.homeResultInPenaltyShootOut = homeResultInPenaltyShootOut;
    }

    public Integer getAwayResultInPenaltyShootOut() {
        return awayResultInPenaltyShootOut;
    }

    public void setAwayResultInPenaltyShootOut(Integer awayResultInPenaltyShootOut) {
        this.awayResultInPenaltyShootOut = awayResultInPenaltyShootOut;
    }
}
