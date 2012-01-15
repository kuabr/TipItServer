package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TournamentSearchData")
public class TournamentSearchDataTO extends TournamentTypeSearchDataTO {

    private static final long serialVersionUID = -1858638262531258339L;

    private String tournSaisonSearchText;

    public String getTournSaisonSearchText() {
        return tournSaisonSearchText;
    }

    public void setTournSaisonSearchText(String tournSaisonSearchText) {
        this.tournSaisonSearchText = tournSaisonSearchText;
    }
}
