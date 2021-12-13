package com.example.demo.projections.summary;

import com.example.demo.model.Election;
import com.example.demo.model.Population;
import com.example.demo.model.VotingAgePopulation;

public interface DistrictSummaryProjection {
    String getId();
    Population getPopulation();
    VotingAgePopulation getVap();
    Election getElection();
}
