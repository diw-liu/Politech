package com.example.demo.handlers;

import com.example.demo.projections.summary.StateSummaryProjection;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public StateSummaryProjection getStateByName(String name) {
        return stateRepository.findByName(name, StateSummaryProjection.class);
    }
}