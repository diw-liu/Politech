package com.example.demo.projections.data;

import com.example.demo.model.Election;
import com.example.demo.model.Population;
import com.example.demo.model.Precinct;
import com.example.demo.model.VotingAgePopulation;

import java.util.List;
import java.util.Set;

public interface DistrictingDataProjection {
    String getId();
    List<DistrictData> getDistricts();

    interface DistrictData {
        String getId();
        String getCd();
        Election getElection();
        Population getPopulation();
        VotingAgePopulation getVap();
        String getGeometryString();
//        Set<PrecinctBones> getBorderPrecincts();
    }

//    interface PrecinctBones {
//        String getId();
//    }
}
