package com.example.demo.handlers;

import com.example.demo.enums.Status;
import com.example.demo.algo.*;
import com.example.demo.model.District;
import com.example.demo.model.Precinct;
import com.example.demo.model.State;
import com.example.demo.model.Districting;

import com.example.demo.projections.algorithm.DistrictNeighborsProjection;
import com.example.demo.projections.algorithm.PrecinctNeighborsProjection;
import com.example.demo.repositories.DistrictRepository;
import com.example.demo.repositories.PrecinctRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class JobService {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    PrecinctRepository precinctRepository;

    Status status;
//    State state;
//    Algorithm algorithm;
//    boolean algoRunnningLock;
//    int cyclesThreshold = 1000;
//    int attemptsThreshold = 5;
//    int failedAttempts = 0;
//    Districting updatedRedistricting;


    public JobService(){
        this.status = Status.IDLE;
    }

    public Status startJob(Constraints constraints, HttpSession session){
        if(status != Status.IDLE) {
            return Status.FAILED;
        }

        @SuppressWarnings("unchecked")
        HashMap<String, District> dhash = (HashMap<String, District>) session.getAttribute("selectedDistricts");
        // getting district neighbors
        for (District d : dhash.values()) {
            Set<District> dneighbors = new HashSet<>();
            Optional<DistrictNeighborsProjection> dnpResponse = districtRepository.findById(d.getId(), DistrictNeighborsProjection.class);
            DistrictNeighborsProjection dnp = dnpResponse.get();
            Iterator<DistrictNeighborsProjection.DistrictId> dnpi = dnp.getNeighbors().iterator();
            while(dnpi.hasNext()) {
                dneighbors.add(dhash.get(dnpi.next().getId()));
            }
            d.setNeighbors(dneighbors);
        }
        session.removeAttribute("selectedDistricts");
        session.setAttribute("selectedDistricts", dhash);

        @SuppressWarnings("unchecked")
        HashMap<String, Precinct> phash = (HashMap<String, Precinct>) session.getAttribute("selectedPrecincts");
        // getting precinct neighbors
        for (Precinct p : phash.values()) {
            Set<Precinct> pneighbors = new HashSet<>();
            Optional<PrecinctNeighborsProjection> pnpResponse = precinctRepository.findById(p.getId(), PrecinctNeighborsProjection.class);
            PrecinctNeighborsProjection pnp = pnpResponse.get();
            Iterator<PrecinctNeighborsProjection.PrecinctId> pnpi = pnp.getNeighbors().iterator();
            while(pnpi.hasNext()) {
                pneighbors.add(phash.get(pnpi.next().getId()));
            }
            p.setNeighbors(pneighbors);
        }
        session.removeAttribute("selectedPrecincts");
        session.setAttribute("selectedPrecincts", phash);
        // instantiate an algorithm class
        // call on that to start the algorithm
        return null;
    }
//
//    public Status pauseJob(){
//        status = Status.PAUSE;
//        return getStatus();
//    }
//
//    public Status resumeJob(HttpSession session){
//        status = Status.PROCESSING;
//        while(this.algoRunnningLock == true){ // wait until the current algo running is done, then start the algo running again.
//            try{
//                Thread.sleep(1000);
//            }
//            catch(InterruptedException ex){
//                Thread.currentThread().interrupt();
//            }
//        }
//        startAlgorithm(session);
//        return getStatus();
//    }
//
//    public Status stopJob(HttpSession session){
//        status = Status.IDLE;
//        algorithm.setAlgorithmCycles(0);  // reset the algorithmCycles to 0
//        terminateAlgorithm(session);
//        return getStatus();
//    }
//
//    public void startAlgorithm(HttpSession session) {
//        while (algorithm.getAlgorithmCycles() < cyclesThreshold && status == Status.PROCESSING) {
//            this.algoRunnningLock = true; // lock the algo running, so it can't be running again until it is done.
//            boolean success = algorithm.runAlgorithm();
//            if(success == false){
//                failedAttempts++;
//            }
//            // every 10 cycles, return a algorithm summary to the client
//            if(algorithm.getAlgorithmCycles() % 10 == 0){
//                AlgorithmSummary algorithmSummary = new AlgorithmSummary();
//                algorithmSummary.setAlgorithmCycles(algorithm.getAlgorithmCycles());
//                algorithmSummary.setMeasures(algorithm.getRedistricting().getMeasures());
//                session.setAttribute("summary", algorithmSummary);  // send algorithmSummary to the client
//            }
//            if(status != Status.PROCESSING) {
//                System.out.print("stop or pause successfully");  // notify client stop or pause successfully
//            }
//            this.algoRunnningLock = false;
//            if(failedAttempts >= attemptsThreshold){
//                break;
//            }
//        }
//        terminateAlgorithm(session);
//    }
//    public void terminateAlgorithm(HttpSession session) {
//        AlgorithmResult algorithmResult = new AlgorithmResult();
//        algorithmResult.setAlgorithmCycles(algorithm.getAlgorithmCycles());
//        algorithmResult.setMeasures(algorithm.getRedistricting().getMeasures());
//        algorithmResult.setUpdatedRedistricting(algorithm.getRedistricting());
//        session.setAttribute("result", algorithmResult);  // send algorithmResult to the client
//    }
//    public boolean checkThresholds(int algorithmCycles) {
//        return false;
//    }
//
//    public State getstate(){
//        return this.state;
//    }
//
//    public void setstate(State state){
//        this.state = state;
//    }
//
//    public Status getStatus(){
//        return this.status;
//    }
//
//    public void setStatus(Status status){
//        this.status = status;
//    }
//
//    public Algorithm getAlgorithm() {
//        return this.algorithm;
//    }
//    public int getCyclesThreshold() {
//        return this.cyclesThreshold;
//    }
//    public int getAttemptsThreshold() {
//        return this.attemptsThreshold;
//    }
//    public int getFailedAttempts() {
//        return this.failedAttempts;
//    }
//    public Districting getUpdatedRedistricting() {
//        return this.updatedRedistricting;
//    }
//
//    public void setAlgorithm(Algorithm algorithm) {
//        this.algorithm = algorithm;
//    }
//    public void setCyclesThreshold(int cyclesThreshold) {
//        this.cyclesThreshold = cyclesThreshold;
//    }
//    public void setAttemptsThreshold(int attemptsThreshold) {
//        this.attemptsThreshold = attemptsThreshold;
//    }
//    public void setFailedAttempts(int failedAttempts) {
//        this.failedAttempts = failedAttempts;
//    }
//    public void setUpdatedRedistricting(Districting updatedRedistricting) {
//        this.updatedRedistricting = updatedRedistricting;
//    }

}


