package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GameDataArgument")
public class GameDataArgumentTO extends GameDataBaseTO {

    private static final long serialVersionUID = 2126603861334278256L;

    private MatchDayIdTO matchDayId;

    private TeamIdTO homeTeamId;

    private TeamIdTO awayTeamId;

    public MatchDayIdTO getMatchDayId() {
        return matchDayId;
    }

    public void setMatchDayId(MatchDayIdTO matchDayId) {
        this.matchDayId = matchDayId;
    }

    public TeamIdTO getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(TeamIdTO homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public TeamIdTO getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(TeamIdTO awayTeamId) {
        this.awayTeamId = awayTeamId;
    }
}
