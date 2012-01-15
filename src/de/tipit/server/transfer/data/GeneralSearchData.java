package de.tipit.server.transfer.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GeneralSearchData")
public class GeneralSearchData implements Serializable {

	private static final long serialVersionUID = 6418998867517274757L;

	Integer maxNrOfResults;

	String searchText;

	public Integer getMaxNrOfResults() {
		return maxNrOfResults;
	}

	public void setMaxNrOfResults(Integer maxNrOfResults) {
		this.maxNrOfResults = maxNrOfResults;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
