package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TournamentTypeDataResult")
public class TournamentTypeDataResultTO extends TournamentTypeDataBaseTO {

    private static final long serialVersionUID = 2297154400524765785L;

    private SportNameTO sportName;

    public SportNameTO getSportName() {
        return sportName;
    }

    public void setSportName(SportNameTO sportName) {
        this.sportName = sportName;
    }
}
