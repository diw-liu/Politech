package com.example.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.demo.model.EnsembleDataDTO;
import com.example.demo.model.State;
import com.example.demo.projections.StateDisplayProjection;
import com.example.demo.projections.StatePopulationProjection;
import com.example.demo.repositories.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.json.simple.parser.ParseException;
import java.awt.Polygon;
import java.sql.*;

@RestController
@RequestMapping("/api")
class MapController{
    private String dbpass = "changeit";
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private DistrictingRepository districtingRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private PrecinctRepository precinctRepository;
    // String districtMD = "src/main/data/MDdistricts.json";
    // String precinctMD = "src/main/data/maryland.json";
    // String districtMI = "src/main/data/MIdistrict.json";
    // String precinctMI = "src/main/data/michigan.json";
    // String districtPA = "src/main/data/PAdistrict.json";
    // String precinctPA = "src/main/data/pennsylvania.json";

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

    @GetMapping("/population")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody StatePopulationProjection getStatePopulationByName(@RequestParam String name) {
        return stateRepository.findByName(name, StatePopulationProjection.class);
//        Optional<StatePopulationProjection> statePopulationResponse = stateRepository.findById(id, StatePopulationProjection.class);
//        return statePopulationResponse.get();
    }

    @GetMapping("/state")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody StateDisplayProjection getStateByName(@RequestParam String name) {
        State selected = stateRepository.findByName(name, State.class);
        return stateRepository.findByName(name, StateDisplayProjection.class);
    }

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

    @GetMapping("/voting")
    @Produces({MediaType.APPLICATION_JSON})
    @ResponseBody public String getVoting() throws FileNotFoundException, IOException, ParseException{

        String dir = "src/main/Data/mockState.json";
        String result = new String(Files.readAllBytes(Paths.get(dir)));
        return result;
    }

    @GetMapping("/demographic")
    @Produces({MediaType.APPLICATION_JSON})
    @ResponseBody public String getDemographic() throws FileNotFoundException, IOException, ParseException{
        String dir = "src/main/Data/mockPop.json";
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

    @GetMapping("/ensemble")
    @Produces({MediaType.APPLICATION_JSON})
    public @ResponseBody String[][] getEnsemble(@RequestParam String state) {
        int stateNum = 0;
        int numDistricts = 0;
        System.out.println(state);
        if (state.equals("Maryland")) {
            stateNum = 24;
            numDistricts = 8;
        } else {
            stateNum = 26;
            numDistricts = 14;
        }
        String[][] boxes = new String[numDistricts][6];
        Connection conn;
        Statement s;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/Pistons", "Pistons", dbpass);
            s = conn.createStatement();

            String sql = "SELECT cd, min, q1, q3, max, med FROM ensembledata WHERE state = "+ stateNum;
            ResultSet rs = s.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            int count = 0;
            while(rs.next()) {
                String[] stats = new String[6];
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                    stats[i-1] = rs.getString(i);
                }
                boxes[count] = stats;
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boxes;
    }

//    @GetMapping("/instance")
//    @Produces({MediaType.APPLICATION_JSON})
//    @ResponseBody public String instantiateTest() throws FileNotFoundException, IOException, ParseException{
//        State s = new State();
//        s.setName("Maryland");
////        Polygon p;
//        s.setId(24L);
//        stateRepository.save(s);
//        return "Saved";
//    }
}