package com.example.demo.model;

import com.example.demo.algo.*;
import com.example.demo.model.data.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Districting {
    private int districtingId;
    private Demographic demographics;
    private List<District> districts;
    private Measures measures;

    @Id
    @GeneratedValue
    public int getDistrictingId() {
        return districtingId;
    }

    public void setDistrictingId(int districtingId) {
        this.districtingId = districtingId;
    }

    @OneToMany(mappedBy = "districting", cascade = {CascadeType.ALL})
    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public Measures getMeasures() {
        return measures;
    }

    public void setMeasures(Measures measures) {
        this.measures = measures;
    }

    public Districting cloneDistricting(){
        Districting clone = new Districting();
        clone.setDistrictingId(this.districtingId);
        clone.setDistricts(this.districts);
        clone.setMeasures(this.measures);
        return clone;
    }

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

    @Override
    public String toString() {
        return "Districting{" +
                "districtingId: " + districtingId +
                ", districts: " + districts +
                ", measures: " + measures +
                '}';
    }
}