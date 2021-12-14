package com.example.demo.handlers;

import com.example.demo.model.County;
import com.example.demo.model.District;
import com.example.demo.model.Districting;
import com.example.demo.projections.data.DistrictingDataProjection;
import com.example.demo.projections.summary.CountySummaryProjection;
import com.example.demo.projections.summary.DistrictSummaryProjection;
import com.example.demo.projections.summary.PrecinctSummaryProjection;
import com.example.demo.projections.summary.StateSummaryProjection;
import com.example.demo.repositories.*;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wololo.geojson.Feature;
import org.wololo.geojson.FeatureCollection;
import org.wololo.jts2geojson.GeoJSONWriter;

import java.util.*;

@Service
public class MapService {
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private DistrictingRepository districtingRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private PrecinctRepository precinctRepository;
    @Autowired
    private CensusBlockRepository censusBlockRepository;
    @Autowired
    private PopulationRepository populationRepository;
    @Autowired
    private CountyRepository countyRepository;

    /* Only returns the State + District level population + election info for enacted */
    public StateSummaryProjection fetchStateByName(String name) {
        return stateRepository.findByName(name, StateSummaryProjection.class);
    }

    public Districting fetchPlan(String id) {
        Optional<Districting> planResponse = districtingRepository.findById(id, Districting.class);
        Districting plan = planResponse.get();
        return plan;
    }

    public DistrictingDataProjection fetchPlanSummary(String id) {
        Optional<DistrictingDataProjection> planResponse = districtingRepository.findById(id, DistrictingDataProjection.class);
        DistrictingDataProjection planSummary = planResponse.get();
        return planSummary;
    }

    public FeatureCollection fetchPrecinctGeometry(String id) {
        List<PrecinctSummaryProjection> psp = precinctRepository.findByDistrictIdStartsWith(id);

        List<Feature> features = new ArrayList<Feature>();
        WKTReader reader = new WKTReader();
        GeoJSONWriter writer = new GeoJSONWriter();

        for (PrecinctSummaryProjection p : psp) {
            Map<String, Object> properties = new HashMap<String, Object>();
            try{
                Geometry pgeo = reader.read(p.getGeometryString());
                properties.put("id", p.getId());
                features.add(new Feature(writer.write(pgeo), properties));
            } catch (Exception e){
                System.out.println("Error reading Precinct LONGTEXT to Geometry using JTS");
            }
        }
        GeoJSONWriter writer1 = new GeoJSONWriter();
        FeatureCollection json = writer1.write(features);
        return json;
    }

    public FeatureCollection fetchCountyGeometry(String stateId) {
        List<County> csp = countyRepository.findByState(stateId); //stateId

        List<Feature> features = new ArrayList<Feature>();
        WKTReader reader = new WKTReader();
        GeoJSONWriter writer = new GeoJSONWriter();

        for (County c : csp) {
            Map<String, Object> properties = new HashMap<String, Object>();
            try{
                Geometry pgeo = reader.read(c.getGeometryString());
                properties.put("county", c.getCounty());
                features.add(new Feature(writer.write(pgeo), properties));
            } catch (Exception e){
                System.out.println("Error reading County LONGTEXT to Geometry using JTS");
            }
        }
        GeoJSONWriter writer1 = new GeoJSONWriter();
        FeatureCollection json = writer1.write(features);
        return json;
    }

}