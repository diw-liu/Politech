package com.example.demo.model;

import com.example.demo.model.data.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
public class CensusBlock {
    private int censusBlockId;
    private Demographic demographics;
    private District parentDistrict;
    private List<Election> elections;
    private Precinct precinct;
    private Polygon blockGeometry;
    private Boolean isBorder;

    @Id
    @GeneratedValue
    public int getCensusBlockId() {
        return censusBlockId;
    }

    public void setCensusBlockId(int censusBlockId) {
        this.censusBlockId = censusBlockId;
    }

    //TODO
    public CensusBlock findDistrictNeighbor(District d){

    }

}