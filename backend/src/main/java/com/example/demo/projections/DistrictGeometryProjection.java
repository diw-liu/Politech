package com.example.demo.projections;

import java.awt.*;
import java.util.List;

public interface DistrictGeometryProjection {
    String getId();
    Polygon getGeometry();
    List<PrecinctGeometryProjection> getPrecincts();
}
