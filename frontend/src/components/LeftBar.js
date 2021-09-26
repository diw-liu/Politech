import React, { useRef, useEffect, useState } from 'react';

import '../css/LeftBar.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';
import PlanStatistics from './PlanStatistics';

const LeftBar = (props) => {
    const [stateName, setStateName] = useState('')

    return (
      <div className='LeftBar'>
        <div class='container'>
          <h2> {props.stateName} </h2>
          <hr/>
          <PlanStatistics/>
        </div>
      </div>
    );
} 
export default LeftBar; 