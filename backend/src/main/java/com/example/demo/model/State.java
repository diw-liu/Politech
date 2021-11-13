package com.example.demo.model;
import com.example.demo.model.data.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class State {
    String name;
    Districting enactedDistricting;
    Districting currentDistricting;
    Districting redistrictedDistricting;
    List<Districting> districtings;
    StateOutline stateOutline;
    Demographic demographic;
    List<Election> elections;
    BoxAndWhisker[] boxAndWhiskerBatch;
}
