package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TournamentDataBase")
public class TournamentDataBaseTO extends TournamentIdTO {

    private static final long serialVersionUID = -6330543983689379677L;

    private String tournSaison;

    public String getTournSaison() {
        return tournSaison;
    }

    public void setTournSaison(String tournSaison) {
        this.tournSaison = tournSaison;
    }
}
