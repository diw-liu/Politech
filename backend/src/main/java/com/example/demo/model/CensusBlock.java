package com.example.demo.model;

import javax.persistence.*;

import java.util.List;
import org.locationtech.jts.geom.*;

@Entity
@Table(name="CensusBlocks")
public class CensusBlock {
    private String id;
    private District district;
    private Precinct precinct;
    private List<CensusBlock> neighbors;
    private List<Population> populations;
    private Polygon geometry;
    private List<Election> elections;
    private boolean border;

    @Id
    @Column(name="id")
    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

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
            name="CensusBlockNeighbors",
            joinColumns = @JoinColumn(name="blockId"),
            inverseJoinColumns = @JoinColumn(name="neighborId")
    )
    public List<CensusBlock> getNeighbors() { return neighbors; }
    public void setNeighbors(List<CensusBlock> n) { neighbors = n; }

    @OneToMany
    @JoinTable(
            name="CensusBlockPopulations",
            joinColumns = @JoinColumn(name="blockId"),
            inverseJoinColumns = @JoinColumn(name="populationId"))
    public List<Population> getPopulations() { return this.populations; }
    public void setPopulations(List<Population> p) { populations = p; }

    @Column(name="geometry")
    public Polygon getGeometry() { return geometry; }
    public void setGeometry(Polygon geometry) { this.geometry = geometry; }

    @OneToMany
    @JoinTable(
            name="CensusBlockElections",
            joinColumns = @JoinColumn(name="blockId"),
            inverseJoinColumns = @JoinColumn(name="electionId"))
    public List<Election> getElections() { return this.elections; }
    public void setElections(List<Election> e) { elections = e; }

    @Column(name="borderStatus")
    public boolean getBorder() { return border; }
    public void setBorder(boolean b) { border = b; }
































    //TODO
    // public CensusBlock findDistrictNeighbor(District d){

    // }

}