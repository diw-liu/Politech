package com.example.demo.projections;

import com.example.demo.model.Population;

import java.util.List;

public interface StatePopulationProjection {
    String getId();
    Population getPopulation();
}

// this no longer works as state doesn't have population anymore