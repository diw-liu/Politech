package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="Measures")
public class Measures {
    private String id;
    private Districting districting;
    private double populationEquality;
    private double polsbyPopper;
    private double objectiveFunction;
    private int opportunityDistricts;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    public String getId() {
        return this.id;
    }

    @OneToOne
    @JoinColumn(name="districtingId")
//    @JsonBackReference
    public Districting getDistricting() { return districting; }
    public void setDistricting(Districting d) { districting = d; }

    @Column(name="populationEquality")
    public double getPopulationEquality() {
        return this.populationEquality;
    }
    @Column(name="polsbyPopper")
    public double getPolsbyPopper() { return this.polsbyPopper; }
    @Column(name="objectiveFunction")
    public double getObjectiveFunction() {
        return this.objectiveFunction;
    }
    @Column(name="opportunityDistricts")
    public int getOpportunityDistricts() {
        return this.opportunityDistricts;
    }

    public void setId(String id) { this.id = id; }
    public void setPopulationEquality(double populationEquality) {
        this.populationEquality = populationEquality;
    }
    public void setPolsbyPopper(double p) { this.polsbyPopper = p; }
    public void setObjectiveFunction(double objectiveFunction) {
        this.objectiveFunction = objectiveFunction;
    }
    public void setOpportunityDistricts(int opportunityDistricts) {
        this.opportunityDistricts = opportunityDistricts;
    }
}
