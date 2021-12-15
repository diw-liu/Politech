package com.example.demo.controller;

import com.example.demo.handlers.MapService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.demo.model.*;
import com.example.demo.projections.StateGeometryProjection;
import com.example.demo.projections.data.DistrictingDataProjection;
import com.example.demo.projections.summary.DistrictSummaryProjection;
import com.example.demo.projections.summary.StateSummaryProjection;
import com.example.demo.repositories.*;
import org.json.simple.JSONObject;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.json.simple.parser.ParseException;
import org.wololo.geojson.Feature;
import org.wololo.geojson.FeatureCollection;
import org.wololo.geojson.GeoJSON;
import org.wololo.jts2geojson.GeoJSONWriter;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;

@CrossOrigin(maxAge = 36000)
@RestController
@RequestMapping("/api")
class MapController {
    @Autowired
    private MapService mapService;

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

    Map<String, String> DISTRICT = Map.of(
        "MD", "MDdistricts.json",
        "MI", "MIdistrict.json",
        "PA", "PAdistrict.json"
        );
    Map<String, String> COUNTY = Map.of(
        "MD", "MDcounty.json",
        "MI", "michigan.json",
        "PA", "pennsylvania.json"
        );
    Map<String, String> PRECINCT = Map.of(
        "MD", "MDprecint.json",
        "MI", "michigan.json",
        "PA", "pennsylvania.json"
        );
    Map<String, String> STATE = Map.of(
            "MD", "24",
            "MI", "26",
            "PA", "42"
    );

    @GetMapping("/{stateName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStateMap(@PathVariable String stateName) throws FileNotFoundException, IOException, ParseException{
        String dir = "src/main/Data/";
        String result = "";
        try{
            String district = String.format("%s/%s",dir,DISTRICT.get(stateName));  
            String precinct = String.format("%s/%s",dir,PRECINCT.get(stateName));
            // String county = String.format("%s/%s",dir,COUNTY.get(stateName));
            String d = new String(Files.readAllBytes(Paths.get(district)));
            String p = new String(Files.readAllBytes(Paths.get(precinct)));
            // String c = new String(Files.readAllBytes(Paths.get(county)));
            result = "[" + d + "," + p +"]";
            // System.out.println(result);
        }catch (IOException ex){
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "error reading file",ex);
        }
        return result;
    }

    // COMPLETED - gives client the state and enacted districts pop + vap + elec info
    @GetMapping("/state")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody
    StateSummaryProjection getStateByName(@RequestParam String name, HttpServletRequest request) {
        StateSummaryProjection ssp = mapService.fetchStateByName(name);
        HttpSession session = request.getSession();
        session.setAttribute("state", ssp);
        return ssp;
    }

    // COMPLETED
    @GetMapping("/precinctgeometry")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody
    JSONObject getPrecinctGeometry(HttpSession session) {
        StateSummaryProjection ssp = (StateSummaryProjection) session.getAttribute("state");
        return mapService.fetchPrecinctGeometry(ssp.getId() + "PL0");
    }

    // COMPLETED
    @GetMapping("/countygeometry")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody JSONObject getCountyGeometry(HttpSession session) {
        StateSummaryProjection ssp = (StateSummaryProjection) session.getAttribute("state");
        return mapService.fetchCountyGeometry(ssp.getId());
    }

    // COMPLETED
    @GetMapping("/districtgeometry")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody JSONObject getDistrictGeometry(HttpSession session) {
        StateSummaryProjection ssp = (StateSummaryProjection) session.getAttribute("state");

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

        for (DistrictSummaryProjection dsp : ssp.getEnacted().getDistricts()) {
            Map<String, Object> properties = new HashMap<String, Object>();
            try{
                Geometry pgeo = reader.read(dsp.getGeometryString());
                properties.put("cd", dsp.getCd());
                features.add(new Feature(writer.write(pgeo), properties));
            } catch (Exception e){
                System.out.println("Error reading District LONGTEXT to Geometry using JTS");
            }
        }
        featureCollection.put("features", features);
        return featureCollection;
    }

    @GetMapping("/selectplan")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public DistrictingDataProjection getPlan(@RequestParam String id, HttpSession session) {
        DistrictingDataProjection ddp = mapService.fetchPlanSummary(id);
        session.setAttribute("selected", ddp.getId());
        return ddp; // TODO - change this later to be something else
    }

    @GetMapping("/all")
    @Produces({MediaType.APPLICATION_JSON})
    @ResponseBody public JSONObject getAll() {
        // get all state outlines
        List<StateGeometryProjection> sgplist = stateRepository.findAllProjectedBy();

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

        for (StateGeometryProjection sgp : sgplist) {
            Map<String, Object> properties = new HashMap<String, Object>();
            try{
                Geometry outline = reader.read(sgp.getGeometryString());
                properties.put("state", sgp.getName());
                features.add(new Feature(writer.write(outline), properties));
            } catch (Exception e){
                System.out.println("Error reading State LONGTEXT to Geometry using JTS");
            }
        }
        featureCollection.put("features", features);
        return featureCollection;
    }

    @GetMapping("/plan")
    @Produces({MediaType.APPLICATION_JSON})
    @ResponseBody public String getPlan() throws FileNotFoundException, IOException, ParseException{
        System.out.println("Okay");
        String dir = "src/main/Data/mockGenerated.json";
        String result = new String(Files.readAllBytes(Paths.get(dir)));
        return result;
    }

    @GetMapping("/plotPoints")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody String getBoxPoints() throws FileNotFoundException, IOException, ParseException{
        String dir = "src/main/Data/proportions_final.json";
        String result = new String(Files.readAllBytes(Paths.get(dir)));
        return result;
    }

    @GetMapping("/newDistricting")
    @Produces({MediaType.APPLICATION_JSON})
    @ResponseBody public List<String> getRedistrict() throws FileNotFoundException, IOException, ParseException{
        File dir = new File("src/main/geo/");
        List<String> result = new ArrayList<>();
        for(String file : dir.list()){
            try{
                String temp = String.format("%s/%s",dir.toString(),file);
                result.add(new String(Files.readAllBytes(Paths.get(temp))));
            }catch (IOException ex){
                ex.printStackTrace();
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "error reading file",ex);
            }
        }
        return result;
    }

    @GetMapping("/preview")
    public ResponseEntity<List<FileSystemResource>> getPreview(@RequestParam String state){
        File folder = new File("src/main/preview/" + state + "_previews/");
        File[] listOfFiles = folder.listFiles();
        List<FileSystemResource> previewList = new ArrayList<>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                Path path = listOfFiles[i].toPath();
                FileSystemResource resource = new FileSystemResource(path);
                previewList.add(resource);
            } 
        }        
        return ResponseEntity.ok().body(previewList);
    }

    @GetMapping("/preview/{state}/{name}")
    public ResponseEntity<Resource> viewImg(@PathVariable String state, @PathVariable String name) throws IOException {
        String inputFile = "src/main/preview/" + state + "_previews/" + name + ".svg";
        Path path = new File(inputFile).toPath();
        FileSystemResource resource = new FileSystemResource(path);
        return ResponseEntity.ok()
                .body(resource);
    }
}