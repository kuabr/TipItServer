package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TournamentRoundDataResult")
public class TournamentRoundDataResultTO extends TournamentRoundDataBaseTO {

    private static final long serialVersionUID = -1116818463211195472L;

    private TournamentDescrTO tournDescr;

    public TournamentDescrTO getTournDescr() {
        return tournDescr;
    }

    public void setTournDescr(TournamentDescrTO tournDescr) {
        this.tournDescr = tournDescr;
    }
}
