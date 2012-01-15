package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WinnerBetDataResult")
public class WinnerBetDataResultTO extends WinnerBetIdTO {

    private static final long serialVersionUID = 3394236127832126581L;

    private TournamentDescrTO tournDescr;

    private TeamNameTO winnerTeamName;

    public TournamentDescrTO getTournDescr() {
        return tournDescr;
    }

    public void setTournDescr(TournamentDescrTO tournDescr) {
        this.tournDescr = tournDescr;
    }

    public TeamNameTO getWinnerTeamName() {
        return winnerTeamName;
    }

    public void setWinnerTeamName(TeamNameTO winnerTeamName) {
        this.winnerTeamName = winnerTeamName;
    }
}
