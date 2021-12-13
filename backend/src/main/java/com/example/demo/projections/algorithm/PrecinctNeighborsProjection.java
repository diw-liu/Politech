package com.example.demo.projections.algorithm;

import java.util.Set;

public interface PrecinctNeighborsProjection {
    Set<PrecinctId> getNeighbors();

    interface PrecinctId {
        String getId();
    }
}
