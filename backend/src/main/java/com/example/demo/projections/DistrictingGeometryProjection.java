package com.example.demo.projections;

import com.example.demo.model.District;
import com.example.demo.model.Population;

import java.util.List;

public interface DistrictingGeometryProjection {
    String getId();
    Population getPopulation();
    List<DistrictGeometryProjection> getDistricts();
}
