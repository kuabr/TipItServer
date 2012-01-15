package de.tipit.server.transfer.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SessionId")
public class SessionIdTO implements Serializable {

	private static final long serialVersionUID = -5796145288888443173L;

	private String sessionId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
