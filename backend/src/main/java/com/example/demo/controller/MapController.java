package com.example.demo.controller;

import com.example.demo.handlers.MapService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.demo.model.*;
import com.example.demo.projections.summary.StateSummaryProjection;
import com.example.demo.repositories.*;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.json.simple.parser.ParseException;
import org.wololo.geojson.GeoJSON;
import org.wololo.jts2geojson.GeoJSONWriter;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;


@RestController
@RequestMapping("/api")
class MapController{
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
    Map<String, String> PRECINCT = Map.of(
        "MD", "maryland.json",
        "MI", "michigan.json",
        "PA", "pennsylvania.json"
        );

    @GetMapping("/{stateName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStateMap(@PathVariable String stateName) throws FileNotFoundException, IOException, ParseException{
        String dir = "src/main/Data/";
        String result = "";
        try{
            String district = String.format("%s/%s",dir,DISTRICT.get(stateName));  
            String precinct = String.format("%s/%s",dir,PRECINCT.get(stateName));
            String d = new String(Files.readAllBytes(Paths.get(district)));
            String p = new String(Files.readAllBytes(Paths.get(precinct)));
            result = "[" + d + "," + p + "]";
        }catch (IOException ex){
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "error reading file",ex);
        }
        return result;
    }

    // COMPLETED
    @GetMapping("/state")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody StateSummaryProjection getStateByName(@RequestParam String name, HttpServletRequest request) {
        StateSummaryProjection ssp = mapService.getStateByName(name);
        HttpSession session = request.getSession();
        session.setAttribute("state", ssp.getName());
        session.setAttribute("selected", null); // selected refers to selected plan
        return ssp;
    }

//    @GetMapping("/population")
//    @Produces(MediaType.APPLICATION_JSON)
//    public @ResponseBody
//    StateSummaryProjection getStatePopulationByName(@RequestParam String name) {
//        return stateRepository.findByName(name, StateSummaryProjection.class);
////        Optional<StatePopulationProjection> statePopulationResponse = stateRepository.findById(id, StatePopulationProjection.class);
////        return statePopulationResponse.get();
//    }


    @GetMapping("/all")
    @Produces({MediaType.APPLICATION_JSON})
    @ResponseBody public List<String> getAll() throws FileNotFoundException, IOException, ParseException{
        File dir = new File("src/main/State/");
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

    @GetMapping("/cb")
    @Produces({MediaType.APPLICATION_JSON})
    public @ResponseBody CensusBlock getCensusBlock() {
        Optional<CensusBlock> cbResponse = censusBlockRepository.findById("0");
        CensusBlock cb = cbResponse.get();
        System.out.println("THISTHISTHISTHISTHISTHISTHIS");
        System.out.println(cb.getGeometry().toString());
//        try {
//            WKTReader reader = new WKTReader();
//            Polygon p = (Polygon) reader.read(cb.getGeometry());
//
//        } catch (Exception e){
//            System.out.println("FUCKFUCKFUCKFUCKFUKCFUCK");
//            return null;
//        }
        return cb;
//        Connection conn;
//        Statement s;
//        try {
//            conn = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/Pistons", "Pistons", dbpass);
//            s = conn.createStatement();
//
//            String sql = "SELECT index, border FROM block";
//            ResultSet rs = s.executeQuery(sql);
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
//            while(rs.next()) {
//                for (int i = 1; i <= columnsNumber; i++) {
//                    if (i > 1) System.out.print(",  ");
//                    String columnValue = rs.getString(i);
//                    System.out.println(columnValue + " " + rsmd.getColumnName(i));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "Gotten";
    }

    @GetMapping("/shape")
    @Produces({MediaType.APPLICATION_JSON})
    @ResponseBody public GeoJSON instantiateTest(@RequestParam String id) throws FileNotFoundException, IOException, ParseException{
        Optional<Precinct> stateResponse = precinctRepository.findById(id);
        Precinct s = stateResponse.get();
//        return s;
        s.convertStringToGeometry();

        Optional<Precinct> p2 = precinctRepository.findById("24P1087");
        Precinct s2 = p2.get();
        s2.convertStringToGeometry();

        Geometry test = s.getGeometry().union(s2.getGeometry());

//        JSONParser jsonParser = new JSONParser();
//        Gson gson = new Gson();
//        JSONObject jsonObject = (JSONObject) jsonParser.parse(s.getGeometryString());

        GeoJSONWriter writer = new GeoJSONWriter();
//        GeoJSON json = writer.write(s.getGeometry());
        GeoJSON json = writer.write(test);
        String jsonstring = json.toString();
        return json;
    }
}