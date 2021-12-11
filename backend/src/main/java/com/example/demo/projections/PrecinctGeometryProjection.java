package com.example.demo.projections;

import com.example.demo.model.CensusBlock;
import com.example.demo.model.District;

import java.awt.*;
import java.util.Set;

public interface PrecinctGeometryProjection {
    String getId();
//    Polygon getGeometry();
//    District getDistrict();
    Set<CensusBlock> getBorderBlocks();
}
