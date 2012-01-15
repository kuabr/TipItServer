package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserGroupPoints")
public class UserGroupPointsTO extends UserGroupIdTO {

    private static final long serialVersionUID = -8859558021486293006L;

    private Float averageUserPoints;

    private Float averageNrOfBets;

    public Float getAverageUserPoints() {
        return averageUserPoints;
    }

    public void setAverageUserPoints(Float averageUserPoints) {
        this.averageUserPoints = averageUserPoints;
    }

    public Float getAverageNrOfBets() {
        return averageNrOfBets;
    }

    public void setAverageNrOfBets(Float averageNrOfBets) {
        this.averageNrOfBets = averageNrOfBets;
    }
}
