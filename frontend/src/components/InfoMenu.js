import React, { Component } from 'react';

import EnactedTable from './EnactedTable';
import GeneratedTable from './GeneratedTable';
import '../css/InfoMenu.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

class InfoMenu extends Component {
    constructor(props){
      super(props);
      this.state = {            
          usState: 'Maryland'   
      };
    }

    render() {
        return (
          <div className='InfoMenu'>
            <div class='container'>
              <p> { this.state.usState } </p>
              <ul class='nav nav-pills' role='tablist'>
                <li class='nav-item' role='presentation'>
                  <button class='nav-link' data-bs-toggle='tab' data-bs-target='#enacted' role='tab' type='button' aria-selected='true'> Enacted </button>
                </li>
                <li class='nav-item' role='presentation'>
                  <button class='nav-link' data-bs-toggle='tab' data-bs-target='#generated' role='tab' type='button' aria-selected='false'> Generated </button>
                </li>
                <li class='nav-item' role='presentation'>
                  <button class='nav-link' data-bs-toggle='tab' data-bs-target='#saved' role='tab' type='button' aria-selected='false'> Saved Plan </button>
                </li>
              </ul>
              <div class='tab-content' id='pills-tabContent'>
                <div class='tab-pane fade show active' id='enacted' role='tabpanel' aria-labelledby='enacted-tab'>
                  <EnactedTable/>
                </div>
                <div class='tab-pane fade' id='generated' role='tabpanel' aria-labelledby='generated-tab'>
                  <GeneratedTable/>
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
} export default InfoMenu