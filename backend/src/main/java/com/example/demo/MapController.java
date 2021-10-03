package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@RestController
@RequestMapping("/api")
class MapController{
    String[] DISTRICT = {"MDdistricts", "districtMI", "districtPA"};
    File folder = new File("../data/MDdistricts.json");
    String dummy = "src/main/data/MDdistricts.json";

    @GetMapping("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStateMap(@PathVariable int id) throws FileNotFoundException, IOException, ParseException{

        folder = new File(dummy);

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(dummy));
        JSONObject jsonObject = (JSONObject)obj;

        return jsonObject.toString();

        // if(folder.isFile()){
        //     return "ok";
        // }
        
        // return jsonObject;
        // return folder;
        // return "o";
        // File[] listOfFiles = folder.listFiles();
        // for(File temp: listOfFiles){
        //     System.out.println(temp.getName());
        // }
        // return listOfFiles[id];
        // return Collections.singletonMap("response", dummy);
        // return folder.getAbsolutePath();
    }

    // @GetMapping("/all")
    // @Produces("application/json")
    // public int getAllState(){
    //     return f.listFiles();
    // }
}