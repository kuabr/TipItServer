package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TournamentTypeName")
public class TournamentTypeNameTO extends TournamentTypeIdTO {

    private static final long serialVersionUID = -8536328991650235812L;

    private String tournTypeName;

    public String getTournTypeName() {
        return tournTypeName;
    }

    public void setTournTypeName(String tournTypeName) {
        this.tournTypeName = tournTypeName;
    }
}
