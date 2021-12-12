<<<<<<< HEAD
//package com.example.demo.controller;
//
//import com.example.demo.algo.*;
//import com.example.demo.enums.*;
//import com.example.demo.model.State;
//import com.example.demo.services.JobService;
//
//import org.apache.commons.io.IOUtils;
//import org.json.simple.JSONObject;
//import org.locationtech.jts.io.WKTReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//import org.json.simple.parser.ParseException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//@RestController
//@RequestMapping("/api")
//class JobController{
//    private final JobService jobService;
//    @Autowired
//    public JobController(JobService jobService){
//        this.jobService = jobService;
//    }
//
//    @PostMapping("/startJob")
//    public Status startJob(@RequestParam String StateName, HttpSession session){
//        State state = (State) session.getAttribute("state");
//        return jobService.startJob(state);
//    }
//}
=======
package com.example.demo.controller;

import com.example.demo.algo.*;
import com.example.demo.enums.*;
import com.example.demo.handlers.JobService;
import com.example.demo.model.State;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/api")
class JobController{
    private final JobService jobService;
    @Autowired
    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @PostMapping("/startJob")
    public Status startJob(@RequestParam String StateName, HttpSession session){
        State state = (State) session.getAttribute("state");
        Constraints constraints;
        return jobService.startJob(state, constraints);
    }


}
>>>>>>> 71a698175d278e6b91bbd81763c64737a595160f
