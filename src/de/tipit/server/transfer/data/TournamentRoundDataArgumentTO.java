package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TournamentRoundDataArgument")
public class TournamentRoundDataArgumentTO extends TournamentRoundDataBaseTO {

    private static final long serialVersionUID = 6939802802900644026L;

    private TournamentIdTO tournId;

    public TournamentIdTO getTournId() {
        return tournId;
    }

    public void setTournId(TournamentIdTO tournId) {
        this.tournId = tournId;
    }
}
