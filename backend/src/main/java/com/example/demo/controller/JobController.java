package com.example.demo.controller;

import com.example.demo.algo.*;
import com.example.demo.enums.*;
import com.example.demo.handlers.JobService;
import com.example.demo.model.District;
import com.example.demo.model.State;

import com.example.demo.projections.algorithm.DistrictNeighborsProjection;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/start")
    public void startJob(@RequestParam double goal, @RequestParam int lower, @RequestParam int higher, HttpServletRequest request){
        Constraints constraints = new Constraints(goal, lower, higher);
        HttpSession session = request.getSession();
        session.setAttribute("constraints", constraints);
        jobService.loadPlan(session);
        jobService.startJob(constraints, session);
    }
}
