package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import java.util.List;
import java.util.Random;

@Entity
@Table(name="Districtings")
public class Districting {
    private String id;
    private State state;
    private List<District> districts;
//    private Population population;
//    private VotingAgePopulation vap;
    private Election election;
    private List<Population> populations;

    @Id
    @Column(name="id")
    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="stateId")
    @JsonBackReference
    public State getState() { return this.state; }
    public void setState(State s) { this.state = s; }

    @OneToMany(mappedBy="districting")
    public List<District> getDistricts() { return this.districts; }
    public void setDistricts(List<District> districts) { this.districts = districts; }

    @OneToMany
    @JoinTable(
            name="DistrictingPopulations",
            joinColumns = @JoinColumn(name="districtingId"),
            inverseJoinColumns = @JoinColumn(name="populationId")
    )
    public List<Population> getPopulations() { return this.populations; }
    public void setPopulations(List<Population> p) { populations = p; }

//    @OneToOne
//    @JoinColumn(name="populationId")
//    public Population getPopulation() { return this.population; }
//    public void setPopulation(Population p) { population = p; }
//
//    @OneToOne
//    @JoinColumn(name="vapId")
//    public VotingAgePopulation getVap() { return vap; }
//    public void setVap(VotingAgePopulation vap) { this.vap = vap; }

    @OneToOne
    @JoinColumn(name="electionId")
    public Election getElection() { return election; }
    public void setElection(Election e) { election = e; }

    //    @OneToMany(mappedBy="districting", cascade=CascadeType.ALL)
    //    public List<BoxAndWhisker> getPlots() { return this.plots; }
    //    public void setPlots(List<BoxAndWhisker> p) {
    //        this.plots = p;
    //    }

    public District selectRandomDistricts(){
        int size = this.districts.size();
        Random rand = new Random();
        return districts.get(rand.nextInt(size));
    }

//    @OneToMany(mappedBy = "districting", cascade = {CascadeType.ALL})
//    public List<District> getDistricts() {
//        return districts;
//    }
//
//    public void setDistricts(List<District> districts) {
//        this.districts = districts;
//    }
//
//    public Measures getMeasures() {
//        return measures;
//    }
//
//    public void setMeasures(Measures measures) {
//        this.measures = measures;
//    }
//
//    public Districting cloneDistricting(){
//        Districting clone = new Districting();
//        clone.setDistrictingId(this.districtingId);
//        clone.setDistricts(this.districts);
//        clone.setMeasures(this.measures);
//        return clone;
//    }

    //TODO
    // public Measures recalculateMeasures(){
        
    // }

    //TODO
    // public List<Precinct> getChangedPrecincts(){
        
    // }

    //TODO
    // public boxWhiskerPoints[] getBoxAndWhiskerPoints(BoxAndWhiskerBasis){

    // }
    
    //TODO
    // public DistrictingInfo getDistrictingSummary(){

    // }

    //TODO
    // public List<CensusBlock> getCensusBlocksByDistrict(List<District>){

    // }

//    @Override
//    public String toString() {
//        return "Districting{" +
//                "districtingId: " + districtingId +
//                ", districts: " + districts +
//                ", measures: " + measures +
//                '}';
//    }
}