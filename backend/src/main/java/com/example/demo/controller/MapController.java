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

import com.example.demo.model.State;
import com.example.demo.projections.StateDisplayProjection;
import com.example.demo.projections.StatePopulationProjection;
import com.example.demo.repositories.DistrictRepository;
import com.example.demo.repositories.DistrictingRepository;
import com.example.demo.repositories.PrecinctRepository;
import com.example.demo.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.json.simple.parser.ParseException;
import java.awt.Polygon;

@RestController
@RequestMapping("/api")
class MapController{
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

    @GetMapping("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody StatePopulationProjection getStatePopulationByName(@RequestParam String name) {
        return stateRepository.findByName(name, StatePopulationProjection.class);
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