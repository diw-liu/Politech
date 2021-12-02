package com.example.demo.model;


import javax.persistence.*;

import java.util.List;
import org.locationtech.jts.geom.*;
//import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name="Districts")
public class District {
    private String id;
    private Districting districting;
    private Polygon geometry;
    private List<Precinct> precincts;
//    private List<Population> populations;
    private Population population;
    private VotingAgePopulation vap;
//    private List<Election> elections;
    private Election election;
    private List<District> neighbors;
    private List<CensusBlock> censusBlocks;
    private List<CensusBlock> borderBlocks;

    @Id
    @Column(name="id")
    public String getId() { return id; }
    public void setId(String districtId) { this.id = districtId; }

    @ManyToOne
    @JoinColumn(name="districtingId")
    public Districting getDistricting() { return this.districting; }
    public void setDistricting(Districting d) { this.districting = d; }

    @Column(name="geometry")
    public Polygon getGeometry() { return this.geometry; }
    public void setGeometry(Polygon p) { this.geometry = p; }

    @OneToMany(mappedBy="district", cascade=CascadeType.ALL)
    public List<Precinct> getPrecincts() { return this.precincts; }
    public void setPrecincts(List<Precinct> precincts) { this.precincts = precincts; }

//    @OneToMany
//    @JoinTable(
//            name="DistrictPopulations",
//            joinColumns = @JoinColumn(name="districtId"),
//            inverseJoinColumns = @JoinColumn(name="populationId")
//    )
//    public List<Population> getPopulations() { return this.populations; }
//    public void setPopulations(List<Population> p) { populations = p; }

    @OneToOne
    @JoinColumn(name="populationId")
    public Population getPopulation() { return this.population; }
    public void setPopulation(Population p) { population = p; }

    @OneToOne
    @JoinColumn(name="vapId")
    public VotingAgePopulation getVap() { return vap; }
    public void setVap(VotingAgePopulation vap) { this.vap = vap; }

//    @OneToMany
//    @JoinTable(
//            name="DistrictElections",
//            joinColumns = @JoinColumn(name="districtId"),
//            inverseJoinColumns = @JoinColumn(name="electionId")
//    )
//    public List<Election> getElections() { return this.elections; }
//    public void setElections(List<Election> e) { elections = e; }

    @OneToOne
    @JoinColumn(name="electionId")
    public Election getElection() { return election; }
    public void setElection(Election e) { election = e; }

//    @OneToMany(mappedBy = "id")
    @OneToMany
    @JoinTable(
            name="DistrictNeighbors",
            joinColumns = @JoinColumn(name="districtId"),
            inverseJoinColumns = @JoinColumn(name="neighborId")
    )
    public List<District> getNeighbors() { return neighbors; }
    public void setNeighbors(List<District> n) { neighbors = n; }

    @OneToMany(mappedBy="district", cascade=CascadeType.ALL)
    public List<CensusBlock> getCensusBlocks() { return this.censusBlocks; }
    public void setCensusBlocks(List<CensusBlock> cb) { this.censusBlocks = cb; }

    @OneToMany
    @JoinTable(
            name="DistrictBorderBlocks",
            joinColumns = @JoinColumn(name="districtId"),
            inverseJoinColumns = @JoinColumn(name="borderBlockId")
    )
    public List<CensusBlock> getBorderBlocks() { return borderBlocks; }
    public void setBorderBlocks(List<CensusBlock> b) { borderBlocks = b; }

    // //TODO
    // public CensusBlock selectRandomBlock(){

    // }

    //TODO
//    public void giveBlock(District givenTo, CensusBlock toGive){
//
//    }

    // //TODO
    // public List<Precinct> getChangedPrecincts(){

    // }

}