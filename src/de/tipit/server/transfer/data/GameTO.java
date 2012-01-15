package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Game")
public class GameTO extends GameDataResultTO {

    private static final long serialVersionUID = 5433124942928248154L;

    private Integer averageHomeResultBet;

    private Integer averageAwayResultBet;

    private GameBetTO[] bets;

    private GameResultTO result;

    public Integer getAverageHomeResultBet() {
        return averageHomeResultBet;
    }

    public void setAverageHomeResultBet(Integer averageHomeResultBet) {
        this.averageHomeResultBet = averageHomeResultBet;
    }

    public Integer getAverageAwayResultBet() {
        return averageAwayResultBet;
    }

    public void setAverageAwayResultBet(Integer averageAwayResultBet) {
        this.averageAwayResultBet = averageAwayResultBet;
    }

    public GameBetTO[] getBets() {
        return bets;
    }

    public void setBets(GameBetTO[] bets) {
        this.bets = bets;
    }

    public GameResultTO getResult() {
        return result;
    }

    public void setResult(GameResultTO result) {
        this.result = result;
    }
}
