import React, { useRef, useEffect, useState } from 'react';
import { slide as Menu } from "react-burger-menu";

import '../css/LeftBar.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';
import GenerateMenu from './GenerateMenu';
import PlanStatistics from './PlanStatistics';

const LeftBar = (props) => {
    const [stateName, setStateName] = useState('')
    const [numDistricts, setNumDistricts] = useState(7)
  
    return (
      <div className='left-bar'>
        {/* <Menu>
          in case we want to make the left bar collapseable https://github.com/negomi/react-burger-menu#styling
        </Menu> */}
        <div class='container'>
          <h2> {props.stateName} </h2>
          <hr/>
          <PlanStatistics/>
          <GenerateMenu generate={props.generate} numDistricts={numDistricts}/>
        </div>
      </div>
    );
} 
export default LeftBar; 