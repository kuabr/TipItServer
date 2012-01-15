package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Team")
public class TeamTO extends TeamDataTO {

    private static final long serialVersionUID = -6399467933664382142L;

    private TournamentTypeNameTO[] allowedTournTypes;

    private GameResultDataTO[] homeGames;

    private GameResultDataTO[] awayGames;

    private TournamentDescrTO[] wonTournaments;

    public TournamentTypeNameTO[] getAllowedTournTypes() {
        return allowedTournTypes;
    }

    public void setAllowedTournTypes(TournamentTypeNameTO[] allowedTournTypes) {
        this.allowedTournTypes = allowedTournTypes;
    }

    public GameResultDataTO[] getHomeGames() {
        return homeGames;
    }

    public void setHomeGames(GameResultDataTO[] homeGames) {
        this.homeGames = homeGames;
    }

    public GameResultDataTO[] getAwayGames() {
        return awayGames;
    }

    public void setAwayGames(GameResultDataTO[] awayGames) {
        this.awayGames = awayGames;
    }

    public TournamentDescrTO[] getWonTournaments() {
        return wonTournaments;
    }

    public void setWonTournaments(TournamentDescrTO[] wonTournaments) {
        this.wonTournaments = wonTournaments;
    }
}
