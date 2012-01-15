package de.tipit.server.transfer.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GameBet")
public class GameBetTO extends GameBetDataWithoutGameTO {

	private static final long serialVersionUID = 2459667331589637896L;

	private UserNameTO user;

	private CommentTO[] comments;

	private Date creation;

	public UserNameTO getUser() {
		return user;
	}

	public void setUser(UserNameTO user) {
		this.user = user;
	}

	public CommentTO[] getComments() {
		return comments;
	}

	public void setComments(CommentTO[] comments) {
		this.comments = comments;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}
}
