package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BetCommunityName")
public class BetCommunityNameTO extends BetCommunityIdTO {

    private static final long serialVersionUID = -3923074380093053693L;

    private String communityName;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
