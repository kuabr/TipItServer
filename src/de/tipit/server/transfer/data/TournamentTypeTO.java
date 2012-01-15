package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TournamentType")
public class TournamentTypeTO extends TournamentTypeDataResultTO {

    private static final long serialVersionUID = 2424033643029822576L;

    private TournamentDescrTO[] tournaments;

    private TeamNameTO[] allowedTeams;

    public TournamentDescrTO[] getTournaments() {
        return tournaments;
    }

    public void setTournaments(TournamentDescrTO[] tournaments) {
        this.tournaments = tournaments;
    }

    public TeamNameTO[] getAllowedTeams() {
        return allowedTeams;
    }

    public void setAllowedTeams(TeamNameTO[] allowedTeams) {
        this.allowedTeams = allowedTeams;
    }
}
