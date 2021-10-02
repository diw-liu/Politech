import React, { useRef, useEffect, useState } from 'react';
import Slider from 'react-input-slider';
// import { slide as Menu } from 'react-burger-menu';

import '../../css/LeftBar.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

const RedistrictMenu = (props) => {
    const [stateName, setStateName] = useState(50)
    const [popEq, setPopEq] = useState(0.7/2)
    const [racDev, setRacDev] = useState(50)
    const [majMin, setMajMin] = useState(4)
    const [effGap, setEffGap] = useState(0.5)
    const [compactness, setCompactness] = useState(0.5)

    const loading = () =>{
      props.setSaved(true);
      props.setGen(true)
      setTimeout(() => props.setGen(false), 5000)
    }
    return (
      <div className='redistrict-menu'>
          <h2>Redistrict </h2>
          <hr/>
          <div>
          <span>Population Equality (%): {Math.round(popEq * 100)/100}</span>
          <Slider style={{width:'100%'}} axis='x' x={popEq} xmax={0.7} xstep={0.001} onChange={ ({x}) => setPopEq(x) }/>
          </div>
          <div>
          {/* <span>Racial Deviation (%): {racDev}</span>
          <Slider style={{width:'100%'}} axis='x' x={racDev} onChange={ ({x}) => setRacDev(x) }/> */}
          </div>
          <div>
          <span style={{width:'100%'}}>Majority-minority districts: {majMin}</span>
          <Slider style={{width:'100%'}} axis='x' x={majMin} xmax={props.numDistricts} onChange={ ({x}) => setMajMin(x) }/>
          </div>
          <div>
          <span>Efficiency gap: {Math.round(effGap * 100)/100}</span>
          <Slider style={{width:'100%'}} axis='x' x={effGap} xmax={1} xstep={0.01} onChange={ ({x}) => setEffGap(x) }/>
          </div>
          <div>
          <span>Graph compactness: {Math.round(compactness * 100)/100}</span>
          <Slider style={{width:'100%'}} axis='x' x={compactness} xmax={1} xstep={0.01} onChange={ ({x}) => setCompactness(x) }/>
          </div>
          <button style={{marginTop:'16px'}} className='btn btn-primary' onClick={loading} > Redistrict </button>
      </div>
    );
} 
export default RedistrictMenu; 