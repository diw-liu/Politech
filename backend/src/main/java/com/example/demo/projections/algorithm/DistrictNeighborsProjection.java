package com.example.demo.projections.algorithm;

import java.util.Set;

public interface DistrictNeighborsProjection {
    Set<DistrictId> getNeighbors();

    interface DistrictId {
        String getId();
    }
}
