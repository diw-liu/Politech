package com.example.demo.model;

import com.example.demo.model.data.*;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.Polygon;
//import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name="Districts")
public class District {
    private int id;
    private Districting districting;
    private Polygon geometry;
    private List<Precinct> precincts;
//    private Demographic demographics;
//    private List<Election> elections;
//    private List<Integer> neighbors;
//    private List<CensusBlock> censusBlocks;
//    private List<CensusBlock> borderBlocks;

    @Id
    @Column(name="id")
    public int getId() { return id; }
    public void setId(int districtId) { this.id = districtId; }

    @ManyToOne
    @JoinColumn(name="districtingId")
    public Districting getDistricting() { return this.districting; }
    public void setDistricting(Districting d) { this.districting = d; }

    @Column(name="geometry")
    public Polygon getGeometry() { return this.geometry; }
    public void setGeometry(Polygon p) { this.geometry = p; }

    @OneToMany(mappedBy="id", cascade=CascadeType.ALL)
    public List<Precinct> getPrecincts() { return this.precincts; }
    public void setPrecincts(List<Precinct> precincts) { this.precincts = precincts; }

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