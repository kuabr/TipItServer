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
@Table(name = "WinnerBetComment")
public class WinnerBetCommentEO extends CommentEO {

    private WinnerBetEO winnerBet;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("winnerBet", winnerBet);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "WinnerBetComment";
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public WinnerBetEO getWinnerBet() {
        return winnerBet;
    }

    public void setWinnerBet(WinnerBetEO winnerBet) {
        this.winnerBet = winnerBet;
    }
}
