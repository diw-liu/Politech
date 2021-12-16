package com.example.demo.projections.summary;

import com.example.demo.model.Measures;

import java.util.List;

public interface EnactedSummaryProjection {
    String getId();
    List<DistrictSummaryProjection> getDistricts();
    Measures getMeasures();
}
