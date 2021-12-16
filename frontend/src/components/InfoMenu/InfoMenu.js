import React, { useEffect, Component, useState} from 'react';
import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";

import BoxAndWhisker from './BoxAndWhisker'
import EnactedTable from './EnactedTable';
import GeneratedTable from './GeneratedTable';
import VotingData from './VotingData';
import DemoData  from './DemoData';
import '../../css/InfoMenu.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

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

const InfoMenu = (props) =>{

  console.log(props);
  // useEffect(() =>{
  //   fetch("/api/voting")
  //     .then(res => res.json())
  //     .then(function(data) {
  //         setParties(data);
  //     });
  // }, []);

  // useEffect(() =>{
  //   fetch("/api/population?name=" + props.stateName)
  //   .then(res => res.json())
  //   .then(function(data) {
  //       setTotalPopDem(data.populations.filter(population => population.type == "TOTAL")[0].population);
  //       setDemos(data.populations);
  //   });
  // }, []);

    return (
      <div className='info-menu'>
        <div class='container'>
          <h2> { NAMES[props.stateName] } </h2>
          <ul class='nav nav-pills' role='tablist'>
            <li class='nav-item' role='presentation'>
              <button class='nav-link' data-bs-toggle='tab' data-bs-target='#enacted' role='tab' type='button' aria-selected='true'> Enacted Plan </button>
            </li>
            <li class='nav-item' role='presentation'>   
              <button class='nav-link' data-bs-toggle='tab' data-bs-target='#generated' role='tab' type='button' aria-selected='false'> Generated Plans </button>
            </li>
            <li class='nav-item' role='presentation'>
              <button class='nav-link' data-bs-toggle='tab' data-bs-target='#plots' role='tab' type='button' aria-selected='false'> Box and Whiskers </button>
            </li>
          </ul>

          <div class='tab-content' id='pills-tabContent'>
            <div class='tab-pane fade show active' id='enacted' role='tabpanel' aria-labelledby='enacted-tab'>
              <Tabs>
                <Tab eventKey="State" title="State">
                  <VotingData enactedInfo={props.enactedInfo}/>
                  <hr/>
                  <DemoData enactedInfo={props.enactedInfo} popType={props.popType}/>
                </Tab>
                <Tab eventKey="Districts" title="Districts">
                  <EnactedTable enactedInfo={props.enactedInfo} popType={props.popType}/>
                </Tab>
              </Tabs>
            </div>
            <div class='tab-pane fade' id='generated' role='tabpanel' aria-labelledby='generated-tab'>
              <GeneratedTable stateName={props.stateName} districtings={props.districtings} getPlan={props.getPlan} enactedInfo={props.enactedInfo}
               setMeasure={props.setMeasure} setPlan={props.setPlan} popType={props.popType} plan={props.plan}/>
            </div>
            <div class='tab-pane fade' id='plots' role='tabpanel' aria-labelledby='plots-tab'>
              <BoxAndWhisker stateName={props.stateName} plan={props.plan} setPlan={props.setPlan} plots={props.plots} setPlots={props.setPlots}/>
            </div>
          </div>
        </div>
      </div>
  );
} 
export default InfoMenu