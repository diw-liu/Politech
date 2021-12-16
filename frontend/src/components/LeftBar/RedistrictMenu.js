import React, { useRef, useEffect, useState } from 'react';
import Slider from 'react-input-slider';
import { redistrict } from '../Map/Preprocess'
// import { slide as Menu } from 'react-burger-menu';

import '../../css/LeftBar.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

const RedistrictMenu = (props) => {
  
  const handleLoading = () =>{
    props.loading()
  }

  return (
    <div className='redistrict-menu'>
        <h2> Redistrict </h2>
        <hr/>
        <div>
          <span>Population Equality Goal: {props.popEq.toFixed(2)}</span>
          <Slider style={{width:'100%'}} axis='x' x={props.popEq} xmax={0.1} xstep={0.001} onChange={ ({x}) => props.setPopEq(x) }/>
        </div>
        <div>
          <span style={{width:'100%'}}>Maximum Opportunity Districts: {props.oppoDist}</span>
          <Slider style={{width:'100%'}} axis='x' x={props.oppoDist} xmax={props.maxDists} xstep={1} onChange={ ({x}) => props.setOppoDist(x) }/>
        </div>
        <button style={{marginTop:'16px'}} className='btn btn-primary' onClick={handleLoading} > Redistrict </button>
    </div>
  );
} 
export default RedistrictMenu; 