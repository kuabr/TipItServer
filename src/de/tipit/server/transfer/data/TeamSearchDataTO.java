package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TeamSearchData")
public class TeamSearchDataTO extends GeneralSearchData {

	private static final long serialVersionUID = 7445218807085387509L;

	private Boolean searchInAbbreviations;

	private Boolean searchInNames;

	public Boolean getSearchInAbbreviations() {
		return searchInAbbreviations;
	}

	public void setSearchInAbbreviations(Boolean searchInAbbreviations) {
		this.searchInAbbreviations = searchInAbbreviations;
	}

	public Boolean getSearchInNames() {
		return searchInNames;
	}

	public void setSearchInNames(Boolean searchInNames) {
		this.searchInNames = searchInNames;
	}
}
