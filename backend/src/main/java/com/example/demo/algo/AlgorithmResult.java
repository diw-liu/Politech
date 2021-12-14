package com.example.demo.algo;

import java.util.HashMap;

public class AlgorithmResult {
    private int cyclesRan;
    private int numPrecinctsChanged;
    private double popeq;
    private HashMap<String, Double> districtCompactness;

    public AlgorithmResult(int cyclesRan, int numPrecinctsChanged, double popeq, HashMap<String, Double> districtCompactness) {
        this.cyclesRan = cyclesRan;
        this.numPrecinctsChanged = numPrecinctsChanged;
        this.popeq = popeq;
        this.districtCompactness = districtCompactness;
    }

    public int getCyclesRan() {
        return cyclesRan;
    }

    public void setCyclesRan(int cyclesRan) {
        this.cyclesRan = cyclesRan;
    }

    public int getNumPrecinctsChanged() {
        return numPrecinctsChanged;
    }

    public void setNumPrecinctsChanged(int numPrecinctsChanged) {
        this.numPrecinctsChanged = numPrecinctsChanged;
    }

    public double getPopeq() {
        return popeq;
    }

    public void setPopeq(double popeq) {
        this.popeq = popeq;
    }

    public HashMap<String, Double> getDistrictCompactness() {
        return districtCompactness;
    }

    public void setDistrictCompactness(HashMap<String, Double> districtCompactness) {
        this.districtCompactness = districtCompactness;
    }
}
