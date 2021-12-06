package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="Populations")
public class Population implements Serializable {

    @Id
    @Column(name="ID")
    private String id;

    @OneToOne
    @JoinColumn(name = "ID")
    private Block block;
    
    @Column(name="TOTAL")
    private int total;

    @Column(name="HISPA")
    private int hispa;

    public int getTotal(){
        return total;
    } 
    public int getHispa(){
        return hispa;
    } 

    public void setTotal(int temp){
        this.total = temp;
    }
//    @Enumerated(EnumType.STRING)
//    @Column(name="type")
//    public PopulationType getType() { return type; }
//    public void setType(PopulationType type) { this.type = type; }
//
//    @Column(name="population")
//    public Long getPopulation() { return population; }
//    public void setPopulation(Long p) { population = p; }
}
