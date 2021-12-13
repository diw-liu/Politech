package com.example.demo.handlers;

import com.example.demo.enums.Age;
import com.example.demo.enums.Status;
import com.example.demo.algo.*;
import com.example.demo.model.*;

import com.example.demo.projections.algorithm.DistrictNeighborsProjection;
import com.example.demo.projections.algorithm.PrecinctNeighborsProjection;
import com.example.demo.projections.summary.StateSummaryProjection;
import com.example.demo.repositories.DistrictRepository;
import com.example.demo.repositories.DistrictingRepository;
import com.example.demo.repositories.PrecinctRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class JobService {
    @Autowired
    DistrictingRepository districtingRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    PrecinctRepository precinctRepository;

    private Status status;
    final int interationThreshold = 10000;

    public JobService(){
        this.status = Status.IDLE;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public Status getStatus() { return status; }
    public int getInterationThreshold() { return interationThreshold; }

    public void loadPlan(HttpSession session) {
        String id = (String) session.getAttribute("selected");
        Optional<Districting> planResponse = districtingRepository.findById(id, Districting.class);
        Districting plan = planResponse.get();
        session.removeAttribute("selected");
        session.setAttribute("selected", plan);
    }

    public Status startJob(Constraints constraints, Age age, HttpSession session){
        if(status != Status.IDLE) {
            return Status.FAILED;
        } else {
            setStatus(Status.PROCESSING);
        }
//        Age age = (Age) session.getAttribute("age");
        // converting geoString to geo for every district, precinct, cb
        HashMap<String, Integer> districtPopulations = new HashMap<>();

        HashMap<String, District> dhash = new HashMap<>();
//        HashMap<String, HashMap<String, Precinct>> dToP = new HashMap<>();
//        HashMap<String, HashMap<String, CensusBlock>> dToC = new HashMap<>();
        ArrayList<String> did = new ArrayList<>();
//        HashMap<String, ArrayList<String>> pid = new HashMap<>();
//        ArrayList<String> cid = new ArrayList<>();

        Districting selected = (Districting) session.getAttribute("selected");
        for (District d : selected.getDistricts()) {
            d.convertStringToGeometry();
            HashMap<String, Precinct> phash = new HashMap<>();
            ArrayList<String> pidTemp = new ArrayList<>();
//            HashMap<String, CensusBlock> chash = new HashMap<>();
            for (Precinct p : d.getBorderPrecincts()) {
                p.convertStringToGeometry();
                // removing neighbors that are in the same district so we will only have precincts in other districts in the set
                p.getNeighbors().removeIf(neighborP -> neighborP.getDistrict().equals(p.getDistrict()));
                for (CensusBlock cb : p.getCensusBlocks()) {
                    cb.convertStringToGeometry();
                    cb.setParentDistrict(p.getDistrict());
//                    chash.put(d.getId(), cb);
//                    cid.add(cb.getId());
                }
                phash.put(p.getId(), p);
                pidTemp.add(p.getId());
            }

//            dToC.put(d.getId(), chash);
            dhash.put(d.getId(), d);
//            dToP.put(d.getId(), phash);
//            pid.put(d.getId(), pidTemp);
            did.add(d.getId());
            if (age == Age.TOTAL) {
                districtPopulations.put(d.getCd(), d.getPopulation().getTotal());
            } else {
                districtPopulations.put(d.getCd(), d.getVap().getTotal());
            }
        }
        AlgorithmSummary summary = new AlgorithmSummary(districtPopulations);
        session.setAttribute("summary", summary);

        Algorithm algo = new Algorithm(dhash, /*dToP,*/ did, /*pid,*/ selected, constraints, age);
        startAlgorithm(algo, age, summary, selected, session);
        return getStatus();
    }

    @Async
    public void startAlgorithm(Algorithm algo, Age age, AlgorithmSummary summary, Districting selected, HttpSession session) {
        int iterations = 0;
        while (iterations < getInterationThreshold() && getStatus() == Status.PROCESSING) {
            StateSummaryProjection ssp = (StateSummaryProjection) session.getAttribute("state");
            int totalPop;
            if (age == Age.TOTAL) {
                totalPop = ssp.getPopulation().getTotal();
            } else {
                totalPop = ssp.getVap().getTotal();
            }
            algo.runAlgorithm(totalPop);
            iterations++;
            if (iterations % 10 == 0) {
                // update the summary
                summary.setIterations(iterations);
                HashMap<String, Integer> districtPops = new HashMap<>();
                for (District d : selected.getDistricts())
                if (age == Age.TOTAL) {
                    districtPops.put(d.getCd(), d.getPopulation().getTotal());
                } else {
                    districtPops.put(d.getCd(), d.getVap().getTotal());
                }
                summary.setDistrictPopulations(districtPops);
            }
        }
    }
}


