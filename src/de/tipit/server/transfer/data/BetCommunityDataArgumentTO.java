package de.tipit.server.transfer.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BetCommunityDataArgument")
public class BetCommunityDataArgumentTO extends BetCommunityDataBaseTO {

    private static final long serialVersionUID = 6151709897269352099L;

    private RuleBookIdTO ruleBookId;

    public RuleBookIdTO getRuleBookId() {
        return ruleBookId;
    }

    public void setRuleBookId(RuleBookIdTO ruleBookId) {
        this.ruleBookId = ruleBookId;
    }
}
