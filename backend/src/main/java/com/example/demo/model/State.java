package com.example.demo.model;
import com.example.demo.model.data.*;

import javax.persistence.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="States")
public class State {
    private String id;
    private String name;
    private Districting enacted;
    private Districting current;
    private Districting redistricted;
    private Polygon outline;
    private List<Districting> districtings;
//    private Demographic demographic;
//    List<Districting> districtings;
//    StateOutline stateOutline;
//    Demographic demographic;
//    List<Election> elections;
//    BoxAndWhisker[] boxAndWhiskerBatch;

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

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="enactedId")
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

    @Column(name="outline")
    public Polygon getOutline() { return this.outline; }
    public void setOutline(Polygon p) {
        this.outline = p;
    }

    @OneToMany(mappedBy="id", cascade=CascadeType.ALL)
    public List<Districting> getDistrictings() { return this.districtings; }
    public void setDistrictings(List<Districting> seawulfDistrictings) {
        this.districtings = seawulfDistrictings;
    }
}
