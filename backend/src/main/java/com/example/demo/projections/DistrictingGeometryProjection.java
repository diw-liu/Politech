package com.example.demo.projections;

import java.util.List;

public interface DistrictingGeometryProjection {
    String getId();
    List<DistrictGeometryProjection> getDistricts();
}
