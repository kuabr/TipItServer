package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TournamentTypeSearchData")
public class TournamentTypeSearchDataTO extends GeneralSearchData {

    private static final long serialVersionUID = 267582023925367809L;

    private SportIdTO sportId;

    public SportIdTO getSportId() {
        return sportId;
    }

    public void setSportId(SportIdTO sportId) {
        this.sportId = sportId;
    }
}
