package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TeamName")
public class TeamNameTO extends TeamIdTO {

	private static final long serialVersionUID = 1550625859549193271L;

	private String shortDisplayName;

	private String displayName;

	public String getShortDisplayName() {
		return shortDisplayName;
	}

	public void setShortDisplayName(String shortDisplayName) {
		this.shortDisplayName = shortDisplayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
