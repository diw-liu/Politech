package com.example.demo.handlers;

import com.example.demo.enums.Status;
import com.example.demo.algo.*;
import com.example.demo.model.*;

import com.example.demo.projections.algorithm.DistrictNeighborsProjection;
import com.example.demo.projections.algorithm.PrecinctNeighborsProjection;
import com.example.demo.repositories.DistrictRepository;
import com.example.demo.repositories.DistrictingRepository;
import com.example.demo.repositories.PrecinctRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class JobService {
    @Autowired
    DistrictingRepository districtingRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    PrecinctRepository precinctRepository;

    Status status;

    public JobService(){
        this.status = Status.IDLE;
    }

    public void loadPlan(HttpSession session) {
        String id = (String) session.getAttribute("selected");
        Optional<Districting> planResponse = districtingRepository.findById(id, Districting.class);
        Districting plan = planResponse.get();
        session.removeAttribute("selected");
        session.setAttribute("selected", plan);
    }

    public Status startJob(Constraints constraints, HttpSession session){
        if(status != Status.IDLE) {
            return Status.FAILED;
        }
        // converting geoString to geo for every district, precinct, cb
        Districting selected = (Districting) session.getAttribute("selected");
        for (District d : selected.getDistricts()) {
            System.out.println("District: " + d.getId());
            System.out.println("---------------------------------------------");
            d.convertStringToGeometry();
            for (Precinct p : d.getBorderPrecincts()) {
                System.out.print(p.getId() + ", ");
                p.convertStringToGeometry();
                System.out.print(p.getCensusBlocks().size() + "; ");
                for (CensusBlock cb : p.getCensusBlocks()) {
                    cb.convertStringToGeometry();
                }
                for (CensusBlock cb : p.getBorderBlocks()) {
                    if (cb.getGeometry() == null) {
                        System.out.println("PROBLEMPROBLEMPROBLEMPROBLEMPROBLEMPROBLEMPROBLEMPROBLEMPROBLEMPROBLEMPROBLEM");
                    }
                }
            }
            System.out.println("---------------------------------------------");
        }
//
//
//        @SuppressWarnings("unchecked")
//        HashMap<String, HashMap<String, Precinct>> dToP = (HashMap<String, HashMap<String, Precinct>>)
//                session.getAttribute("districtToPrecincts");
//
//        @SuppressWarnings("unchecked")
//        HashMap<String, District> dhash = (HashMap<String, District>) session.getAttribute("selectedDistricts");
//        // getting district neighbors
//        for (District d : dhash.values()) {
//            Set<District> dneighbors = new HashSet<>();
//            Optional<DistrictNeighborsProjection> dnpResponse = districtRepository.findById(d.getId(), DistrictNeighborsProjection.class);
//            DistrictNeighborsProjection dnp = dnpResponse.get();
//            Iterator<DistrictNeighborsProjection.DistrictId> dnpi = dnp.getNeighbors().iterator();
//            while(dnpi.hasNext()) {
//                dneighbors.add(dhash.get(dnpi.next().getId()));
//            }
//            d.setNeighbors(dneighbors);
//        }
//        session.removeAttribute("selectedDistricts");
//        session.setAttribute("selectedDistricts", dhash);
//
//        @SuppressWarnings("unchecked")
//        HashMap<String, Precinct> phash = (HashMap<String, Precinct>) session.getAttribute("selectedPrecincts");
//        // getting precinct neighbors
//        for (Precinct p : phash.values()) {
//            Set<Precinct> pneighbors = new HashSet<>();
//            Optional<PrecinctNeighborsProjection> pnpResponse = precinctRepository.findById(p.getId(), PrecinctNeighborsProjection.class);
//            PrecinctNeighborsProjection pnp = pnpResponse.get();
//            Iterator<PrecinctNeighborsProjection.PrecinctId> pnpi = pnp.getNeighbors().iterator();
//            while(pnpi.hasNext()) {
//                pneighbors.add(phash.get(pnpi.next().getId()));
//            }
//            p.setNeighbors(pneighbors);
//        }
//        session.removeAttribute("selectedPrecincts");
//        session.setAttribute("selectedPrecincts", phash);
//
//        System.out.println(dhash.get("24PL0D1").getNeighbors());
//        System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
//        System.out.println(dToP.get("24PL0D1").get("24P1842"));
//        System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
//        System.out.println(phash.get("24P1842"));
//        // instantiate an algorithm class
//        // call on that to start the algorithm
        return null;
    }

}


