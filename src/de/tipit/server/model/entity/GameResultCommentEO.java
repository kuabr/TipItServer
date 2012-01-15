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
@Table(name = "GameResultComment")
public class GameResultCommentEO extends CommentEO {

    private GameResultEO gameResult;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("gameResult", gameResult);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "GameResultComment";
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public GameResultEO getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResultEO gameResult) {
        this.gameResult = gameResult;
    }
}
