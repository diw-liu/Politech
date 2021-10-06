package com.example.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

@RestController
@RequestMapping("/api")
class MapController{
    //String[] DISTRICT = {"MDdistricts", "districtMI", "districtPA"};
    //File folder = new File("../data/MDdistricts.json");
    // String districtMD = "src/main/data/MDdistricts.json";
    // String precintMD = "src/main/data/maryland.json";
    // String districtMI = "src/main/data/MIdistrict.json";
    // String precintMI = "src/main/data/michigan.json";
    // String districtPA = "src/main/data/PAdistrict.json";
    // String precintPA = "src/main/data/pennsylvania.json";
    // String[] DISTRICT = {districtMD,districtMI,districtPA};
    // String[] PRECINT = {precintMD,precintMI,precintPA};

    // @GetMapping("/{id}")
    // @Produces(MediaType.APPLICATION_JSON)
    // public ResponseEntity<String> getStateMap(@PathVariable int id) throws FileNotFoundException, IOException, ParseException{
      
    //     JSONParser parser = new JSONParser();

    //     JSONObject disObject = (JSONObject)parser.parse(new FileReader(DISTRICT[id]));
    //     JSONObject preObject = (JSONObject)parser.parse(new FileReader(PRECINT[id]));

    //     String bothJson = "["+disObject+","+preObject+"]"; //Put both objects in an array of 2 elements
    //     return ResponseEntity.ok(bothJson);
    // }

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

    // @GetMapping("/all")
    // @Produces(MediaType.APPLICATION_JSON)
    // public ResponseEntity<?> getAll() throws FileNotFoundException, IOException, ParseException{
    //     JSONParser parser = new JSONParser();
    //     LinkedList<JSONObject> result = new LinkedList<>();
    //     for(int i = 0; i<DISTRICT.length; i++){
    //         JSONObject disObject = (JSONObject)parser.parse(new FileReader(DISTRICT[i]));
    //         result.add(disObject);
    //     }
    //     return ResponseEntity.ok(result);
    // }

}