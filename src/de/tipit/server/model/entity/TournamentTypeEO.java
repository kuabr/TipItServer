package de.tipit.server.model.entity;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TournamentType")
public class TournamentTypeEO extends EntityRepresentation {

    private Long id;

    private InternationalName tournTypeName;

    private SportEO sport;

    private InternationalDescription description;

    Collection<TournamentEO> tournaments;

    Collection<TeamEO> allowedTeams;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("tournTypeName", tournTypeName);
        data.put("description", description);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "TournamentType";
    }

    @Transient
    @Override
    public String getIdAsString() {
        return this.id.toString();
    }

    @Column
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Embedded
    public InternationalName getTournTypeName() {
        return tournTypeName;
    }

    public void setTournTypeName(InternationalName tournTypeName) {
        this.tournTypeName = tournTypeName;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = false)
    public SportEO getSport() {
        return sport;
    }

    public void setSport(SportEO sport) {
        this.sport = sport;
    }

    @Embedded
    public InternationalDescription getDescription() {
        return description;
    }

    public void setDescription(InternationalDescription description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "tournType", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<TournamentEO> getTournaments() {
        return tournaments;
    }

    public void setTournaments(Collection<TournamentEO> tournaments) {
        this.tournaments = tournaments;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "AllowedTournTypeTeam")
    public Collection<TeamEO> getAllowedTeams() {
        return allowedTeams;
    }

    public void setAllowedTeams(Collection<TeamEO> allowedTeams) {
        this.allowedTeams = allowedTeams;
    }
}
