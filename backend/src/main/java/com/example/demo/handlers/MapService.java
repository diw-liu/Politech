package com.example.demo.handlers;

import com.example.demo.model.Districting;
import com.example.demo.projections.data.DistrictingDataProjection;
import com.example.demo.projections.summary.StateSummaryProjection;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    /* Only returns the State + District level population + election info for enacted */
    public StateSummaryProjection fetchStateByName(String name) {
        return stateRepository.findByName(name, StateSummaryProjection.class);
    }

    public DistrictingDataProjection fetchPlanSummary(String id) {
        Optional<DistrictingDataProjection> planResponse = districtingRepository.findById(id, DistrictingDataProjection.class);
        DistrictingDataProjection planSummary = planResponse.get();
        return planSummary;
    }
    
    public Districting fetchPlan(String id) {
        Optional<Districting> planResponse = districtingRepository.findById(id, Districting.class);
        Districting plan = planResponse.get();
        return plan;
    }
}