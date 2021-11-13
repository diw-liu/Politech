package com.example.demo.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class District {
    private int districtId;
    private Demographics demographics;
    private List<Election> elections;
    private List<Integer> neighbors;
    private List<Precinct> precincts;
    private List<CensusBlock> censusBlocks;
    private List<CensusBlock> borderBlocks;
    private Polygon districtGeometry;
    private double[] boxWhiskerPoints;

    @Id
    @GeneratedValue
    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    //TODO
    public CensusBlock selectRandomBlock(){

    }

    //TODO
    public void giveBlock(District givenTo, CensusBlock toGive){

    }

    //TODO
    public List<Precincts> getChangedPrecincts(){

    }
    
}