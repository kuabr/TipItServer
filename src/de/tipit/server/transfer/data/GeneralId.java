package de.tipit.server.transfer.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GeneralId")
public class GeneralId implements Serializable {

    private static final long serialVersionUID = 4342015191233258716L;

    private Long techId;

    public Long getTechId() {
        return techId;
    }

    public void setTechId(Long techId) {
        this.techId = techId;
    }
}
