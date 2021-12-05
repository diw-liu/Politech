package com.example.demo.model;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

@Entity
@Table(name="CensusBlocks")
public class CensusBlock {
    private String id;
    private Precinct precinct;
//    private Population population;
    // doesnt matter now but forgot VAP class
    private String geometryString;
    private Election election;

    private Precinct parentPrecinct;
    private District parentDistrict;
    private Polygon geometry;
    private boolean border;

    private Set<CensusBlock> neighbors;

//    private District district;
//    private List<Election> elections;
    private List<Population> populations;

    @Id
    @Column(name="id")
    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="precinctId")
    public Precinct getPrecinct() { return precinct; }
    public void setPrecinct(Precinct p) { precinct = p; }

    @OneToMany
    @JoinTable(
            name="CensusBlockNeighbors",
            joinColumns = @JoinColumn(name="blockId"),
            inverseJoinColumns = @JoinColumn(name="neighborId")
    )
    public Set<CensusBlock> getNeighbors() { return neighbors; }
    public void setNeighbors(Set<CensusBlock> n) { neighbors = n; }

//    @OneToOne
//    @JoinColumn(name="populationId")
//    public Population getPopulation() { return this.population; }
//    public void setPopulation(Population p) { population = p; }

    @Column(name="geometry", columnDefinition = "LONGTEXT")
    public String getGeometryString() { return geometryString; }
    public void setGeometryString(String gs) { this.geometryString = gs; }

    @OneToOne
    @JoinColumn(name="electionId")
    public Election getElection() { return election; }
    public void setElection(Election e) { election = e; }

    @OneToMany
    @JoinTable(
            name="CensusBlockPopulations",
            joinColumns = @JoinColumn(name="blockId"),
            inverseJoinColumns = @JoinColumn(name="populationId"))
    public List<Population> getPopulations() { return this.populations; }
    public void setPopulations(List<Population> p) { populations = p; }

//    @Column(name="borderStatus")
    @Transient
    public boolean getBorder() { return border; }
    public void setBorder(boolean b) { border = b; }

    @Transient
    public Precinct getParentPrecinct() { return parentPrecinct; }
    public void setParentPrecinct(Precinct p) { parentPrecinct = p; }

    @Transient
    public District getParentDistrict() { return parentDistrict; }
    public void setParentDistrict(District d) { parentDistrict = d; }

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
            System.out.println("Error reading Census Block LONGTEXT to Polygon using JTS");
            return null;
        }
    }



//    public Polygon getGeometry() {
//        try {
//            WKTReader reader = new WKTReader();
//            return (Polygon) reader.read(geometryString);
//        } catch (ParseException ex) {
//            return null;
//        }
//    }

//    @OneToMany
//    @JoinTable(
//            name="CensusBlockElections",
//            joinColumns = @JoinColumn(name="blockId"),
//            inverseJoinColumns = @JoinColumn(name="electionId"))
//    public List<Election> getElections() { return this.elections; }
//    public void setElections(List<Election> e) { elections = e; }































    //TODO
    // public CensusBlock findDistrictNeighbor(District d){

    // }

}