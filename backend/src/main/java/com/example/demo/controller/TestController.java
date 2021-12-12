package com.example.demo.controller;

import com.example.demo.model.CensusBlock;
import com.example.demo.model.District;
import com.example.demo.model.Precinct;
import com.example.demo.model.State;
import com.example.demo.projections.data.DistrictingDataProjection;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wololo.geojson.GeoJSON;
import org.wololo.jts2geojson.GeoJSONWriter;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TestController {
    @Autowired
    StateRepository stateRepository;
    @Autowired
    DistrictingRepository districtingRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    PrecinctRepository precinctRepository;
    @Autowired
    CensusBlockRepository censusBlockRepository;

    @GetMapping("/testSession")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody String getSession(HttpSession session) {
        String state = (String) session.getAttribute("state");
        return state;
    }

    @GetMapping("/planTest")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody DistrictingDataProjection getPlan(@RequestParam String id) {
        Optional<DistrictingDataProjection> d = districtingRepository.findById(id, DistrictingDataProjection.class);
        DistrictingDataProjection d2 = d.get();
        return d2;
    }

    @GetMapping("/district")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody District getDistrict(@RequestParam String districtId) {
        return districtRepository.findById(districtId).get();
    }

    @GetMapping("/district_np")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody List<Object[]> getDistrictNoGeo(@RequestParam String id) {
        return districtRepository.findNoGeometryById(id);
    }

    @GetMapping("/precinct")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody Precinct getPrecinct(@RequestParam String id) {
        return precinctRepository.findById(id).get();
    }

    @GetMapping("/precinct_test")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody Precinct getPrecinctTest(@RequestParam String id) {
        return precinctRepository.findPrecinctTestId(id);
    }

    @GetMapping("/censusblock")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody /*GeoJSON*/ CensusBlock getCensusBlock(@RequestParam String id) {
        CensusBlock b = censusBlockRepository.findById(id).get();
//        b.convertStringToGeometry();
//        GeoJSONWriter writer = new GeoJSONWriter();
//        GeoJSON json = writer.write(b.getGeometry());
//        return json;
        return b;
    }
}
