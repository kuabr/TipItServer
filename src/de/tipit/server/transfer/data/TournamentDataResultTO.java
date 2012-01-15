package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TournamentDataResult")
public class TournamentDataResultTO extends TournamentDataBaseTO {

    private static final long serialVersionUID = -7995058201424338025L;

    private TournamentTypeNameTO tournTypeName;

    private TeamNameTO winnerTeamName;

    public TournamentTypeNameTO getTournTypeName() {
        return tournTypeName;
    }

    public void setTournTypeName(TournamentTypeNameTO tournTypeName) {
        this.tournTypeName = tournTypeName;
    }

    public TeamNameTO getWinnerTeamName() {
        return winnerTeamName;
    }

    public void setWinnerTeamName(TeamNameTO winnerTeamName) {
        this.winnerTeamName = winnerTeamName;
    }
}
