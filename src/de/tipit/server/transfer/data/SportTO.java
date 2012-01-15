package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Sport")
public class SportTO extends SportDataTO {

    private static final long serialVersionUID = 1743976254937120894L;

    private TournamentTypeNameTO[] tournTypes;

    public TournamentTypeNameTO[] getTournTypes() {
        return tournTypes;
    }

    public void setTournTypes(TournamentTypeNameTO[] tournTypes) {
        this.tournTypes = tournTypes;
    }
}
