package com.example.demo.handlers;

import com.example.demo.enums.Age;
import com.example.demo.enums.Status;
import com.example.demo.algo.*;
import com.example.demo.model.*;

import com.example.demo.projections.summary.StateSummaryProjection;
import com.example.demo.repositories.DistrictRepository;
import com.example.demo.repositories.DistrictingRepository;
import com.example.demo.repositories.PrecinctRepository;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.io.WKTWriter;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.wololo.geojson.Feature;
import org.wololo.jts2geojson.GeoJSONWriter;

import javax.measure.Measure;
import javax.measure.quantity.Area;
import javax.measure.quantity.Length;
import javax.measure.unit.SI;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class JobService {
    @Autowired
    DistrictingRepository districtingRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    PrecinctRepository precinctRepository;

    private Status status;
    private Constraints constraints;
    private double ideal;
    private int iterations; // make it global so it can be reused after resume
    private Algorithm algo; // make it global so it can be reused after resume
    AlgorithmSummary summary; // make it global so it can be reused after resume
    Districting selected; // make it global so it can be reused after resume
    Age age; // make it global so it can be reused after resume
    HttpSession session; // make it global so it can be reused after resume
    boolean algoRunnningLock; // locks when the algo is in processing status
    AlgorithmResult algorithmResult;
    final int interationThreshold = 1000000;
    private long timeStart;
    private long timeEnd;


    public JobService(){
        this.status = Status.IDLE;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public Status getStatus() { return status; }
    public int getInterationThreshold() { return interationThreshold; }
    public int getIterations() { return this.iterations; }
    public AlgorithmSummary getSummary() { return this.summary; }
    public AlgorithmResult getResult(){ return this.algorithmResult; }

    public void loadPlan(HttpSession session) {
        String id = (String) session.getAttribute("selected-name");
        Optional<Districting> planResponse = districtingRepository.findById(id, Districting.class);
        Districting plan = planResponse.get();
//        session.removeAttribute("selected");
        session.setAttribute("selected", plan);
    }

//    @Async
    public Status startJob(Constraints constraints, Age age, HttpSession session){
        this.constraints = constraints;
        if(status == Status.PROCESSING) {
//            session.setAttribute("selected", selected.getId());
            return Status.FAILED;
        }
        setStatus(Status.PROCESSING);
        this.session = session;
        timeStart = System.nanoTime();

        this.selected = (Districting) session.getAttribute("selected");
        HashMap<String, Integer> districtPopulations = new HashMap<>();

        HashMap<String, District> dhash = new HashMap<>();
        ArrayList<String> did = new ArrayList<>();

        System.out.println(selected.getId());
        System.out.println(selected.getDistricts().size());
        for (District d : this.selected.getDistricts()) {
            d.convertStringToGeometry();
            HashMap<String, Precinct> phash = new HashMap<>();
            ArrayList<String> pidTemp = new ArrayList<>();
            for (Precinct p : d.getBorderPrecincts()) {
                p.convertStringToGeometry();
                // removing neighbors that are in the same district so we will only have precincts in other districts in the set
                p.getNeighbors().removeIf(neighborP -> neighborP.getDistrict().equals(p.getDistrict()));
                for (CensusBlock cb : p.getCensusBlocks()) {
                    cb.convertStringToGeometry();
                    cb.setParentDistrict(p.getDistrict());
                }
                phash.put(p.getId(), p);
                pidTemp.add(p.getId());
            }

            dhash.put(d.getId(), d);
            did.add(d.getId());
            if (age == Age.TOTAL) {
                districtPopulations.put(d.getCd(), d.getPopulation().getTotal());
            } else {
                districtPopulations.put(d.getCd(), d.getVap().getTotal());
            }
        }
        // getting the population equality to start
        int totalPop;
        StateSummaryProjection ssp = (StateSummaryProjection) session.getAttribute("state");
        if (age == Age.TOTAL) {
            totalPop = ssp.getPopulation().getTotal();
        } else {
            totalPop = ssp.getVap().getTotal();
        }
        ideal = (totalPop / (double) selected.getDistricts().size());

        // no longer using polsby popper
//        double currentSS = 0;
//        if (age == Age.TOTAL) {
//            for (District d : selected.getDistricts()) {
//                currentSS += Math.pow((d.getPopulation().getTotal() / ideal) - 1.0, 2);
//            }
//        } else {
//            for (District d : selected.getDistricts()) {
//                currentSS += Math.pow((d.getVap().getTotal() / ideal) - 1.0, 2);
//            }
//        }
//        double popeq = Math.sqrt(currentSS);

        double highest = Double.POSITIVE_INFINITY;
        double lowest = Double.NEGATIVE_INFINITY;

        if (age == Age.TOTAL) {
            for (District d : selected.getDistricts()) {
                if (highest == Double.POSITIVE_INFINITY || highest < d.getPopulation().getTotal()) {
                    highest = d.getPopulation().getTotal();
                }
                if (lowest == Double.NEGATIVE_INFINITY || lowest > d.getPopulation().getTotal()) {
                    lowest = d.getPopulation().getTotal();
                }
            }
        } else {
            for (District d : selected.getDistricts()) {
                if (highest == Double.POSITIVE_INFINITY || highest < d.getVap().getTotal()) {
                    highest = d.getVap().getTotal();
                }
                if (lowest == Double.NEGATIVE_INFINITY || lowest > d.getVap().getTotal()) {
                    lowest = d.getVap().getTotal();
                }
            }
        }

        double popeq = (highest - lowest) / ideal;
        this.summary = new AlgorithmSummary(popeq, districtPopulations);
        session.setAttribute("summary", summary);
        this.algo = new Algorithm(dhash, did, selected, constraints, age);
        this.age = age;
        startAlgorithm();
        // create algorithm result with calculations
        timeEnd = System.nanoTime();
        createResult();

//        session.setAttribute("selected", selected.getId());
        return getStatus();
    }

    public AlgorithmResult createResult() {
        int changedPrecincts = 0;
        long runTime = timeEnd - timeStart;
        double timeSeconds = TimeUnit.SECONDS.convert(runTime, TimeUnit.NANOSECONDS);
        // getting compactness for each district
//        HashMap<String, Double> districtCompactness = new HashMap<>();
        for (District d : selected.getDistricts()) {
//            System.out.println("--------------------------------------------------------------------------");
//
//            WKTWriter writer = new WKTWriter();
//            String wktGeo = writer.write(d.getGeometry());
//            WKTReader reader = new WKTReader();
//            Geometry vividGeo = null;
//            try {
//                vividGeo = reader.read(wktGeo);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//            Point centroid = vividGeo.getCentroid();
//            System.out.println(centroid.toString());
//            String autoCode = "AUTO:42001," + centroid.getX() + "," + centroid.getY();
//            CoordinateReferenceSystem auto = null;
//            try {
//                auto = CRS.decode(autoCode);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            MathTransform transform = null;
//            try {
//                transform = CRS.findMathTransform(DefaultGeographicCRS.WGS84, auto);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Geometry geometry1 = null;
//            try {
//                geometry1 = JTS.transform(vividGeo, transform);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            Measure<Double, Area> area = javax.measure.Measure.valueOf(geometry1.getArea(), SI.SQUARE_METRE);
//
//            System.out.println(area);
//
//            Measure<Double, Length> perimeter = Measure.valueOf(geometry1.getLength(), SI.METRE);
//            System.out.println(perimeter);
//
//            double compactness = 4.0 * Math.PI * (area.getValue()/ Math.pow(perimeter.getValue(), 2));
//            System.out.println(compactness);
//            districtCompactness.put(d.getCd(), compactness);

            for (Precinct p : d.getBorderPrecincts()) {
                if (p.getHasChanged()) {
                    changedPrecincts++;
                    // TODO here is also where we get the geometries for changed precincts should we need them
                }
            }
        }
        this.algorithmResult = new AlgorithmResult(timeSeconds, iterations, changedPrecincts, this.summary.getCurrentPopEq());
        session.setAttribute("result", algorithmResult);
        return algorithmResult;
    }

    public void startAlgorithm() {
        boolean acceptable = false;
        while ((iterations < getInterationThreshold()) && (getStatus() == Status.PROCESSING || getStatus() == Status.PAUSE) && !acceptable) {
            while(getStatus() == Status.PAUSE){
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex){
                    Thread.currentThread().interrupt();
                }
            }
            algoRunnningLock = true; // locks each algo iteration, so there can be only one algo runnning at a time
            StateSummaryProjection ssp = (StateSummaryProjection) session.getAttribute("state");
            int totalPop;
            if (age == Age.TOTAL) {
                totalPop = ssp.getPopulation().getTotal();
            } else {
                totalPop = ssp.getVap().getTotal();
            }
            algo.runAlgorithm(totalPop);
            if (iterations % 17 == 0) {
                // update the summary

//                double ideal = (totalPop / (double) selected.getDistricts().size());
//                double currentSS = 0;
//                if (age == Age.TOTAL) {
//                    for (District d : selected.getDistricts()) {
//                        currentSS += Math.pow((d.getPopulation().getTotal() / ideal) - 1.0, 2);
//                    }
//                } else {
//                    for (District d : selected.getDistricts()) {
//                        currentSS += Math.pow((d.getVap().getTotal() / ideal) - 1.0, 2);
//                    }
//                }
//                double popeq = Math.sqrt(currentSS);

                // no longer using polsby popper
                double highest = Double.POSITIVE_INFINITY;
                double lowest = Double.NEGATIVE_INFINITY;

                if (age == Age.TOTAL) {
                    for (District d : selected.getDistricts()) {
                        if (highest == Double.POSITIVE_INFINITY || highest < d.getPopulation().getTotal()) {
                            highest = d.getPopulation().getTotal();
                        }
                        if (lowest == Double.NEGATIVE_INFINITY || lowest > d.getPopulation().getTotal()) {
                            lowest = d.getPopulation().getTotal();
                        }
                    }
                } else {
                    for (District d : selected.getDistricts()) {
                        if (highest == Double.POSITIVE_INFINITY || highest < d.getVap().getTotal()) {
                            highest = d.getVap().getTotal();
                        }
                        if (lowest == Double.NEGATIVE_INFINITY || lowest > d.getVap().getTotal()) {
                            lowest = d.getVap().getTotal();
                        }
                    }
                }

                double popeq = (highest - lowest) / ideal;

                if (popeq <= constraints.getPopulationEquality()) {
                    acceptable = true;
                }

                summary.setCurrentPopEq(popeq);
                summary.setIterations(iterations);
                HashMap<String, Integer> districtPops = new HashMap<>();
                for (District d : selected.getDistricts())
                if (age == Age.TOTAL) {
                    districtPops.put(d.getCd(), d.getPopulation().getTotal());
                } else {
                    districtPops.put(d.getCd(), d.getVap().getTotal());
                }
                summary.setDistrictPopulations(districtPops);
            }
            iterations++;
            algoRunnningLock = false; // unlocks algo iteration when the iteration is done
        }
        if(this.status == Status.PROCESSING){ // if algo is complete naturally(not by stop or pause), return completed status
            this.status = Status.COMPLETED;
        }
        timeEnd = System.nanoTime();
        createResult();
    }


    public Status pauseJob(){
        if(status != Status.PROCESSING){
            return Status.FAILED;
        }
        status = Status.PAUSE;
        while(this.algoRunnningLock){} // wait until the current algo running is done, then return pause status
        return getStatus();
    }


    public Status resumeJob(){
        if(status != Status.PAUSE){
            return Status.FAILED;
        }
        status = Status.PROCESSING;
        while(this.algoRunnningLock){} // wait until the current algo running is done, then start the algo running again.
        return getStatus();
    }

    public Status stopJob(){
        if(this.status != Status.PROCESSING && this.status != Status.PAUSE){  // if the algo is not processing or paused, return failed status
            return Status.FAILED; 
        } 
        status = Status.COMPLETED;
        while(this.algoRunnningLock){} // wait until the current algo running is done, then return stop status
        timeEnd = System.nanoTime();
        createResult();
//        session.setAttribute("selected", selected.getId());
        return getStatus();
    }
}


