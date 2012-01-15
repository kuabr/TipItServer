package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserGroupName")
public class UserGroupNameTO extends UserGroupIdTO {

    private static final long serialVersionUID = 8894459168217542457L;

    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
