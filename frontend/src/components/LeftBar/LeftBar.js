import React, { useRef, useEffect, useState } from 'react';
// import { slide as Menu } from "react-burger-menu";

import '../../css/LeftBar.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

import RedistrictMenu from './RedistrictMenu';
import PlanStatistics from './PlanStatistics';
// import LayerSelector from '../Map/LayerSelector';

const LeftBar = (props) => {
    const [numDistricts, setNumDistricts] = useState('7')
    return (
      <div className='left-bar'>
        {/* <Menu>
          in case we want to make the left bar collapseable https://github.com/negomi/react-burger-menu#styling
        </Menu> */}
        <div class='container'>
          <h2> {props.stateName} {(props.plan == 0) ? 'Enacted' : props.plan }</h2>
          <hr/>
          <PlanStatistics props={props}/>
          <hr/>
          { <RedistrictMenu numDistricts={numDistricts} setGen={props.setGen} 
                                  state={props.state} setState={props.setState} saved={props.saved} setSaved={props.setSaved}/>}
        </div>
        <div class='container'> 
        
        </div>
      </div>
    );
} 
export default LeftBar; 