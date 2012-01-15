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
@Table(name = "GameBetComment")
public class GameBetCommentEO extends CommentEO {

    private GameBetEO gameBet;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("gameBet", gameBet);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "GameBetComment";
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public GameBetEO getGameBet() {
        return gameBet;
    }

    public void setGameBet(GameBetEO gameBet) {
        this.gameBet = gameBet;
    }
}
