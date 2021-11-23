import React, { useEffect, Component, useState} from 'react';
import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";

import BoxAndWhisker from './BoxAndWhisker'
import EnactedTable from './EnactedTable';
import GeneratedTable from './GeneratedTable';
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

  useEffect(() =>{
    fetch("/api/voting")
      .then(res => res.json())
      .then(function(data) {
          setParties(data);
      });
  }, []);

  useEffect(() =>{
    fetch("/api/population?name=" + props.stateName)
    .then(res => res.json())
    .then(function(data) {
        setTotalPopDem(data.populations.filter(population => population.type == "TOTAL")[0].population);
        setDemos(data.populations);
    });
  }, []);

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
                  <div>
                  <h5>Voting Data</h5>
                  <table style={{ width: '100%' }}>
                      <tr className="item">
                        <th style={{ textAlign: 'left' }}>Party</th>
                        <th style={{ textAlign: 'right' }}>Districts</th>
                        <th style={{ textAlign: 'right' }}>Votes</th>
                        <th style={{ textAlign: 'right' }}>District Percentage</th>
                        <th style={{ textAlign: 'right' }}>Vote Percentage</th>
                      </tr>
                      {
                          parts.map(party => (
                              <tr key={party.id} align="start">
                                <td className="party_name" style={{ textAlign: 'left' }}>{party.id}</td>
                                <td className="party_district" style={{ textAlign: 'right' }}>{party.districts}</td>
                                <td className="party_vote" style={{ textAlign: 'right' }}>{party.votes.toLocaleString('en-US')}</td>
                                <td className="party_dper" style={{ textAlign: 'right' }}>{party.dper.toLocaleString('en-US')}</td>
                                <td className="party_vper" style={{ textAlign: 'right' }}>{party.vper.toLocaleString('en-US')}</td>
                              </tr>
                          ))
                      }
                  </table>
                  </div>
                  <br></br>
                  <div>
                  <h5>Demographic Data </h5>
                  (Total Population: {totalPopDem.toLocaleString('en-US')})
                  <table style={{ width: '100%' }}>
                      <tr className="item">
                        <th style={{ textAlign: 'left' }}>Demographic</th>
                        <th style={{ textAlign: 'right' }}>Population</th>
                        <th style={{ textAlign: 'right' }}>Population Percentage</th>
                      </tr>
                      {
                          demos.map(demo => (
                              <tr key={demo.id} align="start">
                                <td className="demo_name" style={{ textAlign: 'left' }}>{demographicMapping[demo.type]}</td>
                                <td className="demo_pop" style={{ textAlign: 'right' }}>{demo.population.toLocaleString('en-US')}</td>
                                <td className="demo_pper" style={{ textAlign: 'right' }}>{parseFloat(((demo.population / totalPopDem) * 100).toFixed(2))}</td>
                              </tr>
                          ))
                      }
                  </table>
                  </div>
                </Tab>
                <Tab eventKey="Districts" title="Districts">
                  <EnactedTable/>
                </Tab>
              </Tabs>
            </div>
            <div class='tab-pane fade' id='generated' role='tabpanel' aria-labelledby='generated-tab'>
              <GeneratedTable setPlan={props.setPlan}/>
            </div>
            <div class='tab-pane fade' id='plots' role='tabpanel' aria-labelledby='plots-tab'>
              <div><BoxAndWhisker stateName={props.stateName} plan={props.plan} setPlan={props.setPlan}/></div>
            </div>
            
          </div>
        </div>
      </div>
  );
} 
export default InfoMenu