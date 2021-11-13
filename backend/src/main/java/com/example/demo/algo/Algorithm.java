package com.example.demo.algo;
import com.example.demo.model.Districting;

public class Algorithm {
    Districting redistricting;
    int algorithmCycles;
    int status; 

    public Districting getRedistricting() {
        return this.redistricting;
    }
    
    public int getAlgorithmCycles() {
        return this.algorithmCycles;
    }
    
    public int getStatus() {
        return this.status;
    }

    public void getRedistricting(Districting redistricting) {
        this.redistricting = redistricting;
    }
    public void setAlgorithmCycles(int algorithmCycles) {
        this.algorithmCycles = algorithmCycles;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public void runAlgorithm() {}
    public void calculateMove() {}
    public void selectRandomDistricts() {}
    public void makeMove() {}
    public void generateNewBoundary() {}
}