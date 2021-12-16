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

    // console.log(props);

    // let map = {};

    //   const districtMap = (Id) => {
    //     if(Id in map ){
    //       return map[Id]
    //     }
    //     var temp = [Math.random()*256, Math.random()*256, Math.random()*256];
    //     map[Id] = temp;
    //     return temp
    //   }

    // const districtColor = new GeoJsonLayer({
    //   id: 'districtColor',
    //   data : props.enactedGeo,
    //   filled: true,
    //   stroked: false,
    //   extruded: false,
    //   getFillColor: d => districtMap(d.properties.cd),
    //   pointType: 'circle',
    // })

    // display.push(districtColor)
      
    // // Finetune the lines with function
    // for (const variable in props.layers) {
    //   if(props.flag[variable]){
    //     display.push(new GeoJsonLayer({
    //             id: variable,
    //             data : props.layers[variable],
    //             stroked: true,
    //             filled: false,
    //             extruded: false,
    //             pointType: 'circle',
    //             lineWidthScale: 20,
    //             lineWidthMinPixels: 2,
    //             getLineColor: [228,220,220],
    //             getLineWidth: 1,
    //           }));
    //     // console.log(variable);
    //     // console.log(props.layers[variable]);
    //   }

    // }
  
    // console.log(props);
      return (
            <div class='tab-content' id='pills-tabContent'>
              <div class='tab-pane fade show active' id='allPlans' role='tabpanel' aria-labelledby='allPlans-tab'>
                <Tabs>
                  <Tab eventKey="allPlans" title="All Plans">
                    <PlansTable props={props} dummyTesting={props.dummyTesting}/>
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