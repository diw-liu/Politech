package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="Measures")
public class Measures {
    private Long id;
    private double populationEquality;
    private double racialDeviation;
    private double efficiencyGap;
    private double partisanSymmetry;
    private double graphCompactness;
    private double objectiveFunction;
    private int opportunityDistricts;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    public Long getId() {
        return this.id;
    }
    @Column(name="populationEquality")
    public double getPopulationEquality() {
        return this.populationEquality;
    }
    @Column(name="racialDeviation")
    public double getRacialDeviation() {
        return this.racialDeviation;
    }
    @Column(name="efficienctGap")
    public double getEfficiencyGap() {
        return this.efficiencyGap;
    }
    @Column(name="partisanSymmetry")
    public double getPartisanSymmetry() {
        return this.partisanSymmetry;
    }
    @Column(name="graphCompactness")
    public double getGraphCompactness() {
        return this.graphCompactness;
    }
    @Column(name="objectiveFunction")
    public double getObjectiveFunction() {
        return this.objectiveFunction;
    }
    @Column(name="opportunityDistricts")
    public int getOpportunityDistricts() {
        return this.opportunityDistricts;
    }

    public void setId(Long id) { this.id = id; }
    public void setPopulationEquality(double populationEquality) {
        this.populationEquality = populationEquality;
    }
    public void setRacialDeviation(double racialDeviation) {
        this.racialDeviation = racialDeviation;
    }
    public void setEfficiencyGap(double efficiencyGap) {
        this.efficiencyGap = efficiencyGap;
    }
    public void setPartisanSymmetry(double partisanSymmetry) {
        this.partisanSymmetry = partisanSymmetry;
    }
    public void setGraphCompactness(double graphCompactness) {
        this.graphCompactness = graphCompactness;
    }
    public void setObjectiveFunction(double objectiveFunction) {
        this.objectiveFunction = objectiveFunction;
    }
    public void setOpportunityDistricts(int opportunityDistricts) {
        this.opportunityDistricts = opportunityDistricts;
    }
}
