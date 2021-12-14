package com.example.demo.model;


import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Geometry geometry;

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
    public Set<Precinct> getBorderPrecincts() { return this.borderPrecincts; }
    public void setBorderPrecincts(Set<Precinct> precincts) { this.borderPrecincts = precincts; }

    @ManyToMany
    @JoinTable(
            name="DistrictNeighbors",
            joinColumns = @JoinColumn(name="districtId"),
            inverseJoinColumns = @JoinColumn(name="neighborId")
    )
    @JsonIgnore
    public Set<District> getNeighbors() { return neighbors; }
    public void setNeighbors(Set<District> n) { neighbors = n; }

    @Transient
//    @JsonSerialize(using = GeometrySerializer.class)
//    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    @JsonIgnore
    public Geometry getGeometry() { return geometry; }
    public void setGeometry(Geometry p) { geometry = p; }

    public Geometry convertStringToGeometry() {
        try {
            WKTReader reader = new WKTReader();
            Geometry p = reader.read(this.getGeometryString());
            this.setGeometry(p);
            return p;
        } catch (Exception e){
            System.out.println("Error reading District LONGTEXT to Polygon using JTS");
            return null;
        }
    }

    public Precinct selectRandomBorderPrecinct(){
        if (borderPrecincts.size() == 0) { return null; }
        Random rand = new Random();
        int index = rand.nextInt(borderPrecincts.size());
        int i = 0;
        for(Precinct p : borderPrecincts)
        {
            if (i == index)
                return p;
            i++;
        }
        return null;
    }

    public District selectRandomNeighbor(){
        if (neighbors.size() == 0) { return null; }
        Random rand = new Random();
        int index = rand.nextInt(neighbors.size());
        int i = 0;
        for(District d : neighbors)
        {
            if (i == index)
                return d;
            i++;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (o == this) { return true; }
        if (getClass() != o.getClass()) { return false; }
        final District other = (District) o;
        return this.id.equals(other.getId());
    }
}