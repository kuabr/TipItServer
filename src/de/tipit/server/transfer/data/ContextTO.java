package de.tipit.server.transfer.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Context")
public class ContextTO implements Serializable {

    private static final long serialVersionUID = -7961232298529384731L;

    public static enum Language {
        DE, EN
    }

    private Language language;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
