package com.example.demo.projections.summary;

import com.example.demo.model.Election;
import com.example.demo.model.Population;
import com.example.demo.model.VotingAgePopulation;

public interface StateSummaryProjection {
    String getId();
    String getName();
    Population getPopulation();
    VotingAgePopulation getVap();
    Election getElection();
    EnactedSummaryPopulation getEnacted();
}