import React, { useRef, useEffect, useState } from 'react';
// import { slide as Menu } from "react-burger-menu";

import '../../css/LeftBar.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

import RedistrictMenu from './RedistrictMenu';
import PlanStatistics from './PlanStatistics';
import PopulationSelector from './PopulationSelector';
// import LayerSelector from '../Map/LayerSelector';

const LeftBar = (props) => {

    return (
      <div className='left-bar'>
        {/* <Menu>
          in case we want to make the left bar collapseable https://github.com/negomi/react-burger-menu#styling
        </Menu> */
        }
        <div class='container'>
          <h2> { props.stateName } {props.plan}</h2>
          <hr/>
          <PlanStatistics plan={props.plan} measure={props.measure}/>
          <hr/>
          <PopulationSelector popType={props.popType} setPopType={props.setPopType}/>
          <hr/>
          <RedistrictMenu popType={props.popType} maxDists={props.enactedInfo.districts.length} measure={props.measure}
                popEq={props.popEq} setPopEq={props.setPopEq} oppoDist={props.oppoDist} setOppoDist={props.setOppoDist}
                loading={props.loading}/>
        </div>
      </div>
    );
} 
export default LeftBar; 