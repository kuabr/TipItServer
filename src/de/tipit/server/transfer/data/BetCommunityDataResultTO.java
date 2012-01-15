package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BetCommunityDataResult")
public class BetCommunityDataResultTO extends BetCommunityDataBaseTO {

    private static final long serialVersionUID = -8695987461834517991L;

    private RuleBookNameTO ruleBookName;

    public RuleBookNameTO getRuleBookName() {
        return ruleBookName;
    }

    public void setRuleBookName(RuleBookNameTO ruleBookName) {
        this.ruleBookName = ruleBookName;
    }
}
