package com.example.demo.model;

import javax.persistence.*;

import java.util.List;
//import org.locationtech.jts.geom.*;
import java.awt.*;

@Entity
@Table(name="Precincts")
public class Precinct {
    private String id;
    private Polygon geometry;
    private District district;
//    private List<Population> populations;
    private Population populations;
    private VotingAgePopulation vap;
//    private List<Election> elections;
    private Election election;
    private List<Precinct> neighbors;
    private Boolean hasChanged;

    @Id
    @Column(name="id")
    public String getPrecinctId() { return id; }
    public void setPrecinctId(String id) { this.id = id; }

    @Column(name="geometry")
    public Polygon getGeometry() { return geometry; }
    public void setGeometry(Polygon geometry) { this.geometry = geometry; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="districtId")
    public District getDistrict() { return district; }
    public void setDistrict(District d) { district = d; }

//    @OneToMany
//    @JoinTable(
//            name="PrecinctPopulations",
//            joinColumns = @JoinColumn(name="precinctId"),
//            inverseJoinColumns = @JoinColumn(name="populationId")
//    )
//    public List<Population> getPopulations() { return this.populations; }
//    public void setPopulations(List<Population> p) { populations = p; }

    @OneToOne
    @JoinColumn(name="populationId")
    public Population getPopulations() { return this.populations; }
    public void setPopulations(Population p) { populations = p; }

    @OneToOne
    @JoinColumn(name="vapId")
    public VotingAgePopulation getVap() { return vap; }
    public void setVap(VotingAgePopulation vap) { this.vap = vap; }

//    @OneToMany
//    @JoinTable(
//            name="PrecinctElections",
//            joinColumns = @JoinColumn(name="precinctId"),
//            inverseJoinColumns = @JoinColumn(name="electionId")
//    )
//    public List<Election> getElections() { return this.elections; }
//    public void setElections(List<Election> e) { elections = e; }
    @OneToOne
    @JoinColumn(name="electionId")
    public Election getElection() { return election; }
    public void setElection(Election e) { election = e; }

    @OneToMany
    @JoinTable(
            name="PrecinctNeighbors",
            joinColumns = @JoinColumn(name="precinctId"),
            inverseJoinColumns = @JoinColumn(name="neighborId")
    )
    public List<Precinct> getNeighbors() { return neighbors; }
    public void setNeighbors(List<Precinct> n) { neighbors = n; }

    @Transient
    public Boolean getHasChanged() { return hasChanged; }
    public void setHasChanged(Boolean b) { hasChanged = b; }

//    //TODO
//    public void changeBoundary(Polygon blockGeometry){
//
//    }
}