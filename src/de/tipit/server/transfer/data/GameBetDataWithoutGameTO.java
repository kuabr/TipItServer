package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GameBetDataWithoutGame")
public class GameBetDataWithoutGameTO extends GameBetIdTO {

	private static final long serialVersionUID = 4315950371533383209L;

	private Integer homeResult;

	private Integer awayResult;

	public Integer getHomeResult() {
		return homeResult;
	}

	public void setHomeResult(Integer homeResult) {
		this.homeResult = homeResult;
	}

	public Integer getAwayResult() {
		return awayResult;
	}

	public void setAwayResult(Integer awayResult) {
		this.awayResult = awayResult;
	}
}
