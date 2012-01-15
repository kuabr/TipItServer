package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserPoints")
public class UserPointsTO extends UserIdTO {

    private static final long serialVersionUID = -9141166623954048634L;

    private Integer userPoints;

    private Integer nrOfBets;

    public Integer getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(Integer userPoints) {
        this.userPoints = userPoints;
    }

    public Integer getNrOfBets() {
        return nrOfBets;
    }

    public void setNrOfBets(Integer nrOfBets) {
        this.nrOfBets = nrOfBets;
    }
}
