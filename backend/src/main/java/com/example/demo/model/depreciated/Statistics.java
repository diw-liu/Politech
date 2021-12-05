//package com.example.demo.model;
//
//import javax.persistence.*;
//import javax.swing.*;
//
//@Entity
//@Table(name="Statistics")
//public class Statistics {
//    private Long id;
//    private BoxAndWhisker plot;
//    private double min;
//    private double max;
//    private double upperQuartile;
//    private double lowerQuartile;
//    private double median;
//    private double point;
//
//    @Id
//    @Column(name="id")
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    @ManyToOne(fetch=FetchType.EAGER)
//    @JoinColumn(name="plotId")
//    public BoxAndWhisker getPlot() { return plot; }
//    public void setPlot(BoxAndWhisker p) { this.plot = p; }
//
//    @Column(name="min")
//    public double getMin() { return min; }
//    public void setMin(double min) { this.min = min; }
//
//    @Column(name="max")
//    public double getMax() { return max; }
//    public void setMax(double max) { this.max = max; }
//
//    @Column(name="upperQuartile")
//    public double getUpperQuartile() { return upperQuartile; }
//    public void setUpperQuartile(double upperQuartile) { this.upperQuartile = upperQuartile; }
//
//    @Column(name="lowerQuartile")
//    public double getLowerQuartile() { return lowerQuartile; }
//    public void setLowerQuartile(double lowerQuartile) { this.lowerQuartile = lowerQuartile; }
//
//    @Column(name="median")
//    public double getMedian() { return median; }
//    public void setMedian(double median) { this.median = median; }
//
//    @Column(name="point")
//    public double getPoint() { return point; }
//    public void setPoint(double median) { this.point = point; }
//}