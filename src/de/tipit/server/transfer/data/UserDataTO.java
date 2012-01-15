package de.tipit.server.transfer.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserData")
public class UserDataTO extends UserIdTO {

	private static final long serialVersionUID = -6738688275820170189L;

	public static enum Gender {
		FEMALE, MALE
	}

	private UserAccountTO account;

	private UserContactTO contact;

	private String fullPreName;

	private String fullSurName;

	private Gender gender;

	private Date birthday;

	public UserAccountTO getAccount() {
		return account;
	}

	public void setAccount(UserAccountTO account) {
		this.account = account;
	}

	public UserContactTO getContact() {
		return contact;
	}

	public void setContact(UserContactTO contact) {
		this.contact = contact;
	}

	public String getFullPreName() {
		return fullPreName;
	}

	public void setFullPreName(String fullPreName) {
		this.fullPreName = fullPreName;
	}

	public String getFullSurName() {
		return fullSurName;
	}

	public void setFullSurName(String fullSurName) {
		this.fullSurName = fullSurName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
