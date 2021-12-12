package com.example.demo.projections.summary;

import com.example.demo.model.Election;
import com.example.demo.model.Measures;
import com.example.demo.model.Population;
import com.example.demo.model.VotingAgePopulation;

import java.util.List;

public interface StateSummaryProjection {
    String getId();
    String getName();
    Population getPopulation();
    VotingAgePopulation getVap();
    Election getElection();
    EnactedSummaryPopulation getEnacted();
    List<DistrictingId> getDistrictings();

    interface DistrictingId {
        String getId();
        Measures getMeasures();
    }
}