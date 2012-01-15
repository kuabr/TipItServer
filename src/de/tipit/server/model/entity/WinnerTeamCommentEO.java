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
@Table(name = "WinnerTeamComment")
public class WinnerTeamCommentEO extends CommentEO {

    private TournamentEO tournamentWithWinnerTeam;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("tournamentWithWinnerTeam", tournamentWithWinnerTeam);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "WinnerTeamComment";
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public TournamentEO getTournamentWithWinnerTeam() {
        return tournamentWithWinnerTeam;
    }

    public void setTournamentWithWinnerTeam(TournamentEO tournamentWithWinnerTeam) {
        this.tournamentWithWinnerTeam = tournamentWithWinnerTeam;
    }
}
