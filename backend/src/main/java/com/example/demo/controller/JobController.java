package com.example.demo.controller;

import com.example.demo.algo.*;
import com.example.demo.enums.*;
import com.example.demo.handlers.JobService;
import com.example.demo.model.District;
import com.example.demo.model.Districting;
import com.example.demo.model.Measures;
import com.example.demo.model.State;

import com.example.demo.projections.algorithm.DistrictNeighborsProjection;
import com.example.demo.projections.summary.DistrictSummaryProjection;
import com.example.demo.projections.summary.StateSummaryProjection;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.json.simple.parser.ParseException;
import org.wololo.geojson.Feature;
import org.wololo.jts2geojson.GeoJSONWriter;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/start")
    public Status startJob(@RequestParam double goal, @RequestParam int lower, @RequestParam int higher, @RequestParam int age, HttpServletRequest request){
//        if (goal < 0 || goal > 1 ) { return Status.FAILED; }
        Constraints constraints = new Constraints(goal, lower, higher);
        HttpSession session = request.getSession();
        session.setAttribute("constraints", constraints);
        // session.setAttribute("age", Age.valueOf(age));
        jobService.loadPlan(session);
        return jobService.startJob(constraints, Age.valueOf(age), session);
    }

    @GetMapping("/redistricted")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody JSONObject getRedistricted(HttpSession session) {
        Districting districting = (Districting) session.getAttribute("selected");

        JSONObject featureCollection = new JSONObject();
        featureCollection.put("type", "FeatureCollection");
        JSONObject crsprops = new JSONObject();
        JSONObject crs = new JSONObject();
        crsprops.put("name", "urn:ogc:def:crs:EPSG::4269");
        crs.put("type", "name");
        crs.put("properties", crsprops);
        featureCollection.put("crs", crs);

        List<Feature> features = new ArrayList<Feature>();
        WKTReader reader = new WKTReader();
        GeoJSONWriter writer = new GeoJSONWriter();

        for (District d : districting.getDistricts()) {
            Map<String, Object> properties = new HashMap<String, Object>();
            try{
                Geometry pgeo = reader.read(d.getGeometryString());
                properties.put("cd", d.getCd());
                features.add(new Feature(writer.write(pgeo), properties));
            } catch (Exception e){
                System.out.println("Error reading District LONGTEXT to Geometry using JTS");
            }
        }
        featureCollection.put("features", features);
        return featureCollection;
    }

    @GetMapping("/summary")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody AlgorithmSummary getSummary(HttpSession session) {
        AlgorithmSummary summary = (AlgorithmSummary) session.getAttribute("summary");
        return summary;
    }

    @GetMapping("/result")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody AlgorithmResult getResult(HttpSession session) {
        AlgorithmResult result = (AlgorithmResult) session.getAttribute("result");
        return result;
    }

    @GetMapping("/pause")
    public Status pauseJob(){
        return jobService.pauseJob();
    }

    @GetMapping("/resume")
    public Status resumeJob(){
        return jobService.resumeJob();
    }

    @GetMapping("/stop")
    public Status stopJob(){
        return jobService.stopJob();
    }

    @GetMapping("/getStatus")
    public Status getStatus(){
        return jobService.getStatus();
    }

    @GetMapping("/getIterations")
    public int getIterations(){
        return jobService.getIterations();
    }
}
