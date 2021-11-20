package com.example.demo.model;

import com.example.demo.model.data.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.Polygon;

@Entity
@Table(name="CensusBlocks")
public class CensusBlock {
    private int id;
    private District district;
    private Precinct precinct;
    private List<Population> populations;
    private Polygon geometry;
    private List<Election> elections;
//    private Boolean isBorder;

    @Id
    @Column(name="id")
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="districtId")
    public District getDistrict() { return district; }
    public void setDistrict(District d) { district = d; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="precinctId")
    public Precinct getPrecinct() { return precinct; }
    public void setPrecinct(Precinct p) { precinct = p; }

    @OneToMany
    @JoinTable(
            name="CensusBlockPopulations",
            joinColumns = @JoinColumn(name="censusBlockId"),
            inverseJoinColumns = @JoinColumn(name="populationId"))
    public List<Population> getPopulations() { return this.populations; }
    public void setPopulations(List<Population> p) { populations = p; }

    @Column(name="geometry")
    public Polygon getGeometry() { return geometry; }
    public void setGeometry(Polygon geometry) { this.geometry = geometry; }

    @OneToMany
    @JoinTable(
            name="CensusBlockElections",
            joinColumns = @JoinColumn(name="censusBlockId"),
            inverseJoinColumns = @JoinColumn(name="electionId"))
    public List<Election> getElections() { return this.elections; }
    public void setElections(List<Election> e) { elections = e; }
































    //TODO
    // public CensusBlock findDistrictNeighbor(District d){

    // }

}