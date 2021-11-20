package com.example.demo.model;

import com.example.demo.model.data.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.Polygon;

@Entity
@Table(name="Precincts")
public class Precinct {
    private int id;
    private Polygon geometry;
    private District district;
    private List<Population> populations;
    private List<Election> elections;
    private List<Precinct> neighbors;
    private Boolean hasChanged;

    @Id
    public int getPrecinctId() { return id; }
    public void setPrecinctId(int id) { this.id = id; }

    @Column(name="geometry")
    public Polygon getGeometry() { return geometry; }
    public void setGeometry(Polygon geometry) { this.geometry = geometry; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="districtId")
    public District getDistrict() { return district; }
    public void setDistrict(District d) { district = d; }

    @OneToMany
    @JoinTable(
            name="PrecinctPopulations",
            joinColumns = @JoinColumn(name="precinctId"),
            inverseJoinColumns = @JoinColumn(name="populationId")
    )
    public List<Population> getPopulations() { return this.populations; }
    public void setPopulations(List<Population> p) { populations = p; }

    @OneToMany
    @JoinTable(
            name="PrecinctElections",
            joinColumns = @JoinColumn(name="precinctId"),
            inverseJoinColumns = @JoinColumn(name="electionId")
    )
    public List<Election> getElections() { return this.elections; }
    public void setElections(List<Election> e) { elections = e; }

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