package com.example.demo.model;

import com.example.demo.model.data.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.Polygon;

@Entity
@Table(name="CensusBlocks")
public class CensusBlock {
    private int id;
//    private Demographic demographics;
//    private District district;
//    private List<Election> elections;
//    private Precinct precinct;
//    private Polygon geometry;
//    private Boolean isBorder;

    @Id
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    //TODO
    // public CensusBlock findDistrictNeighbor(District d){

    // }

}