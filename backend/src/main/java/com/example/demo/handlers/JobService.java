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
    private int iterations; // make it global so it can be reused after resume
    private Algorithm algo; // make it global so it can be reused after resume
    AlgorithmSummary summary; // make it global so it can be reused after resume
    Districting selected; // make it global so it can be reused after resume
    Age age; // make it global so it can be reused after resume
    HttpSession session; // make it global so it can be reused after resume
    boolean algoRunnningLock; // locks when the algo is in processing status
    final int interationThreshold = 10000;


    public JobService(){
        this.status = Status.IDLE;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public Status getStatus() { return status; }
    public int getInterationThreshold() { return interationThreshold; }
    public int getIterations() { return this.iterations; }

    public void loadPlan(HttpSession session) {
        String id = (String) session.getAttribute("selected");
        Optional<Districting> planResponse = districtingRepository.findById(id, Districting.class);
        Districting plan = planResponse.get();
        session.removeAttribute("selected");
        session.setAttribute("selected", plan);
    }

    public Status startJob(Constraints constraints, Age age, HttpSession session){
        if(status == Status.PROCESSING) {
            return Status.FAILED;
        }
        setStatus(Status.PROCESSING);
        this.session = session; 
//        Age age = (Age) session.getAttribute("age");
        // converting geoString to geo for every district, precinct, cb
        HashMap<String, Integer> districtPopulations = new HashMap<>();

        HashMap<String, District> dhash = new HashMap<>();
//        HashMap<String, HashMap<String, Precinct>> dToP = new HashMap<>();
//        HashMap<String, HashMap<String, CensusBlock>> dToC = new HashMap<>();
        ArrayList<String> did = new ArrayList<>();
//        HashMap<String, ArrayList<String>> pid = new HashMap<>();
//        ArrayList<String> cid = new ArrayList<>();

        this.selected = (Districting) session.getAttribute("selected");
        for (District d : this.selected.getDistricts()) {
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
        this.summary = new AlgorithmSummary(districtPopulations);
        session.setAttribute("summary", summary);
        this.algo = new Algorithm(dhash, /*dToP,*/ did, /*pid,*/ selected, constraints, age);
        this.age = age;
        // startAlgorithm(algo, age, summary, selected, session);
        startAlgorithm();
        return getStatus();
    }
    
    @Async
    public void startAlgorithm() {
        while (iterations < getInterationThreshold() && (getStatus() == Status.PROCESSING || getStatus() == Status.PAUSE)) {
            while(getStatus() == Status.PAUSE){
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex){
                    Thread.currentThread().interrupt();
                }
            }
            algoRunnningLock = true; // locks each algo iteration, so there can be only one algo runnning at a time
            StateSummaryProjection ssp = (StateSummaryProjection) session.getAttribute("state");
            int totalPop;
            if (age == Age.TOTAL) {
                totalPop = ssp.getPopulation().getTotal();
            } else {
                totalPop = ssp.getVap().getTotal();
            }
            algo.runAlgorithm(totalPop);
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
            iterations++;
            algoRunnningLock = false; // unlocks algo iteration when the iteration is done
        }
        if(this.status == Status.PROCESSING){ // if algo is complete naturally(not by stop or pause), return completed status
            this.status = Status.COMPLETED;
        }
    }


    public Status pauseJob(){
        if(status != Status.PROCESSING){
            return Status.FAILED;
        }
        status = Status.PAUSE;
        while(this.algoRunnningLock == true){} // wait until the current algo running is done, then return pause status
        return getStatus();
    }


    public Status resumeJob(){
        if(status != Status.PAUSE){
            return Status.FAILED;
        }
        status = Status.PROCESSING;
        while(this.algoRunnningLock == true){ // wait until the current algo running is done, then start the algo running again.
            // try{
            //     Thread.sleep(1000);
            // }
            // catch(InterruptedException ex){
            //     Thread.currentThread().interrupt();
            // }
        }
        return getStatus();
    }

    public Status stopJob(){
        if(this.status != Status.PROCESSING && this.status != Status.PAUSE){  // if the algo is not processing or paused, return failed status
            return Status.FAILED; 
        } 
        status = Status.COMPLETED;
        while(this.algoRunnningLock == true){ // wait until the current algo running is done, then return stop status
            // try{
            //     Thread.sleep(1000);
            // }
            // catch(InterruptedException ex){
            //     Thread.currentThread().interrupt();
            // }
        }
        return getStatus();
    }
}


