package com.example.demo.algo;

import java.util.HashMap;

public class AlgorithmSummary {
    private int iterations;
    private HashMap<String, Integer> districtPopulations;

    public AlgorithmSummary(HashMap<String, Integer> districtPopulations) {
        iterations = 0;
        this.districtPopulations = districtPopulations;
    }

    public int getIterations() { return iterations; }
    public HashMap<String, Integer> getDistrictPopulations() { return districtPopulations; }

    public void setIterations(int i) { iterations = i; }
    public void setDistrictPopulations(HashMap<String, Integer> dpop) { districtPopulations = dpop; }
}
