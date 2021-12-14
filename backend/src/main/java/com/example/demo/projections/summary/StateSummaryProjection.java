package com.example.demo.projections.summary;

import com.example.demo.model.*;

import java.util.List;

public interface StateSummaryProjection {
    String getId();
    String getName();
    Population getPopulation();
    VotingAgePopulation getVap();
    Election getElection();
    EnactedSummaryProjection getEnacted();
    List<BoxAndWhisker> getPlots();
    List<DistrictingId> getDistrictings();

    interface DistrictingId {
        String getId();
        Measures getMeasures();
    }
}