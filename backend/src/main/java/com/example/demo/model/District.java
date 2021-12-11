package com.example.demo.model;


import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    private String cd;

    private Polygon geometry;

    private Set<District> neighbors;
    private Set<Precinct> borderPrecincts;

    @Id
    @Column(name="id")
    public String getId() { return id; }
    public void setId(String districtId) { this.id = districtId; }

    @ManyToOne
    @JoinColumn(name="districtingId")
    @JsonBackReference
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

    @Column(name="cd")
    public String getCd() { return cd; }
    public void setCd(String c) { cd = c; }

    @ManyToMany
    @JoinTable(
            name="DistrictBorderPrecincts",
            joinColumns = @JoinColumn(name="districtId"),
            inverseJoinColumns = @JoinColumn(name="borderPrecinctId")
    )
    @JsonManagedReference
    public Set<Precinct> getPrecincts() { return this.borderPrecincts; }
    public void setPrecincts(Set<Precinct> precincts) { this.borderPrecincts = precincts; }

//    @OneToMany(mappedBy = "id")
    @ManyToMany
    @JoinTable(
            name="DistrictNeighbors",
            joinColumns = @JoinColumn(name="districtId"),
            inverseJoinColumns = @JoinColumn(name="neighborId")
    )
//    @JsonBackReference
    public Set<District> getNeighbors() { return neighbors; }
    public void setNeighbors(Set<District> n) { neighbors = n; }

    @Transient
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
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