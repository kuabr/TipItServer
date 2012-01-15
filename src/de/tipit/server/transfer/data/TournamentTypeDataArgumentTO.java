package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TournamentTypeDataArgument")
public class TournamentTypeDataArgumentTO extends TournamentTypeDataBaseTO {

    private static final long serialVersionUID = -4444179226063296879L;

    private SportIdTO sportId;

    public SportIdTO getSportId() {
        return sportId;
    }

    public void setSportId(SportIdTO sportId) {
        this.sportId = sportId;
    }
}
