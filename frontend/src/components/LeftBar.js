import React, { Component } from 'react';

import '../css/LeftBar.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';
import PlanStatistics from './PlanStatistics';

class LeftBar extends Component {
    constructor(props){
        super(props);
        this.state = {
          usState: 'Maryland'
        };
    }

    render() {
        return (
          <div className='LeftBar'>
            <div class='container'>
              <h2> {this.state.usState} </h2>
              <PlanStatistics/>
            </div>
          </div>
        );
    }
} export default LeftBar; 