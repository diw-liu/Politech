package com.example.demo.model;

import com.example.demo.model.data.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Precinct {
    private int precinctId;
    private Demographic demographics;
    private District parentDistrict;
    private List<Election> elections;
    private List<Precinct> neighbors;
    private Polygon precinctGeometry;
    private Boolean hasChanged;

    
    @Id
    @GeneratedValue
    public int getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(int precinctId) {
        this.precinctId = precinctId;
    }

    //TODO
    public void changeBoundary(Polygon blockGeometry){

    }

    
    
}