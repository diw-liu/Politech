package com.example.demo.algo;

import java.util.List;

import com.example.demo.model.Districting;
import com.example.demo.model.Measures;

public class AlgorithmResult {
    Districting updatedRedistricting;
    Measures measures;
    List<Integer> changedPrecincts;
    int algorithmCycles;

    public Districting getUpdatedRedistricting() {
        return this.updatedRedistricting;
    }
    public Measures getMeasures() {
        return this.measures;
    }
    public List<Integer> getChangedPrecincts() {
        return this.changedPrecincts;
    }
    public int getAlgorithmCycles() {
        return this.algorithmCycles;
    }

    public void setUpdatedRedistricting(Districting updatedRedistricting) {
        this.updatedRedistricting = updatedRedistricting;
    }
    public void setMeasures(Measures measures) {
        this.measures = measures;
    }
    public void setChangedPrecincts(List<Integer> changedPrecincts) {
        this.changedPrecincts = changedPrecincts;
    }
    public void setAlgorithmCycles(int algorithmCycles) {
        this.algorithmCycles = algorithmCycles;
    }
}