package com.example.demo.model;

import com.example.demo.enums.ElectionType;

import javax.persistence.*;

@Entity
@Table(name="Elections")
public class Election {
    private String id;
    private int totalVotes;
    private int democraticVotes;
    private int republicanVotes;
    private int otherVotes;
    private ElectionType type;

    @Id
    @Column(name="id")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @Column(name="totalVotes")
    public int getTotalVotes() { return totalVotes; }
    public void setTotalVotes(int v) { totalVotes = v; }

    @Column(name="democraticVotes")
    public int getDemocraticVotes() { return democraticVotes; }
    public void setDemocraticVotes(int d) { democraticVotes = d; }

    @Column(name="republicanVotes")
    public int getRepublicanVotes() { return republicanVotes; }
    public void setRepublicanVotes(int r) { republicanVotes = r; }

    @Column(name="otherVotes")
    public int getOtherVotes() { return otherVotes; }
    public void setOtherVotes(int o) { otherVotes = o; }

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    public ElectionType getType() { return type; }
    public void setType(ElectionType t) { type = t; }
}