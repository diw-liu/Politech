package com.example.demo.model;
import com.example.demo.algo.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Districting {
    private int districtingId;
    private List<District> districts = new ArrayList<>();
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


    @Override
    public String toString() {
        return "Districting{" +
                "districtingId=" + districtingId +
                ", districts=" + districts +
                '}';
    }
}