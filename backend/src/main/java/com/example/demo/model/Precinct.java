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
//    private Demographic demographics;
//    private List<Election> elections;
//    private List<Precinct> neighbors;
//    private Boolean hasChanged;

    @Id
    @Column(name="id")
    public int getPrecinctId() { return id; }
    public void setPrecinctId(int id) { this.id = id; }

    @Column(name="geometry")
    public Polygon getGeometry() { return geometry; }
    public void setGeometry(Polygon geometry) { this.geometry = geometry; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="districtId")
    public District getDistrict() { return district; }
    public void setDistrict(District d) { district = d; }

//    //TODO
//    public void changeBoundary(Polygon blockGeometry){
//
//    }
}