package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BetCommunityDataBase")
public class BetCommunityDataBaseTO extends BetCommunityNameTO {

    private static final long serialVersionUID = -5765029746377113486L;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
