package com.example.demo.model;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

@Entity
@Table(name="CensusBlocks")
public class CensusBlock {
    private String id;
    private Precinct precinct;
    private Population population;
    private VotingAgePopulation vap;
    private String geometryString;
    private Election election;

    private Precinct parentPrecinct;
    private District parentDistrict;
    private Geometry geometry;
    private boolean border;

    private Set<CensusBlock> neighbors;

    @Id
    @Column(name="id")
    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="precinctId")
    @JsonBackReference
    public Precinct getPrecinct() { return precinct; }
    public void setPrecinct(Precinct p) { precinct = p; }

    @ManyToMany
    @JoinTable(
            name="CensusBlockNeighbors",
            joinColumns = @JoinColumn(name="blockId"),
            inverseJoinColumns = @JoinColumn(name="neighborId")
    )
    @JsonIgnore
    public Set<CensusBlock> getNeighbors() { return neighbors; }
    public void setNeighbors(Set<CensusBlock> n) { neighbors = n; }

    @OneToOne
    @JoinColumn(name="populationId")
    public Population getPopulation() { return this.population; }
    public void setPopulation(Population p) { population = p; }

    @OneToOne
    @JoinColumn(name="vapId")
    public VotingAgePopulation getVap() { return vap; }
    public void setVap(VotingAgePopulation vap) { this.vap = vap; }

    @Column(name="geometry", columnDefinition = "LONGTEXT")
    public String getGeometryString() { return geometryString; }
    public void setGeometryString(String gs) { this.geometryString = gs; }

    @OneToOne
    @JoinColumn(name="electionId")
    public Election getElection() { return election; }
    public void setElection(Election e) { election = e; }

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
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    public Geometry getGeometry() { return geometry; }
    public void setGeometry(Geometry p) { geometry = p; }

    public Geometry convertStringToGeometry() {
        try {
            WKTReader reader = new WKTReader();
            Geometry p = reader.read(this.getGeometryString());
            this.setGeometry(p);
            return p;
        } catch (Exception e){
            System.out.println("Error reading Census Blocks LONGTEXT to Geometry using JTS");
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (o == this) { return true; }
        if (getClass() != o.getClass()) { return false; }
        final CensusBlock other = (CensusBlock) o;
        return this.id.equals(other.getId());
    }

    //TODO
    // public CensusBlock findDistrictNeighbor(District d){

    // }

}