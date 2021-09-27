import React, { Component, useState} from 'react';
import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";

import EnactedTable from './EnactedTable';
import GeneratedTable from './GeneratedTable';
import parties from '../data/mockState.js';
import demographics from '../data/mockPop.js';
import '../css/InfoMenu.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

const InfoMenu = (props) =>{

  const [parts, setParties] = useState(parties);
  const [demos, setDemos] = useState(demographics);

    return (
      <div className='InfoMenu'>
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
              <button class='nav-link' data-bs-toggle='tab' data-bs-target='#saved' role='tab' type='button' aria-selected='false'> Saved Plans </button>
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
                  (Total Votes: 2,618,519)
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
                  Total Population: 5,787,015
                  <table style={{ width: '100%' }}>
                      <tr className="item">
                        <th style={{ textAlign: 'left' }}>Demographic</th>
                        <th style={{ textAlign: 'right' }}>Population</th>
                        <th style={{ textAlign: 'right' }}>Population Percentage</th>
                      </tr>
                      {
                          demos.map(demo => (
                              <tr key={demo.id} align="start">
                                <td className="demo_name" style={{ textAlign: 'left' }}>{demo.name}</td>
                                <td className="demo_pop" style={{ textAlign: 'right' }}>{demo.population.toLocaleString('en-US')}</td>
                                <td className="demo_pper" style={{ textAlign: 'right' }}>{parseFloat(((demo.population / 5787015) * 100).toFixed(2))}</td>
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
            <div class='tab-pane fade' id='saved' role='tabpanel' aria-labelledby='saved-tab'>
              <div>
              </div>
            </div>
          </div>
        </div>
      </div>
  );
} 
export default InfoMenu