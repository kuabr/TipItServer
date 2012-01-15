package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TournamentDataArgument")
public class TournamentDataArgumentTO extends TournamentDataBaseTO {

    private static final long serialVersionUID = -6893998331705189126L;

    private TournamentTypeIdTO tournTypeId;

    private TeamIdTO winnerTeamId;

    public TournamentTypeIdTO getTournTypeId() {
        return tournTypeId;
    }

    public void setTournTypeId(TournamentTypeIdTO tournTypeId) {
        this.tournTypeId = tournTypeId;
    }

    public TeamIdTO getWinnerTeamId() {
        return winnerTeamId;
    }

    public void setWinnerTeamId(TeamIdTO winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }
}
