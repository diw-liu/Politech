package com.example.demo.algo;

public class Constraints {
    double populationEquality;
    int lowerOpportunity;
    int higherOpportunity;

    public Constraints(double populationEquality, int lowerOpportunity, int higherOpportunity) {
        this.populationEquality = populationEquality;
        this.lowerOpportunity = lowerOpportunity;
        this.higherOpportunity = higherOpportunity;
    }

    public double getPopulationEquality() {
        return this.populationEquality;
    }
    public int getLowerOpportunity() {
        return this.lowerOpportunity;
    }
    public int getHigherOpportunity() {
        return higherOpportunity;
    }

//    public void setPopulationEquality(double populationEquality) {
//        this.populationEquality = populationEquality;
//    }
//    public void setLowerOpportunity(int opportunityDistricts) {
//        this.lowerOpportunity = opportunityDistricts;
//    }
//    public void setHigherOpportunity(int d) {
//        higherOpportunity = d;
//    }
}
