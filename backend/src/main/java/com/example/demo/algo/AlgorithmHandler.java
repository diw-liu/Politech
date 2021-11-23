package com.example.demo.algo;

import com.example.demo.model.Districting;

public class AlgorithmHandler {
    Algorithm algorithm;
    int cyclesThreshold = 1000;
    int attemptsThreshold = 5;
    int failedAttempts = 0;
    Districting updatedRedistricting;

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

    public void startAlgorithm() {
        while (algorithm.getAlgorithmCycles() < cyclesThreshold) {
            boolean success = algorithm.runAlgorithm();
            if(success == false){
                failedAttempts++;
            }
            if( failedAttempts >= attemptsThreshold){
                break;
            }
        }
        terminateAlgorithm();
    }
    public void terminateAlgorithm() {
        
    }
    public boolean checkThresholds(int algorithmCycles) {
        return false;
    }
}