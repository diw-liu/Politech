package com.example.demo.projections;

import com.example.demo.model.Population;

import java.util.List;

public interface StatePopulationProjection {
    String getId();
    Population getPopulation();
}
