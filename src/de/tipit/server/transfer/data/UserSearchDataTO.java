package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

import de.tipit.server.transfer.data.UserDataTO.Gender;

@XmlRootElement(name = "UserSearchData")
public class UserSearchDataTO extends GeneralSearchData {

	private static final long serialVersionUID = -7651134587315457674L;

	private Gender gender;

	private Boolean isInactive;

	private Boolean isDisabled;

	private Boolean isAdmin;

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Boolean getIsInactive() {
		return isInactive;
	}

	public void setIsInactive(Boolean isInactive) {
		this.isInactive = isInactive;
	}

	public Boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
