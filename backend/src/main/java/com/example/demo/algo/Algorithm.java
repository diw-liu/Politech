package com.example.demo.algo;
import com.example.demo.model.Districting;
import com.example.demo.model.District;
import com.example.demo.enums.PopulationType;
import com.example.demo.model.CensusBlock;
import com.example.demo.model.Measures;
import com.example.demo.model.Population;

import java.util.List;
import java.util.Random;
// import java.awt.Polygon;
import org.locationtech.jts.geom.*;

public class Algorithm {
   Districting redistricting;
   Constraints constraints;
   int algorithmCycles = 0;
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
   public void setRedistricting(Districting redistricting) {
        this.redistricting = redistricting;
    }  

   public boolean runAlgorithm() {
       District takenFrom = redistricting.selectRandomDistricts();
       CensusBlock toGive = getBorderCensusBlock(takenFrom);
       District givenTo = toGive.getDistrict();
       // if the move generate a better district, then we process the move
       if(calculateMove(takenFrom, givenTo, toGive)){
           generateNewBoundary(takenFrom, givenTo, toGive);
           this.algorithmCycles++;
           return true;
       }
       else{
           undoMove(takenFrom, givenTo, toGive);
           return false;
       }
   }

   public CensusBlock getBorderCensusBlock(District district){
       CensusBlock censusBlock = district.selectRandomCensusBlock();
       // keep searching the neighbor censusBlock that is not in the original district
       while(true){
           List<CensusBlock> censusBlockNeighbors = censusBlock.getNeighbors();
           for(CensusBlock neigborCB : censusBlockNeighbors){
               if(neigborCB.getDistrict() == district){
                   continue;
               }
               return neigborCB;
           }
           censusBlock = district.selectRandomCensusBlock();
       }
   }

   public boolean calculateMove(District takenFrom, District givenTo, CensusBlock toGive) {
       boolean isMoveBetter = false;
       List<CensusBlock> censusBlocksTakenFrom = takenFrom.getCensusBlocks();
       List<CensusBlock> censusBlocksGivenTo = givenTo.getCensusBlocks();
       censusBlocksTakenFrom.remove(toGive);
       censusBlocksGivenTo.add(toGive);
       double populationEquality = redistricting.getMeasures().getPopulationEquality();
//        double newPopulationEquality = caclculatePopulationEquality();
       double newPopulationEquality = 0; // @TODO made a change so we need to fix this later
       // if the new Population Equality is lower than the old one and the constraint set by user, then the move is going to be processed
       if(newPopulationEquality < populationEquality && newPopulationEquality < constraints.getPopulationEquality()){
           redistricting.getMeasures().setPopulationEquality(newPopulationEquality);
           isMoveBetter = true;
       }
       return isMoveBetter;
   }

//    public double caclculatePopulationEquality(){
//        Long sumSquares = 0L;
//        int redistrictingSize = redistricting.getDistricts().size();
//        Long idealPop = redistricting.getPopulations().get(PopulationType.TOTAL.ordinal()).getPopulation() / redistrictingSize;
//        for(int i = 0; i < redistrictingSize; i++){
//            Long districtTotal = redistricting.getDistricts().get(i).getPopulations().get(PopulationType.TOTAL.ordinal()).getPopulation();
//            sumSquares = (long) Math.pow((long)((districtTotal / idealPop) - 1), 2);
//        }
//        return Math.sqrt((double)sumSquares);
//    }

   public Districting undoMove(District takenFrom, District givenTo, CensusBlock toGive) {
       List<CensusBlock> censusBlocksTakenFrom = takenFrom.getCensusBlocks();
       List<CensusBlock> censusBlocksGivenTo = givenTo.getCensusBlocks();
       censusBlocksTakenFrom.add(toGive);
       censusBlocksGivenTo.remove(toGive);
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