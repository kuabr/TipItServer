package de.tipit.server.transfer.data;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Period")
public class PeriodTO implements Serializable {

    private static final long serialVersionUID = -3456176005291103784L;

    private Date first;

    private Date last;

    public Date getFirst() {
        return first;
    }

    public void setFirst(Date first) {
        this.first = first;
    }

    public Date getLast() {
        return last;
    }

    public void setLast(Date last) {
        this.last = last;
    }
}
