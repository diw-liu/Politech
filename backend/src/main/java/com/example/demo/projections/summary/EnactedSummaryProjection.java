package com.example.demo.projections.summary;

import java.util.List;

public interface EnactedSummaryProjection {
    String getId();
    List<DistrictSummaryProjection> getDistricts();
}
