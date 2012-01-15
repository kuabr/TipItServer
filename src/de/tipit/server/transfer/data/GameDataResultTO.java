package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GameDataResult")
public class GameDataResultTO extends GameDataBaseTO {

    private static final long serialVersionUID = -1173690679897562474L;

    private MatchDayDescrTO matchDayDescr;

    private TeamNameTO homeTeamName;

    private TeamNameTO awayTeamName;

    public MatchDayDescrTO getMatchDayDescr() {
        return matchDayDescr;
    }

    public void setMatchDayDescr(MatchDayDescrTO matchDayDescr) {
        this.matchDayDescr = matchDayDescr;
    }

    public TeamNameTO getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(TeamNameTO homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public TeamNameTO getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(TeamNameTO awayTeamName) {
        this.awayTeamName = awayTeamName;
    }
}
