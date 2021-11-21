package com.example.demo.model;

import javax.persistence.*;

import java.awt.*;
import java.util.List;

@Entity
@Table(name="States")
public class State {
    private Long id;
    private String name;
    private Districting enacted;
    private Districting current;
    private Districting redistricted;
    private Polygon outline;
    private List<Districting> districtings;
    private List<Population> populations;
    private List<Election> elections;

    @Id
    @Column(name="id")
    public Long getId() { return this.id; }
    public void setId(Long id) {
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

    @OneToMany(mappedBy="state", cascade=CascadeType.ALL)
    public List<Districting> getDistrictings() { return this.districtings; }
    public void setDistrictings(List<Districting> seawulfDistrictings) {
        this.districtings = seawulfDistrictings;
    }

    @OneToMany
    @JoinTable(
            name="StatePopulations",
            joinColumns = @JoinColumn(name="stateId"),
            inverseJoinColumns = @JoinColumn(name="populationId")
    )
    public List<Population> getPopulations() { return this.populations; }
    public void setPopulations(List<Population> p) { populations = p; }

    @OneToMany
    @JoinTable(
            name="StateElections",
            joinColumns = @JoinColumn(name="stateId"),
            inverseJoinColumns = @JoinColumn(name="electionId")
    )
    public List<Election> getElections() { return this.elections; }
    public void setElections(List<Election> e) { elections = e; }
}
