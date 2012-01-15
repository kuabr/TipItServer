package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RuleBookName")
public class RuleBookNameTO extends RuleBookIdTO {

	private static final long serialVersionUID = 9076831238652033880L;

	private String displayName;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
