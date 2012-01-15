package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TournamentTypeDataBase")
public class TournamentTypeDataBaseTO extends TournamentTypeNameTO {

    private static final long serialVersionUID = -94291104609905035L;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
