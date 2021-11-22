package com.example.demo.algo;
import com.example.demo.model.Districting;
import com.example.demo.model.District;
import com.example.demo.model.CensusBlock;
import com.example.demo.model.Measures;

import java.util.List;
import java.util.Random;
// import java.awt.Polygon;
import org.locationtech.jts.geom.*;

public class Algorithm {
    Districting redistricting;
    int algorithmCycles;
    int status; 

    public Districting getRedistricting() {
        return this.redistricting;
    }
    
    public int getAlgorithmCycles() {
        return this.algorithmCycles;
    }
    
    public int getStatus() {
        return this.status;
    }

    public void getRedistricting(Districting redistricting) {
        this.redistricting = redistricting;
    }
    public void setAlgorithmCycles(int algorithmCycles) {
        this.algorithmCycles = algorithmCycles;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public void runAlgorithm() {
        District takenFrom = selectRandomDistricts();
        List<District> takenFromNeighbors = takenFrom.getNeighbors();
        int neighborSize = takenFromNeighbors.size();
        Random rand = new Random();
        District givenTo = takenFromNeighbors.get(rand.nextInt(neighborSize));
        List<CensusBlock> takenFromBorderBlocks = takenFrom.getBorderBlocks();
        int borderBlockSize = takenFromBorderBlocks.size();
        CensusBlock toGive = takenFromBorderBlocks.get(rand.nextInt(borderBlockSize));
        // if the move generate a better district, then we process the move
        if(calculateMove(takenFrom, givenTo, toGive)){
            makeMove(takenFrom, givenTo, toGive);
            generateNewBoundary(takenFrom, givenTo, toGive);
        }
    }

    public boolean calculateMove(District takenFrom, District givenTo, CensusBlock toGive) {
        boolean isMoveBetter = false;
        List<CensusBlock> censusBlocksTakenFrom = takenFrom.getCensusBlocks();
        List<CensusBlock> censusBlocksGivenTo = givenTo.getCensusBlocks();
        censusBlocksTakenFrom.remove(toGive);
        censusBlocksGivenTo.add(toGive);

        // int score = calculate_score(takenFrom, givenTo);
        // if(score > threshold){
        //     isMoveBetter = true;
        // }
            
        censusBlocksTakenFrom.add(toGive); // undo remove and add
        censusBlocksGivenTo.remove(toGive);
        return isMoveBetter;
    }

    public District selectRandomDistricts() {
        List<District> districts = this.redistricting.getDistricts();
        int size = districts.size();
        Random rand = new Random();
        return districts.get(rand.nextInt(size));
    }

    public Districting makeMove(District takenFrom, District givenTo, CensusBlock toGive) {
        List<CensusBlock> censusBlocksTakenFrom = takenFrom.getCensusBlocks();
        List<CensusBlock> censusBlocksGivenTo = givenTo.getCensusBlocks();
        censusBlocksTakenFrom.remove(toGive);
        censusBlocksGivenTo.add(toGive);
        return this.redistricting;

    }
    public void generateNewBoundary(District takenFrom, District givenTo, CensusBlock toGive) {
        Polygon takenFromGeometry = takenFrom.getGeometry();
        Polygon givenToGeometry = givenTo.getGeometry();
        Polygon toGiveGeometry = toGive.getGeometry();
        // calculate difference between the district taken from and the censusBlock
        Geometry newTakenFromGeometry = takenFromGeometry.difference(toGiveGeometry);
        // calculate union between the district given to and the censusBlock
        Geometry newGivenToGeometry = givenToGeometry.union(toGiveGeometry);
        // update the boundary for both districts
        takenFrom.setGeometry((Polygon) newTakenFromGeometry);
        givenTo.setGeometry((Polygon) newGivenToGeometry);
    }


}