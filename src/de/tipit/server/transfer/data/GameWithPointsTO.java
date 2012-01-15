package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GameWithPoints")
public class GameWithPointsTO extends GameTO {

    private static final long serialVersionUID = 204025304132654219L;

    private Integer[] points;

    public Integer[] getPoints() {
        return points;
    }

    public void setPoints(Integer[] points) {
        this.points = points;
    }
}
