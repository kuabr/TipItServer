package de.tipit.server.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class InternationalDescription {

    public static final int MAX_DESCRIPTION_LENGTH = 768;

    private String descrDE;

    private String descrEN;

    @Transient
    @Override
    public String toString() {
        return "DE='" + descrDE + "'; EN='" + descrEN + "'";
    }

    @Column(nullable = true, unique = false, length = MAX_DESCRIPTION_LENGTH)
    public String getDescrDE() {
        return descrDE;
    }

    public void setDescrDE(String descrDE) {
        this.descrDE = descrDE;
    }

    @Column(nullable = true, unique = false, length = MAX_DESCRIPTION_LENGTH)
    public String getDescrEN() {
        return descrEN;
    }

    public void setDescrEN(String descrEN) {
        this.descrEN = descrEN;
    }
}
