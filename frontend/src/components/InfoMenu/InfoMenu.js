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

const InfoMenu = (props) =>{

  const [parts, setParties] = useState([]);
  const [demos, setDemos] = useState([]);
  const [totalPopDem, setTotalPopDem] = useState([]);

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
          <h1> { props.stateName } </h1>
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
          {/* <Tabs>
            <Tab eventKey="Enacted" title="Enacted Plan">
            </Tab>
            <Tab eventKey="Generated" title="Generated Plans">
            </Tab>
            <Tab eventKey="Saved" title="Saved Plans">
            </Tab>
          </Tabs> */}
          {/* <Tabs>
            <Tab eventKey="State" title="State">
              
            </Tab>
            <Tab eventKey="Districts" title="Districts">

            </Tab>
          </Tabs> */}
          <div class='tab-content' id='pills-tabContent'>
            <div class='tab-pane fade show active' id='enacted' role='tabpanel' aria-labelledby='enacted-tab'>
              <Tabs>
                <Tab eventKey="State" title="State">
                  <VotingData enactedInfo={props.enactedInfo}/>
                  <hr/>
                  <DemoData enactedInfo={props.enactedInfo} popType={props.popType}/>
                </Tab>
                <Tab eventKey="Districts" title="Districts">
                  <EnactedTable enactedInfo={props.enactedInfo}/>
                </Tab>
              </Tabs>
            </div>
            <div class='tab-pane fade' id='generated' role='tabpanel' aria-labelledby='generated-tab'>
              <GeneratedTable districtings={props.districtings} setPlan={props.setPlan}/>
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