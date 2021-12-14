package com.example.demo.projections.summary;

import com.example.demo.model.Election;
import com.example.demo.model.Population;
import com.example.demo.model.Precinct;
import com.example.demo.model.VotingAgePopulation;

import java.util.Set;

public interface DistrictSummaryProjection {
    String getId();
    String getCd();
    Population getPopulation();
    VotingAgePopulation getVap();
    Election getElection();
    String getGeometryString();

//    Set<PrecinctSummaryProjection> getBorderPrecincts();
}
