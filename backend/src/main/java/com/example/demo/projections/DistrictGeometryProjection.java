package com.example.demo.projections;

import com.example.demo.model.Precinct;

import java.awt.*;
import java.util.List;

public interface DistrictGeometryProjection {
    String getId();
//    Polygon getGeometry();
//    String getGeometryString();
    List<PrecinctGeometryProjection> getPrecincts();
}
