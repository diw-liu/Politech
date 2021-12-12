package com.example.demo.projections.testing;

import java.util.List;

public interface DistrictGeometryProjection {
    String getId();
//    Polygon getGeometry();
//    String getGeometryString();
    List<PrecinctGeometryProjection> getPrecincts();
}
