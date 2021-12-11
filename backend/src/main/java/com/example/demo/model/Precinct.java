package com.example.demo.model;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

    private boolean hasChanged = false;
    private District parentDistrict;
    private Geometry geometry;
    private String county;

    private Set<Precinct> neighbors;
    private Set<CensusBlock> censusBlocks;
    private Set<CensusBlock> borderBlocks; // border census blocks are blocks that are borders of the DISTRICT
    private District district;

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

    @ManyToMany
    @JoinTable(
            name="PrecinctNeighbors",
            joinColumns = @JoinColumn(name="precinctId"),
            inverseJoinColumns = @JoinColumn(name="neighborId")
    )
    @JsonIgnore
    public Set<Precinct> getNeighbors() { return neighbors; }
    public void setNeighbors(Set<Precinct> n) { neighbors = n; }

    @OneToMany
    @JoinTable(
            name="PrecinctCensusBlocks",
            joinColumns = @JoinColumn(name="precinctId"),
            inverseJoinColumns = @JoinColumn(name="censusBlockId")
    )
    @JsonManagedReference
    public Set<CensusBlock> getCensusBlocks() { return censusBlocks; }
    public void setCensusBlocks(Set<CensusBlock> n) { censusBlocks = n; }

    @OneToMany
    @JoinTable(
            name="PrecinctBorderBlocks",
            joinColumns = @JoinColumn(name="precinctId"),
            inverseJoinColumns = @JoinColumn(name="borderBlockId")
    )
    @JsonManagedReference
    public Set<CensusBlock> getBorderBlocks() { return borderBlocks; }
    public void setBorderBlocks(Set<CensusBlock> n) { borderBlocks = n; }

    @Transient
    public District getParentDistrict() { return parentDistrict; }
    public void setParentDistrict(District d) { parentDistrict = d; }

    @Transient
    public Boolean getHasChanged() { return hasChanged; }
    public void setHasChanged(Boolean b) { hasChanged = b; }

    @Transient
//    @JsonSerialize(using = GeometrySerializer.class)
//    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    @JsonIgnore
    public Geometry getGeometry() { return geometry; }
    public void setGeometry(Geometry p) { geometry = p; }

    @Column(name="county")
    public String getCounty() { return county; }
    public void setCounty(String county) { this.county = county; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="districtId")
    @JsonBackReference
    public District getDistrict() { return district; }
    public void setDistrict(District d) { district = d; }

    public Geometry convertStringToGeometry() {
        try {
            WKTReader reader = new WKTReader();
            Geometry p = reader.read(this.getGeometryString());
            this.setGeometry(p);
            return p;
        } catch (Exception e){
            System.out.println("Error reading Precinct LONGTEXT to Polygon using JTS");
            return null;
        }
    }
}