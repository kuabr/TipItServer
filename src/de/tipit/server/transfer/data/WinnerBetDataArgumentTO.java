package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WinnerBetDataArgument")
public class WinnerBetDataArgumentTO extends WinnerBetIdTO {

    private static final long serialVersionUID = 5839849916014887352L;

    private TournamentIdTO tournId;

    private TeamIdTO winnerTeamId;

    public TournamentIdTO getTournId() {
        return tournId;
    }

    public void setTournId(TournamentIdTO tournId) {
        this.tournId = tournId;
    }

    public TeamIdTO getWinnerTeamId() {
        return winnerTeamId;
    }

    public void setWinnerTeamId(TeamIdTO winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }
}
