package com.example.demo.model;

import com.example.demo.enums.PopulationType;

import javax.persistence.*;

@Entity
@Table(name="Populations")
public class Population {
    private String id;
//    private PopulationType type;
//    private int population;
    private int total;
    private int white;
    private int black;
    private int hispanic;
    private int asian;
    private int nativ;
    private int other;

//    private Districting districting;
//    private District district;
//    private Precinct precinct;
//    private CensusBlock censusBlock;

    @Id
    @Column(name="id")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @Column(name="TOTAL")
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    @Column(name="HISPA")
    public int getHispanic() { return hispanic; }
    public void setHispanic(int hispanic) { this.hispanic = hispanic; }

    @Column(name="WHITE")
    public int getWhite() { return white; }
    public void setWhite(int white) { this.white = white; }

    @Column(name="BLACK")
    public int getBlack() { return black; }
    public void setBlack(int black) { this.black = black; }

    @Column(name="ASIAN")
    public int getAsian() { return asian; }
    public void setAsian(int asian) { this.asian = asian; }

    @Column(name="NATIV")
    public int getNativ() { return nativ; }
    public void setNativ(int n) { this.nativ = n; }

    @Column(name="OTHER")
    public int getOther() { return other; }
    public void setOther(int other) { this.other = other; }

//    @Enumerated(EnumType.STRING)
//    @Column(name="type")
//    public PopulationType getType() { return type; }
//    public void setType(PopulationType type) { this.type = type; }
//
//    @Column(name="population")
//    public int getPopulation() { return population; }
//    public void setPopulation(int p) { population = p; }


}
