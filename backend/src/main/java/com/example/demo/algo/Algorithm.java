package com.example.demo.algo;
import com.example.demo.model.Districting;
import com.example.demo.model.District;
import com.example.demo.model.CensusBlock;
import com.example.demo.model.Measures;
import com.example.demo.model.Population;
import com.example.demo.model.Precinct;

import java.util.*;

import org.locationtech.jts.dissolve.LineDissolver;
import org.locationtech.jts.geom.*;

public class Algorithm {
    private HashMap<String, District> districts;
    private HashMap<String, Precinct> precincts;
    private HashMap<String, HashMap<String, Precinct>> districtToPrecinct;
    private Constraints constraints;
    private ArrayList<String> did;
    private ArrayList<String> pid;

}
