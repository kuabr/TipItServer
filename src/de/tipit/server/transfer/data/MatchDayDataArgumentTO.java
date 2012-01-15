package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MatchDayDataArgument")
public class MatchDayDataArgumentTO extends MatchDayDataBaseTO {

    private static final long serialVersionUID = 5408039480293433656L;

    private TournamentRoundIdTO tournRoundId;

    public TournamentRoundIdTO getTournRoundId() {
        return tournRoundId;
    }

    public void setTournRoundId(TournamentRoundIdTO tournRoundId) {
        this.tournRoundId = tournRoundId;
    }
}
