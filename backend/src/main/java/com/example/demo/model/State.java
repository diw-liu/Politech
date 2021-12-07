package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.WKTReader;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

import java.awt.*;
import java.util.List;

@Entity
@Table(name="States")
public class State {
    private String id;
    private String name;
    private Districting enacted;
    private Districting current;
    private Districting redistricted;
    private String geometryString;
    private Polygon geometry;

    private List<Districting> districtings;
    private List<BoxAndWhisker> plots;

//    private Population population;
//    private VotingAgePopulation vap;
//    private Election election;

    @Id
    @Column(name="id")
    public String getId() { return this.id; }
    public void setId(String id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() { return this.name; }
    public void setName(String name) {
        this.name = name;
    }

//    @OneToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name="enactedId")
    @Transient
    public Districting getEnacted() { return this.enacted; }
    public void setEnacted(Districting e) {
        this.enacted = e;
    }

    @Transient
    public Districting getCurrent() { return this.current; }
    public void setCurrent(Districting c) {
        this.current = c;
    }

    @Transient
    public Districting getRedistricted() { return this.redistricted; }
    public void setRedistricted(Districting r) {
        this.redistricted = r;
    }

    @Column(name="geometry", columnDefinition="LONGTEXT")
    public String getGeometryString() { return this.geometryString; }
    public void setGeometryString(String p) {
        this.geometryString = p;
    }

    @Transient
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    public Polygon getGeometry() { return geometry; }
    public void setGeometry(Polygon p) { geometry = p; }

    @OneToMany(mappedBy="state", cascade=CascadeType.ALL)
    @JsonManagedReference
    public List<Districting> getDistrictings() { return this.districtings; }
    public void setDistrictings(List<Districting> seawulfDistrictings) {
        this.districtings = seawulfDistrictings;
    }

    @OneToMany(mappedBy="state", cascade=CascadeType.ALL)
    public List<BoxAndWhisker> getPlots() { return plots; }
    public void setPlots(List<BoxAndWhisker> p) { plots = p; }

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
}
