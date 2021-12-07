package com.example.demo.model;


import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.WKTReader;
//import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name="Districts")
public class District {
    private String id;
    private Districting districting;
    private String geometryString;
    private Population population;
    private VotingAgePopulation vap;
    private Election election;

    private Polygon geometry;

    private Set<District> neighbors;
    private Set<Precinct> borderPrecincts;
//    private List<CensusBlock> censusBlocks;
//    private List<CensusBlock> borderBlocks;

//    private List<Population> populations;
//    private List<Election> elections;

    @Id
    @Column(name="id")
    public String getId() { return id; }
    public void setId(String districtId) { this.id = districtId; }

    @ManyToOne
    @JoinColumn(name="districtingId")
    public Districting getDistricting() { return this.districting; }
    public void setDistricting(Districting d) { this.districting = d; }

    @Column(name="geometry", columnDefinition = "LONGTEXT")
    public String getGeometryString() { return this.geometryString; }
    public void setGeometryString(String p) { this.geometryString = p; }

    @OneToOne
    @JoinColumn(name="populationId")
    public Population getPopulation() { return this.population; }
    public void setPopulation(Population p) { population = p; }

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
            name="DistrictBorderPrecincts",
            joinColumns = @JoinColumn(name="districtId"),
            inverseJoinColumns = @JoinColumn(name="borderPrecinctId")
    )
    public Set<Precinct> getPrecincts() { return this.borderPrecincts; }
    public void setPrecincts(Set<Precinct> precincts) { this.borderPrecincts = precincts; }

//    @OneToMany(mappedBy = "id")
    @OneToMany
    @JoinTable(
            name="DistrictNeighbors",
            joinColumns = @JoinColumn(name="districtId"),
            inverseJoinColumns = @JoinColumn(name="neighborId")
    )
    public Set<District> getNeighbors() { return neighbors; }
    public void setNeighbors(Set<District> n) { neighbors = n; }

//    @OneToMany
//    @JoinTable(
//            name="DistrictPopulations",
//            joinColumns = @JoinColumn(name="districtId"),
//            inverseJoinColumns = @JoinColumn(name="populationId")
//    )
//    public List<Population> getPopulations() { return this.populations; }
//    public void setPopulations(List<Population> p) { populations = p; }

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

//    @OneToMany(mappedBy="district", cascade=CascadeType.ALL)
//    public List<CensusBlock> getCensusBlocks() { return this.censusBlocks; }
//    public void setCensusBlocks(List<CensusBlock> cb) { this.censusBlocks = cb; }
//
//    @OneToMany
//    @JoinTable(
//            name="DistrictBorderBlocks",
//            joinColumns = @JoinColumn(name="districtId"),
//            inverseJoinColumns = @JoinColumn(name="borderBlockId")
//    )
//    public List<CensusBlock> getBorderBlocks() { return borderBlocks; }
//    public void setBorderBlocks(List<CensusBlock> b) { borderBlocks = b; }



//    @OneToMany
//    @JoinTable(
//            name="DistrictElections",
//            joinColumns = @JoinColumn(name="districtId"),
//            inverseJoinColumns = @JoinColumn(name="electionId")
//    )
//    public List<Election> getElections() { return this.elections; }
//    public void setElections(List<Election> e) { elections = e; }

    //TODO
//    public CensusBlock selectRandomCensusBlock(){
//        int size = this.censusBlocks.size();
//        Random rand = new Random();
//        return censusBlocks.get(rand.nextInt(size));
//    }


    //TODO
//    public void giveBlock(District givenTo, CensusBlock toGive){
//
//    }

    // //TODO
    // public List<Precinct> getChangedPrecincts(){

    // }

}