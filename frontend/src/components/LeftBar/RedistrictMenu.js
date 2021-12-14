import React, { useRef, useEffect, useState } from 'react';
import Slider from 'react-input-slider';
import { redistrict } from '../Map/Preprocess'
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
    const [showAlgoButton, setShowAlgoButton] = useState(false)

    const loading = () =>{
      props.setSaved(true);
      props.setGen(true)
      setShowAlgoButton(true)
      setTimeout(() => props.setGen(false), 5000)
      // fetch("/api/newDistricting",{
      //   method: 'GET',
      //   headers:{'Content-Type': 'application/x-www-form-urlencoded'}
      // })
      //   .then(res => res.json()) 
      //   .then(message => {
      //     // localStorage.setItem("All",JSON.stringify(message));
      //     var result = message.map(x => 
       
      //       JSON.parse(x).features[0]
      //       );
      //     console.log(props.state)
      //     console.log(result)
      //     // props.setState(result)
      //     props.setState(redistrict(result, 0))
      //   })
      fetch("/api/state?name=MD");
      fetch("/api/selectplan?id=24PL0");
      fetch("/job/start?goal=0.08&lower=3&higher=7&age=0");
    }

    const pause = () =>{
      fetch("/job/pause");
    }

    const resume = () =>{
      fetch("/job/resume");
    }

    const stop = () =>{
      fetch("/job/stop");
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
          <div style={showAlgoButton ? {} : { display: 'none' }}>
            <button style={{marginTop:'16px'}} className='btn btn-primary' onClick={pause} > Pause </button>
            <button style={{marginTop:'16px'}} className='btn btn-primary' onClick={resume} > Resume </button>
            <button style={{marginTop:'16px'}} className='btn btn-primary' onClick={stop} > Stop </button>
          </div>

      </div>
    );
} 
export default RedistrictMenu; 