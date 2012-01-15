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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Sport")
public class SportEO extends EntityRepresentation {

    private Long id;

    private InternationalName sportName;

    private InternationalDescription description;

    private Collection<TournamentTypeEO> tournTypes;

    @Transient
    @Override
    public SortedMap<String, Object> getDataAsMap() {
        SortedMap<String, Object> data = new TreeMap<String, Object>();

        data.put("sportName", sportName);
        data.put("description", description);

        return data;
    }

    @Transient
    @Override
    public String getEntityName() {
        return "Sport";
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
    public InternationalName getSportName() {
        return sportName;
    }

    public void setSportName(InternationalName sportName) {
        this.sportName = sportName;
    }

    @Embedded
    public InternationalDescription getDescription() {
        return description;
    }

    public void setDescription(InternationalDescription description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "sport", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public Collection<TournamentTypeEO> getTournTypes() {
        return tournTypes;
    }

    public void setTournTypes(Collection<TournamentTypeEO> tournTypes) {
        this.tournTypes = tournTypes;
    }
}
