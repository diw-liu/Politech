package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

import javax.persistence.*;

@Entity
@Table(name="County")
public class County {
    private String id;
    private String state;
    private String county;
    private String geometryString;

    private Geometry geometry;

    @Id
    @Column(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name="county")
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Column(name="geometry")
    public String getGeometryString() {
        return geometryString;
    }

    public void setGeometryString(String geometryString) {
        this.geometryString = geometryString;
    }

    @Transient
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
            System.out.println("Error reading County LONGTEXT to Geometry using JTS");
            return null;
        }
    }
}
