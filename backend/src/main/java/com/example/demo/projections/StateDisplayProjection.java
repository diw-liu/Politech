package com.example.demo.projections;

import com.example.demo.model.Districting;
import com.example.demo.model.Election;
import com.example.demo.model.Population;

import java.util.List;

public interface StateDisplayProjection {
    String getId();
    String getName();
    DistrictingGeometryProjection getEnacted();
//    List<DistrictingGeometryProjection> getDistrictings();
//    DistrictingGeometryProjection getEnacted();
//    List<Population> getPopulations();
//    List<Election> getElections();
}
