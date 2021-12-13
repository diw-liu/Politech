package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.projections.data.DistrictingDataProjection;
import com.example.demo.projections.testing.DistrictGeometryProjection;
import com.example.demo.repositories.*;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wololo.geojson.Feature;
import org.wololo.geojson.FeatureCollection;
import org.wololo.geojson.GeoJSON;
import org.wololo.jts2geojson.GeoJSONWriter;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

@RestController
@RequestMapping("/test")
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
    public @ResponseBody State getSession(HttpSession session) {
        State state = (State) session.getAttribute("stateWhole");
        return state;
    }

    @GetMapping("/testWholeState")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody String getWholeState(HttpSession session) {
        Optional<State> sr = stateRepository.findById("24", State.class);
        session.setAttribute("stateWhole", sr.get());
        return "done";
    }

//    @GetMapping("/testSession")
//    @Produces(MediaType.APPLICATION_JSON)
//    public @ResponseBody String getSession(HttpSession session) {
//        String state = (String) session.getAttribute("state");
//        return state;
//    }
//
//    @GetMapping("/testSession")
//    @Produces(MediaType.APPLICATION_JSON)
//    public @ResponseBody String getSession(HttpSession session) {
//        String state = (String) session.getAttribute("state");
//        return state;
//    }

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

    @GetMapping("/district_union_multi")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody GeoJSON getUnionDistrict() {
        Optional<DistrictGeometryProjection> d1resp = districtRepository.findById("24PL0D1", DistrictGeometryProjection.class);
        Optional<DistrictGeometryProjection> d2resp = districtRepository.findById("24PL0D2", DistrictGeometryProjection.class);
        DistrictGeometryProjection dgp1 = d1resp.get();
        DistrictGeometryProjection dgp2 = d2resp.get();
        District d1 = new District();
        District d2 = new District();
        d1.setId("24PL0D1");
        d2.setId("24PLOD2");
        d1.setGeometryString(dgp1.getGeometryString());
        d2.setGeometryString(dgp2.getGeometryString());
        d1.convertStringToGeometry();
        d2.convertStringToGeometry();

        Geometry test = d1.getGeometry().union(d2.getGeometry());
        GeoJSONWriter writer = new GeoJSONWriter();
        GeoJSON json = writer.write(test);
        String jsonstring = json.toString();
        return json;
    }

    @GetMapping("/district_geometry")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody GeoJSON getDistrictGeometry(@RequestParam String id) {
        Optional<DistrictGeometryProjection> dgpresp = districtRepository.findById(id, DistrictGeometryProjection.class);
        DistrictGeometryProjection dgp = dgpresp.get();
        District d = new District();
        d.setId(id);
        d.setGeometryString(dgp.getGeometryString());
        d.convertStringToGeometry();
        GeoJSONWriter writer = new GeoJSONWriter();
        GeoJSON json = writer.write(d.getGeometry());
        String jsonstring = json.toString();
        return json;
    }

    @GetMapping("/sessionChange")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody String changeSession(HttpSession session) {
        Districting d = (Districting) session.getAttribute("selected");
        d.setId("NOSNAONSADONSDIOSANDIODAS");
        return d.getId();
    }

    @GetMapping("/sessionCheck")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody String checkSession(HttpSession session) {
        Districting d = (Districting) session.getAttribute("selected");
        return d.getId();
    }

    @GetMapping("/getDistrictingGo")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody FeatureCollection getRedistricted(HttpSession session) {
        Districting redistr = (Districting) session.getAttribute("selected");

        List<Feature> features = new ArrayList<Feature>();
        Map<String, Object> properties = new HashMap<String, Object>();

        GeoJSONWriter writer = new GeoJSONWriter();

        for (District d : redistr.getDistricts()) {
            features.add(new Feature(writer.write(d.getGeometry()), null));
        }
        GeoJSONWriter writer1 = new GeoJSONWriter();
        FeatureCollection json = writer1.write(features);
        return json;
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
