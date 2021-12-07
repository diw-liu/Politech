package com.example.demo.model;

import org.locationtech.jts.io.WKTReader;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.locationtech.jts.geom.*;
//import java.awt.*;

@Entity
@Table(name="Precincts")
public class Precinct {
    private String id;
    private String geometryString;
    private Population populations;
    private VotingAgePopulation vap;
    private Election election;

    private Boolean hasChanged;
    private District parentDistrict;
    private Polygon geometry;

    private Set<Precinct> neighbors;
    private Set<CensusBlock> censusBlocks;
    private Set<CensusBlock> borderBlocks; // border census blocks are blocks that are borders of the DISTRICT
//    private District district;
//    private List<Population> populations;
//    private List<Election> elections;

    @Id
    @Column(name="id")
    public String getPrecinctId() { return id; }
    public void setPrecinctId(String id) { this.id = id; }

    @Column(name="geometry", columnDefinition = "LONGTEXT")
    public String getGeometryString() { return geometryString; }
    public void setGeometryString(String gs) { this.geometryString = gs; }

    @OneToOne
    @JoinColumn(name="populationId")
    public Population getPopulations() { return this.populations; }
    public void setPopulations(Population p) { populations = p; }

    @OneToOne
    @JoinColumn(name="vapId")
    public VotingAgePopulation getVap() { return vap; }
    public void setVap(VotingAgePopulation vap) { this.vap = vap; }

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
    public Set<Precinct> getNeighbors() { return neighbors; }
    public void setNeighbors(Set<Precinct> n) { neighbors = n; }

    @ManyToMany
    @JoinTable(
            name="PrecinctCensusBlocks",
            joinColumns = @JoinColumn(name="precinctId"),
            inverseJoinColumns = @JoinColumn(name="censusBlockId")
    )
    public Set<CensusBlock> getCensusBlocks() { return censusBlocks; }
    public void setCensusBlocks(Set<CensusBlock> n) { censusBlocks = n; }

    @OneToMany
    @JoinTable(
            name="PrecinctBorderBlocks",
            joinColumns = @JoinColumn(name="precinctId"),
            inverseJoinColumns = @JoinColumn(name="borderBlockId")
    )
    public Set<CensusBlock> getBorderBlocks() { return borderBlocks; }
    public void setBorderBlocks(Set<CensusBlock> n) { borderBlocks = n; }

//    @OneToMany
//    @JoinTable(
//            name="PrecinctPopulations",
//            joinColumns = @JoinColumn(name="precinctId"),
//            inverseJoinColumns = @JoinColumn(name="populationId")
//    )
//    public List<Population> getPopulations() { return this.populations; }
//    public void setPopulations(List<Population> p) { populations = p; }

    @Transient
    public District getParentDistrict() { return parentDistrict; }
    public void setParentDistrict(District d) { parentDistrict = d; }

    @Transient
    public Boolean getHasChanged() { return hasChanged; }
    public void setHasChanged(Boolean b) { hasChanged = b; }

    @Transient
    public Polygon getGeometry() { return geometry; }
    public void setGeometry(Polygon p) { geometry = p; }

    public Polygon convertStringToPolygon() {
        try {
            WKTReader reader = new WKTReader();
            Polygon p = (Polygon) reader.read(this.getGeometryString());
            this.setGeometry(p);
            return p;
        } catch (Exception e){
            System.out.println("Error reading Precinct LONGTEXT to Polygon using JTS");
            return null;
        }
    }

//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="districtId")
//    public District getDistrict() { return district; }
//    public void setDistrict(District d) { district = d; }



//    @OneToMany
//    @JoinTable(
//            name="PrecinctElections",
//            joinColumns = @JoinColumn(name="precinctId"),
//            inverseJoinColumns = @JoinColumn(name="electionId")
//    )
//    public List<Election> getElections() { return this.elections; }
//    public void setElections(List<Election> e) { elections = e; }

//    //TODO
//    public void changeBoundary(Polygon blockGeometry){
//
//    }
}