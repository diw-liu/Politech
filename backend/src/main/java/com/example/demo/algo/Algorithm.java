package com.example.demo.algo;
import com.example.demo.enums.Age;
import com.example.demo.model.Districting;
import com.example.demo.model.District;
import com.example.demo.model.CensusBlock;
import com.example.demo.model.Measures;
import com.example.demo.model.Population;
import com.example.demo.model.Precinct;

import java.lang.reflect.Array;
import java.util.*;

import org.locationtech.jts.dissolve.LineDissolver;
import org.locationtech.jts.geom.*;

public class Algorithm {
    private HashMap<String, District> districts;
//    private HashMap<String, HashMap<String, Precinct>> dToP;
    private ArrayList<String> dids;
//    private HashMap<String, ArrayList<String>> pids;
    private Age age;
    private int badMoves;

    Districting redistricting;
    Constraints constraints;

    public Algorithm(HashMap<String, District> districts, /*HashMap<String, HashMap<String, Precinct>> dToP,*/ ArrayList<String> dids, /*HashMap<String, ArrayList<String>> pids,*/ Districting redistricting, Constraints constraints, Age age) {
        this.districts = districts;
//        this.dToP = dToP;
        this.dids = dids;
//        this.pids = pids;
        this.redistricting = redistricting;
        this.constraints = constraints;
        this.age = age;
        badMoves = 0;
    }

    public boolean runAlgorithm(int totalPop) {
        return move(totalPop);
    }

    public Precinct findPrecinctToGive(District toGiveD, Random rand) {
        // from the random district, list out all the precincts that have neighbors in different districts
        ArrayList<Precinct> validPrecincts = new ArrayList<>();
        for (Precinct candidatePrecinct : toGiveD.getBorderPrecincts()) {
            if (candidatePrecinct.getNeighbors() == null || candidatePrecinct.getNeighbors().size() == 0) { continue; }
            for (Precinct candidateNeighbor : candidatePrecinct.getNeighbors()) {
                if (toGiveD.getNeighbors().contains(candidateNeighbor.getDistrict())) {
                    validPrecincts.add(candidatePrecinct);
                    break;
                }
            }
        }
        // select the precinct randomly from the valid
        return validPrecincts.get(rand.nextInt(validPrecincts.size()));
    }

    public ArrayList<CensusBlock> findValidBlocks(Precinct toGiveP) {
        // from the random precinct, list out all the valid census blocks
        ArrayList<CensusBlock> validBlocks = new ArrayList<>();
        for (CensusBlock candidateCb : toGiveP.getBorderBlocks()) {
            for (CensusBlock candidateNeighbor : candidateCb.getNeighbors()) {
                if (toGiveP.getNeighbors().contains(candidateNeighbor.getPrecinct())) {
                    validBlocks.add(candidateCb);
                    break;
                }
            }
        }
        return validBlocks;
    }

    public Precinct findPrecinctToTake(CensusBlock toGiveC, Precinct toGiveP, Random rand) {
        // find a neighbor of cb to give that has a different precinct and choose that precinct to move to
        ArrayList<Precinct> validPrecincts = new ArrayList<>();
        for (CensusBlock neighbor : toGiveC.getNeighbors()) {
            if (!neighbor.getPrecinct().equals(toGiveP) && !validPrecincts.contains(neighbor.getPrecinct())) {
                validPrecincts.add(neighbor.getPrecinct());
            }
        }
        // select the precinct randomly from the valid
        return validPrecincts.get(rand.nextInt(validPrecincts.size()));
    }

    public boolean move(double totalPop) {
        // select a random district
        Random rand = new Random();
        int numD = districts.size();
        String did = dids.get(rand.nextInt(numD));
        District toGiveD = districts.get(did);

        Precinct toGiveP = findPrecinctToGive(toGiveD, rand);

        ArrayList<CensusBlock> validBlocks = findValidBlocks(toGiveP);
        while (validBlocks.size() == 0) {
            toGiveP = findPrecinctToGive(toGiveD, rand);
            validBlocks = findValidBlocks(toGiveP);
        }
        // select the precinct randomly from the valid
        CensusBlock toGiveC = validBlocks.get(rand.nextInt(validBlocks.size()));

        Precinct toTakeP = findPrecinctToTake(toGiveC, toGiveP, rand);
        District toTakeD = toTakeP.getDistrict();

        double ideal = (totalPop / (double) districts.size());
        // should we try this one?
//        double currentSS = 0;
//        if (age == Age.TOTAL) {
//            for (District d : districts.values()) {
//                currentSS += Math.pow((d.getPopulation().getTotal() / ideal) - 1.0, 2);
//            }
//        } else {
//            for (District d : districts.values()) {
//                currentSS += Math.pow((d.getVap().getTotal() / ideal) - 1.0, 2);
//            }
//        }
//        currentSS = Math.sqrt(currentSS);

        // -----------------THIS WAS WHAT WAS BEING USED TO CALCULATE------------------------------------
//        double lowest = Double.NEGATIVE_INFINITY;
//        double highest = Double.POSITIVE_INFINITY;
//        double currentPE;
//        if (age == Age.TOTAL) {
//            for (District d : districts.values()) {
//                if (lowest == Double.NEGATIVE_INFINITY || lowest > d.getPopulation().getTotal()) {
//                    lowest = d.getPopulation().getTotal();
//                }
//
//                if (highest == Double.POSITIVE_INFINITY || highest < d.getPopulation().getTotal()) {
//                    highest = d.getPopulation().getTotal();
//                }
//            }
//        } else {
//            for (District d : districts.values()) {
//                if (lowest == Double.NEGATIVE_INFINITY || lowest > d.getVap().getTotal()) {
//                    lowest = d.getVap().getTotal();
//                }
//
//                if (highest == Double.POSITIVE_INFINITY || highest < d.getVap().getTotal()) {
//                    highest = d.getVap().getTotal();
//                }
//            }
//        }
//        currentPE = (highest - lowest) / ideal;
        // ----------------------------------------------------------------------------------------------

        boolean improved = false;
        if (age == Age.TOTAL) {
            improved = toGiveD.getPopulation().getTotal() > toTakeD.getPopulation().getTotal();
        } else {
            improved = toGiveD.getVap().getTotal() > toTakeD.getPopulation().getTotal();
        }

        // make the move
        toGiveC.setParentDistrict(toTakeD);
        toGiveC.setPrecinct(toTakeP);

        toGiveD.getPopulation().subtract(toGiveC.getPopulation());
        toGiveD.getVap().subtract(toGiveC.getPopulation());
        toGiveD.getElection().subtract(toGiveC.getElection());

        toGiveP.getBorderBlocks().remove(toGiveC);
        toGiveP.getCensusBlocks().remove(toGiveC);

        toTakeP.getBorderBlocks().add(toGiveC);
        toTakeP.getCensusBlocks().add(toGiveC);

        toTakeD.getPopulation().add(toGiveC.getPopulation());
        toTakeD.getVap().add(toGiveC.getPopulation());
        toTakeD.getElection().add(toGiveC.getElection());

        // calculate new sum squares
//        double newSS = 0;
//        if (age == Age.TOTAL) {
//            for (District d : districts.values()) {
//                newSS += Math.pow((d.getPopulation().getTotal() / ideal) - 1.0, 2);
//            }
//        } else {
//            for (District d : districts.values()) {
//                newSS += Math.pow((d.getVap().getTotal() / ideal) - 1.0, 2);
//            }
//        }
//        newSS = Math.sqrt(newSS);

        // --------------------------THIS WAS WHAT WAS BEING USED TO CALCULATE -------------------------------
//        double newPE;
//        if (age == Age.TOTAL) {
//            for (District d : districts.values()) {
//                if (lowest == Double.NEGATIVE_INFINITY || lowest > d.getPopulation().getTotal()) {
//                    lowest = d.getPopulation().getTotal();
//                }
//                if (highest == Double.POSITIVE_INFINITY || highest < d.getPopulation().getTotal()) {
//                    highest = d.getPopulation().getTotal();
//                }
//            }
//        } else {
//            for (District d : districts.values()) {
//                if (lowest == Double.NEGATIVE_INFINITY || lowest > d.getVap().getTotal()) {
//                    lowest = d.getVap().getTotal();
//                }
//                if (highest == Double.POSITIVE_INFINITY || highest < d.getVap().getTotal()) {
//                    highest = d.getVap().getTotal();
//                }
//            }
//        }
//        newPE = (highest - lowest) / ideal;
        // --------------------------------------------------------------------------------------------------------

        // if improved, keep move
        /*if (newSS <= currentSS)*/ /*if (newPE <= currentPE) */


        if (improved) {
            // generate new geometry
            toGiveD.setGeometry(LineDissolver.dissolve(toGiveD.getGeometry().difference(toGiveC.getGeometry())));
            toTakeD.setGeometry(LineDissolver.dissolve(toTakeD.getGeometry().union(toGiveC.getGeometry())));

//            toGiveD.setGeometry(toGiveD.getGeometry().buffer(0));
//            toTakeD.setGeometry(toTakeD.getGeometry().buffer(0));
            // adjust new border block creation

            for (CensusBlock newBorder : toGiveC.getNeighbors()) {
                newBorder.getPrecinct().getBorderBlocks().add(newBorder);

                // if the block is surrounded by blocks of the same precinct, it is not a border block anymore
                for (CensusBlock borderNeighbor : newBorder.getNeighbors()) {
                    // check if they contain a neighbor with a different precinct
                    int counter = 0;
                    for (CensusBlock neighborNeighbors : borderNeighbor.getNeighbors()) {
                        if (neighborNeighbors.getPrecinct().equals(borderNeighbor.getPrecinct())) {
                            counter++;
                        }
                    }
                    if (counter == borderNeighbor.getNeighbors().size()) {
                        // has no neighbors that is in a different precinct -> is not a border block
                        borderNeighbor.getPrecinct().getBorderBlocks().remove(borderNeighbor);
                    }
                }
            }
            toGiveP.setHasChanged(true);
            toTakeP.setHasChanged(true);
            badMoves = 0; // reset badmoves counter
            return true;
        }
        // if not improved, 90% to keep move
        if ((badMoves >= 5) || Math.random() < 0.9) {
            // undo move
            toGiveC.setParentDistrict(toGiveD);
            toGiveC.setPrecinct(toGiveP);

            toGiveD.getPopulation().add(toGiveC.getPopulation());
            toGiveD.getVap().add(toGiveC.getPopulation());

            toGiveP.getBorderBlocks().add(toGiveC);
            toGiveP.getCensusBlocks().add(toGiveC);

            toTakeP.getBorderBlocks().remove(toGiveC);
            toTakeP.getCensusBlocks().remove(toGiveC);

            toTakeD.getPopulation().subtract(toGiveC.getPopulation());
            toTakeD.getVap().subtract(toGiveC.getPopulation());

            badMoves++;
            return false;
        }
        // bad move but do not undo

        toGiveD.setGeometry(LineDissolver.dissolve(toGiveD.getGeometry().difference(toGiveC.getGeometry())));
        toTakeD.setGeometry(LineDissolver.dissolve(toTakeD.getGeometry().union(toGiveC.getGeometry())));

//        toGiveD.setGeometry(toGiveD.getGeometry().buffer(0));
//        toTakeD.setGeometry(toTakeD.getGeometry().buffer(0));
        // adjust new border block creation

        for (CensusBlock newBorder : toGiveC.getNeighbors()) {
            if (!newBorder.getPrecinct().getBorderBlocks().contains(newBorder)) {
                newBorder.getPrecinct().getBorderBlocks().add(newBorder);
            }

            // if the block is surrounded by blocks of the same precinct, it is not a border block anymore
            for (CensusBlock borderNeighbor : newBorder.getNeighbors()) {
                // check if they contain a neighbor with a different precinct
                int counter = 0;
                for (CensusBlock neighborNeighbors : borderNeighbor.getNeighbors()) {
                    if (neighborNeighbors.getPrecinct().equals(borderNeighbor.getPrecinct())) {
                        counter++;
                    }
                }
                if (counter == borderNeighbor.getNeighbors().size()) {
                    // has no neighbors that is in a different precinct -> is not a border block
                    borderNeighbor.getPrecinct().getBorderBlocks().remove(borderNeighbor);
                }
            }
        }
        toGiveP.setHasChanged(true);
        toTakeP.setHasChanged(true);
        badMoves++;
        return false;
    }

}
