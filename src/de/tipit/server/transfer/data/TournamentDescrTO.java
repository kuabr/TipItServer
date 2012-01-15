package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TournamentDescr")
public class TournamentDescrTO extends TournamentIdTO {

	private static final long serialVersionUID = -8350957127283138922L;

	private String displayDescr;

	public String getDisplayDescr() {
		return displayDescr;
	}

	public void setDisplayDescr(String displayDescr) {
		this.displayDescr = displayDescr;
	}
}
