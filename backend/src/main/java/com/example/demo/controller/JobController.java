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
    public Status startJob(@RequestParam double goal, @RequestParam int lower, @RequestParam int higher, HttpServletRequest request){
//        State state = (State) session.getAttribute(StateName); // get state from session
        Constraints constraints = new Constraints(goal, lower, higher);

        HttpSession session = request.getSession();
        session.setAttribute("constraints", constraints);
//        return jobService.startJob(state, constraints, session);
        return null;
    }

    @GetMapping("/summary")
    public AlgorithmSummary getSummary(HttpSession session){
        AlgorithmSummary algorithmSummary = (AlgorithmSummary) session.getAttribute("summary");
        return algorithmSummary;
    }

    @GetMapping("/result")
    public AlgorithmResult getResult(HttpSession session){
        AlgorithmResult algorithmResult = (AlgorithmResult) session.getAttribute("result");
        return algorithmResult;
    }

    @PostMapping("/pauseJob")
    public Status pauseJob(){
        return jobService.pauseJob();
    }

    @PostMapping("/resumeJob")
    public Status resumeJob(HttpSession session){
        return jobService.resumeJob(session);
    }

    @PostMapping("/stopJob")
    public Status stopJob(@RequestParam String StateName){
        return jobService.getStatus();
    }

}
