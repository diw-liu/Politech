package com.example.demo.model;

import com.example.demo.enums.PopulationType;

import javax.persistence.*;

@Entity
@Table(name="Populations")
public class Population {
    private Long id;
    private PopulationType type;
    private Long population;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    public PopulationType getType() { return type; }
    public void setType(PopulationType type) { this.type = type; }

    @Column(name="population")
    public Long getPopulation() { return population; }
    public void setPopulation(Long p) { population = p; }
}
