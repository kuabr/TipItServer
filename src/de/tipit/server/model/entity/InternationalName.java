package de.tipit.server.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class InternationalName {

    public static final int MAX_NAME_LENGTH = 24;

    private String nameDE;

    private String nameEN;

    @Transient
    @Override
    public String toString() {
        return "DE='" + nameDE + "'; EN='" + nameEN + "'";
    }

    @Column(nullable = false, unique = true, length = MAX_NAME_LENGTH)
    public String getNameDE() {
        return nameDE;
    }

    public void setNameDE(String nameDE) {
        this.nameDE = nameDE;
    }

    @Column(nullable = false, unique = true, length = MAX_NAME_LENGTH)
    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }
}
