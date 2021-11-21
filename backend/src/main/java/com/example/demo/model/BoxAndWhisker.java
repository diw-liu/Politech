package com.example.demo.model;

import com.example.demo.enums.Basis;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="BoxAndWhiskers")
public class BoxAndWhisker{
    private Long id;
    private Districting districting;
    private Basis basis;
    private List<Statistics> statistics; // used to create the box and whiskers for each district

    @Id
    @Column(name="id")
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="districtingId")
    public Districting getDistricting() { return this.districting; }
    public void setDistricting(Districting d) { this.districting = d; }

    @Enumerated(EnumType.STRING)
    @Column(name="basis")
    public Basis getBasis() { return basis; }
    public void setBasis(Basis basis) { this.basis = basis; }

    @OneToMany(mappedBy="plot", cascade=CascadeType.ALL)
    public List<Statistics> getStatistics() { return statistics; }
    public void setStatistics(List<Statistics> statistics) { this.statistics = statistics; }

    // //TODO
    // public List<Integer> fetchIntermediate(String name){

    // }
    // //TODO
    // public List<String> fetchAllState(){

    // }
    // //TODO
    // public List<String> fetchState(){

    // }
    // //TODO
    // public List<String> fetchDistricting(){

    // }
    // //TODO
    // public List<Integer> fetchDeviation(){

    // }
    // //TODO
    // public List<Integer> fetchAverage(){

    // }
}