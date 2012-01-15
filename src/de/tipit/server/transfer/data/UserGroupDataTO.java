package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserGroupData")
public class UserGroupDataTO extends UserGroupNameTO {

	private static final long serialVersionUID = -5859452180145000282L;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
