package com.example.demo.model;

import com.example.demo.enums.Basis;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="BoxAndWhiskers")
public class BoxAndWhisker{
    private String id;
    private State state;
    private Basis basis;
    private double min;
    private double max;
    private double upperQuartile;
    private double lowerQuartile;
    private double median;

    @Id
    @Column(name="id")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="stateId")
    public State getState() { return this.state; }
    public void setState(State d) { this.state = d; }

    @Enumerated(EnumType.STRING)
    @Column(name="basis")
    public Basis getBasis() { return basis; }
    public void setBasis(Basis basis) { this.basis = basis; }

    @Column(name="min")
    public double getMin() { return min; }
    public void setMin(double min) { this.min = min; }

    @Column(name="max")
    public double getMax() { return max; }
    public void setMax(double max) { this.max = max; }

    @Column(name="upperQuartile")
    public double getUpperQuartile() { return upperQuartile; }
    public void setUpperQuartile(double upperQuartile) { this.upperQuartile = upperQuartile; }

    @Column(name="lowerQuartile")
    public double getLowerQuartile() { return lowerQuartile; }
    public void setLowerQuartile(double lowerQuartile) { this.lowerQuartile = lowerQuartile; }

    @Column(name="median")
    public double getMedian() { return median; }
    public void setMedian(double median) { this.median = median; }

}