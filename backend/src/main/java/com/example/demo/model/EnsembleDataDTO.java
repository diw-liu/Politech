package com.example.demo.model;

public class EnsembleDataDTO {
    private int state;
    private int cd;
    private String min;
    private String q1;
    private String q3;
    private String max;
    private String med;

    public EnsembleDataDTO(int state, int cd, String min, String q1, String q3, String max, String med) {
        this.state = state;
        this.cd = cd;
        this.min = min;
        this.q1 = q1;
        this.q3 = q3;
        this.max = max;
        this.med = med;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMed() {
        return med;
    }

    public void setMed(String med) {
        this.med = med;
    }
}
