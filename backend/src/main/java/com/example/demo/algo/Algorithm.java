package com.example.demo.algo;
import com.example.demo.model.Districting;
import com.example.demo.model.District;
import com.example.demo.enums.PopulationType;
import com.example.demo.model.CensusBlock;
import com.example.demo.model.Measures;
import com.example.demo.model.Population;
import com.example.demo.model.Precinct;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.locationtech.jts.dissolve.LineDissolver;
import org.locationtech.jts.geom.*;

public class Algorithm {
    Districting redistricting;
    Constraints constraints;
    Precinct ptTakenFrom;
    Precinct ptGivenTo;
    int algorithmCycles;
    int status;

    public Algorithm(Districting redistricting, Constraints constraints){
        this.redistricting = redistricting;
        this.constraints = constraints;
        this.algorithmCycles = 0;
    }

    public boolean runAlgorithm() {
        District takenFrom = redistricting.selectRandomDistricts();
        CensusBlock toGive = getBorderCensusBlock(takenFrom);
        District givenTo = toGive.getParentDistrict();
        ptGivenTo = toGive.getParentPrecinct();
        // if the move generate a better district, then we process the move
        if(calculateMove(takenFrom, givenTo, toGive)){
            generateNewBoundary(takenFrom, givenTo, toGive);  // update the geometry for both districts
            updateBorderCencusBlock(toGive);  // update the list of border cencus block for both districts
            this.algorithmCycles++;
            return true;
        }
        else{
            return false;
        }
    }


    public CensusBlock getBorderCensusBlock(District district){
        ptTakenFrom = district.selectRandomBorderPrecinct();
        CensusBlock censusBlock = ptTakenFrom.selectRandomBorderCensusBlock();
        // keep searching the neighbor censusBlock that is not in the original district
        while(true){
            Set<CensusBlock> censusBlockNeighbors = censusBlock.getNeighbors();
            for(CensusBlock neigborCB : censusBlockNeighbors){
                if(neigborCB.getParentDistrict() == district){
                    continue;
                }
                return neigborCB;
            }
            ptTakenFrom = district.selectRandomBorderPrecinct();
            censusBlock = ptTakenFrom.selectRandomBorderCensusBlock();
        }
    }

    public boolean calculateMove(District takenFrom, District givenTo, CensusBlock toGive) {
        boolean isMoveBetter = false;
        // List<CensusBlock> censusBlocksTakenFrom = takenFrom.getCensusBlocks();
        // List<CensusBlock> censusBlocksGivenTo = givenTo.getCensusBlocks();
        // censusBlocksTakenFrom.remove(toGive);
        // censusBlocksGivenTo.add(toGive);
        double populationEquality = redistricting.getMeasures().getPopulationEquality();
        // double newPopulationEquality = caclculatePopulationEquality();
        double newPopulationEquality = 0; // @TODO made a change so we need to fix this later
        // if the new Population Equality is lower than the old one and the constraint set by user, then the move is going to be processed
        if(newPopulationEquality < populationEquality && newPopulationEquality < constraints.getPopulationEquality()){
            redistricting.getMeasures().setPopulationEquality(newPopulationEquality);
            isMoveBetter = true;
        }
        if(isMoveBetter == false){
            undoMove(ptTakenFrom, ptGivenTo, toGive);
        }
        return isMoveBetter;
    }

    // public double caclculatePopulationEquality(){
    //     Long sumSquares = 0L;
    //     int redistrictingSize = redistricting.getDistricts().size();
    //     Long idealPop = redistricting.getPopulations().get(PopulationType.TOTAL.ordinal()).getPopulation() / redistrictingSize;
    //     for(int i = 0; i < redistrictingSize; i++){
    //         Long districtTotal = redistricting.getDistricts().get(i).getPopulations().get(PopulationType.TOTAL.ordinal()).getPopulation();
    //         sumSquares = (long) Math.pow((long)((districtTotal / idealPop) - 1), 2);
    //     }
    //     return Math.sqrt((double)sumSquares);
    // }

    public Districting undoMove(Precinct takenFrom, Precinct givenTo, CensusBlock toGive) {
        Set<CensusBlock> censusBlocksTakenFrom = takenFrom.getCensusBlocks();
        Set<CensusBlock> censusBlocksGivenTo = givenTo.getCensusBlocks();
        censusBlocksTakenFrom.add(toGive);
        censusBlocksGivenTo.remove(toGive);
        return this.redistricting;
    }

    public void generateNewBoundary(District takenFrom, District givenTo, CensusBlock toGive) {

        Geometry takenFromGeometry = takenFrom.getGeometry();
        Geometry givenToGeometry = givenTo.getGeometry();
        Geometry toGiveGeometry = toGive.getGeometry();
        // calculate difference between the district taken from and the censusBlock
        Geometry newTakenFromGeometry = takenFromGeometry.difference(toGiveGeometry);
        newTakenFromGeometry = LineDissolver.dissolve(newTakenFromGeometry);
        // calculate union between the district given to and the censusBlock
        Geometry newGivenToGeometry = givenToGeometry.union(toGiveGeometry);
        newGivenToGeometry = LineDissolver.dissolve(newGivenToGeometry);
        // update the boundary for both districts
        takenFrom.setGeometry(newTakenFromGeometry);
        givenTo.setGeometry(newGivenToGeometry);
    }

    public void updateBorderCencusBlock(CensusBlock censusBlock) {
        Set<CensusBlock> censusBlockNeighbors = censusBlock.getNeighbors();
        for(CensusBlock neigborCB : censusBlockNeighbors){
            Set<CensusBlock> censusBlockNbNbs = neigborCB.getNeighbors();
            for(CensusBlock nbnbCB : censusBlockNbNbs){
                if(neigborCB.getParentDistrict() != nbnbCB.getParentDistrict()){
                    neigborCB.setBorder(true);
                    continue;
                }
            }
        }
    }

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
    public void setRedistricting(Districting redistricting) {
        this.redistricting = redistricting;
    }

}
