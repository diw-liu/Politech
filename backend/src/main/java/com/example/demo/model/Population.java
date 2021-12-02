package com.example.demo.model;

import com.example.demo.enums.PopulationType;

import javax.persistence.*;

@Entity
@Table(name="Populations")
public class Population {
    private String id;
    private int total;
    private int hispanic;
    private int white;
    private int black;
    private int asian;
    private int other;

    @Id
    @Column(name="id")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @Column(name="total")
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    @Column(name="hispanic")
    public int getHispanic() { return hispanic; }
    public void setHispanic(int hispanic) { this.hispanic = hispanic; }

    @Column(name="white")
    public int getWhite() { return white; }
    public void setWhite(int white) { this.white = white; }

    @Column(name="black")
    public int getBlack() { return black; }
    public void setBlack(int black) { this.black = black; }

    @Column(name="asian")
    public int getAsian() { return asian; }
    public void setAsian(int asian) { this.asian = asian; }

    @Column(name="other")
    public int getOther() { return other; }
    public void setOther(int other) { this.other = other; }

//    @Enumerated(EnumType.STRING)
//    @Column(name="type")
//    public PopulationType getType() { return type; }
//    public void setType(PopulationType type) { this.type = type; }
//
//    @Column(name="population")
//    public Long getPopulation() { return population; }
//    public void setPopulation(Long p) { population = p; }


}
