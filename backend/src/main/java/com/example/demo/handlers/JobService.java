package com.example.demo.handlers;

import com.example.demo.enums.Status;
import com.example.demo.algo.*;
import com.example.demo.model.State;
import com.example.demo.model.Districting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class JobService {
    Status status;
    State state;
    Algorithm algorithm;
    int cyclesThreshold = 1000;
    int attemptsThreshold = 5;
    int failedAttempts = 0;
    Districting updatedRedistricting;

    public JobService(){
        this.status = Status.IDLE;
    }

    public Status startJob(State state, Constraints constraints, HttpSession session){
        if(status != Status.IDLE) {
            return Status.FAILED;
        }
        setstate(state);
        algorithm = new Algorithm(state.getRedistricted(), constraints);
        setStatus(Status.PROCESSING);
        startAlgorithm(session);
        return getStatus();
    }

    public Status pauseJob(){
        status = Status.PAUSE;
        return getStatus();
    }

    public Status resumeJob(HttpSession session){
        status = Status.PROCESSING;
        startAlgorithm(session);
        return getStatus();
    }

    public Status stopJob(HttpSession session){
        status = Status.IDLE;
        algorithm.setAlgorithmCycles(0);  // reset the algorithmCycles to 0
        terminateAlgorithm(session);
        return getStatus();
    }

    public void startAlgorithm(HttpSession session) {
        while (algorithm.getAlgorithmCycles() < cyclesThreshold && status == Status.PROCESSING) {
            boolean success = algorithm.runAlgorithm();
            if(success == false){
                failedAttempts++;
            }
            // every 10 cycles, return a algorithm summary to the client
            if(algorithm.getAlgorithmCycles() % 10 == 0){
                AlgorithmSummary algorithmSummary = new AlgorithmSummary();
                algorithmSummary.setAlgorithmCycles(algorithm.getAlgorithmCycles());
                algorithmSummary.setMeasures(algorithm.getRedistricting().getMeasures());
                session.setAttribute("summary", algorithmSummary);  // send algorithmSummary to the client
            }
            if(status != Status.PROCESSING) {
                System.out.print("stop or pause successfully");  // notify client stop or pause successfully
                
            }
            if(failedAttempts >= attemptsThreshold){
                break;
            }
        }
        terminateAlgorithm(session);
    }
    public void terminateAlgorithm(HttpSession session) {
        AlgorithmResult algorithmResult = new AlgorithmResult();
        algorithmResult.setAlgorithmCycles(algorithm.getAlgorithmCycles());
        algorithmResult.setMeasures(algorithm.getRedistricting().getMeasures());
        algorithmResult.setUpdatedRedistricting(algorithm.getRedistricting());
        session.setAttribute("result", algorithmResult);  // send algorithmResult to the client
    }
    public boolean checkThresholds(int algorithmCycles) {
        return false;
    }

    public State getstate(){
        return this.state;
    }

    public void setstate(State state){
        this.state = state;
    }

    public Status getStatus(){
        return this.status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public Algorithm getAlgorithm() {
        return this.algorithm;
    }
    public int getCyclesThreshold() {
        return this.cyclesThreshold;
    }
    public int getAttemptsThreshold() {
        return this.attemptsThreshold;
    }
    public int getFailedAttempts() {
        return this.failedAttempts;
    }
    public Districting getUpdatedRedistricting() {
        return this.updatedRedistricting;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }
    public void setCyclesThreshold(int cyclesThreshold) {
        this.cyclesThreshold = cyclesThreshold;
    }
    public void setAttemptsThreshold(int attemptsThreshold) {
        this.attemptsThreshold = attemptsThreshold;
    }
    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }
    public void setUpdatedRedistricting(Districting updatedRedistricting) {
        this.updatedRedistricting = updatedRedistricting;
    }

}


