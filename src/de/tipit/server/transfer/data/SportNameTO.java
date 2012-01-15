package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SportName")
public class SportNameTO extends SportIdTO {

    private static final long serialVersionUID = -2474099588486065921L;

    private String sportName;

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }
}
