package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MatchDayDataBase")
public class MatchDayDataBaseTO extends MatchDayIdTO {

    private static final long serialVersionUID = 7684534010395145389L;

    private Integer day;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
