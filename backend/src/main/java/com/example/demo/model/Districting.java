package com.example.demo.model;

import com.example.demo.algo.*;
import com.example.demo.model.data.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Districtings")
public class Districting {
    private int id;
    private State state;
    private List<District> districts;
    private List<Population> populations;

    @Id
    @Column(name="id")
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="stateId")
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

//    private Measures measures;





//    @ElementCollection
//    @CollectionTable(
//            name="Districts",
//            joinColumns=@JoinColumn(name="districtingId"))






























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