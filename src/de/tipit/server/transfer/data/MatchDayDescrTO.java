package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MatchDayDescr")
public class MatchDayDescrTO extends MatchDayIdTO {

	private static final long serialVersionUID = -3921629462015552571L;

	private String displayDescr;

	public String getDisplayDescr() {
		return displayDescr;
	}

	public void setDisplayDescr(String displayDescr) {
		this.displayDescr = displayDescr;
	}
}
