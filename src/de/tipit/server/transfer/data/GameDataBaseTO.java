package de.tipit.server.transfer.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GameDataBase")
public class GameDataBaseTO extends GameIdTO {

    private static final long serialVersionUID = -7469607179852898685L;

    private Integer gameNumber;

    private Date matchPoint;

    private String homeTeamPlaceHolder;

    private String awayTeamPlaceHolder;

    public Integer getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(Integer gameNumber) {
        this.gameNumber = gameNumber;
    }

    public Date getMatchPoint() {
        return matchPoint;
    }

    public void setMatchPoint(Date matchPoint) {
        this.matchPoint = matchPoint;
    }

    public String getHomeTeamPlaceHolder() {
        return homeTeamPlaceHolder;
    }

    public void setHomeTeamPlaceHolder(String homeTeamPlaceHolder) {
        this.homeTeamPlaceHolder = homeTeamPlaceHolder;
    }

    public String getAwayTeamPlaceHolder() {
        return awayTeamPlaceHolder;
    }

    public void setAwayTeamPlaceHolder(String awayTeamPlaceHolder) {
        this.awayTeamPlaceHolder = awayTeamPlaceHolder;
    }
}
