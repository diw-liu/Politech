package com.example.demo.algo;

import java.util.HashMap;

public class AlgorithmResult {
    private double seconds;
    private int cyclesRan;
    private int numPrecinctsChanged;
    private double popeq;

    public AlgorithmResult(double seconds, int cyclesRan, int numPrecinctsChanged, double popeq) {
        this.cyclesRan = cyclesRan;
        this.numPrecinctsChanged = numPrecinctsChanged;
        this.popeq = popeq;
        this.seconds = seconds;
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

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }
}
