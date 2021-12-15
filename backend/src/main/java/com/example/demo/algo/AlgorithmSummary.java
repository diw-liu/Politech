package com.example.demo.algo;

import java.util.HashMap;

public class AlgorithmSummary {
    private int iterations;
    private double currentPopEq;
    private HashMap<String, Integer> districtPopulations;

    public AlgorithmSummary(double currentPopEq, HashMap<String, Integer> districtPopulations) {
        iterations = 0;
        this.currentPopEq = currentPopEq;
        this.districtPopulations = districtPopulations;
    }

    public double getCurrentPopEq() {
        return currentPopEq;
    }

    public void setCurrentPopEq(double currentPopEq) {
        this.currentPopEq = currentPopEq;
    }

    public int getIterations() { return iterations; }
    public HashMap<String, Integer> getDistrictPopulations() { return districtPopulations; }

    public void setIterations(int i) { iterations = i; }
    public void setDistrictPopulations(HashMap<String, Integer> dpop) { districtPopulations = dpop; }
}
