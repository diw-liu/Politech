package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="Blocks")
public class Block {
    
    @Id
    @Column(name="ID")
    private String id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "block")
    private Population population;

    public Population getPopulation(){return population;}
}
