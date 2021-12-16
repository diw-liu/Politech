import React, { useEffect, useState, useMemo } from 'react';
import ReactTooltip from 'react-tooltip';
import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";
import ReactTable from "react-table";
import { useTable, useSortBy } from 'react-table'
import XMLParser from 'react-xml-parser';
import SVG from 'react-inlinesvg';
import Districts from '../../data/mock2.js';
import '../../css/InfoMenu.css';
import PlansTable from './PlansTable.js';
import PlanDistricts from './PlanDistricts.js';

const demographicMapping = {
    "TOTAL": "Total",
    "HISPANIC": "Hispanic",
    "WHITE": "White Non-Hispanic",
    "BLACK": "African American",
    "ASIAN": "Asian",
    "RACE_OTHER": "Other"
  }
  
  const NAMES = {
    "MD" : "Maryland",
    "MI" : "Michigan",
    "PA" : "Pennsylvania"
  }
  
  const GeneratedTable = (props) =>{
  
    console.log(props);
      return (
            <div class='tab-content' id='pills-tabContent'>
              <div class='tab-pane fade show active' id='allPlans' role='tabpanel' aria-labelledby='allPlans-tab'>
                <Tabs>
                  <Tab eventKey="allPlans" title="All Plans">
                    <PlansTable props={props}/>
                  </Tab>
                  <Tab eventKey="planDistricts" title="Districts">
                    <PlanDistricts stateName={props.stateName} districtings={props.districtings} getPlan={props.getPlan} enactedInfo={props.enactedInfo}
               setMeasure={props.setMeasure} setPlan={props.setPlan} popType={props.popType} plan={props.plan}/>
                  </Tab>
                </Tabs>
              </div>
            </div>
    );
}
 
export default GeneratedTable;