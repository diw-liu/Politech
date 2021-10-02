package com.example.demo;

import java.io.File;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
class MapController{
    String[] DISTRICT = {"MDdistricts", "districtMI", "districtPA"};
    File folder = new File("../data/MDdistricts.json");

    @GetMapping("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStateMap(@PathVariable int id){
        String message="{num:125, nickname:'Alonzo' type:'Adult'}";
        return message;
        // return folder;
        // if(folder.isFile()){
        //     return "okay";
        // }
        // return "o";
        // File[] listOfFiles = folder.listFiles();
        // for(File temp: listOfFiles){
        //     System.out.println(temp.getName());
        // }
        // return listOfFiles[id];
    }

    // @GetMapping("/all")
    // @Produces("application/json")
    // public int getAllState(){
    //     return f.listFiles();
    // }
}