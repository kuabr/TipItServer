package de.tipit.server.model.entity;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "RuleBookComment")
public class RuleBookCommentEO extends CommentEO {

    private RuleBookEO ruleBook;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("ruleBook", ruleBook);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "RuleBookComment";
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public RuleBookEO getRuleBook() {
        return ruleBook;
    }

    public void setRuleBook(RuleBookEO ruleBook) {
        this.ruleBook = ruleBook;
    }
}
