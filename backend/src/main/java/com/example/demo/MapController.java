package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
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
    //String[] DISTRICT = {"MDdistricts", "districtMI", "districtPA"};
    // File folder = new File("../data/MDdistricts.json");
    // String districtMD = "src/main/data/MDdistricts.json";
    // String precintMD = "src/main/data/maryland.json";
    // String districtMI = "src/main/data/MIdistrict.json";
    // String precintMI = "src/main/data/michigan.json";
    // String districtPA = "src/main/data/PAdistrict.json";
    // String precintPA = "src/main/data/pennsylvania.json";
    // String[] DISTRICT = {districtMD,precintMD,districtMI,precintMI,districtPA,precintPA};
    String districtMD = "src/main/data/MDdistricts.json";
    String precinctMD = "src/main/data/maryland.json";
    String districtMI = "src/main/data/MIdistrict.json";
    String precinctMI = "src/main/data/michigan.json";
    String districtPA = "src/main/data/PAdistrict.json";
    String precinctPA = "src/main/data/pennsylvania.json";
    String[] DISTRICT = {districtMD,districtMI,districtPA};
    String[] PRECINCT = {precinctMD,precinctMI,precinctPA};
    @GetMapping("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStateMap(@PathVariable int id) throws FileNotFoundException, IOException, ParseException{

        //File folder = new File(DISTRICT[id]);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(DISTRICT[id]));
        JSONObject jsonObject = (JSONObject)obj;

        Object obj2 = parser.parse(new FileReader(PRECINCT[id]));
        JSONObject jsonObject2 = (JSONObject)obj2;

        String res = "[" + jsonObject.toString() + "," + jsonObject2.toString() + "]";

        return res;

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

    @GetMapping("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> getAll() throws FileNotFoundException, IOException, ParseException{

        JSONParser parser = new JSONParser();

        JSONObject combined = new JSONObject();
        // for(String key : JSONObject.getNames(Obj2))
        // {
        //      merged.put(key, Obj2.get(key));
        // }
        for(int i = 0; i<DISTRICT.length; i++){
            JSONObject disObject = (JSONObject)parser.parse(new FileReader(DISTRICT[i]));
            combined.put(i, disObject);
        }

        // JSONObject preObject = (JSONObject)parser.parse(new FileReader(PRECINT[id]));

        // String bothJson = "["+disObject+","+preObject+"]"; //Put both objects in an array of 2 elements
        return ResponseEntity.ok(combined);
    }

    // @GetMapping("/all")
    // @Produces("application/json")
    // public int getAllState(){
    //     return f.listFiles();
    // }
}