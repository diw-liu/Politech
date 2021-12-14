import React, { useEffect, useState } from 'react';
import InfoMenu from './InfoMenu/InfoMenu';
import LeftBar from './LeftBar/LeftBar';
import Map from './Map/Map';
import StateSelector from './Map/StateSelector';
import LayerSelector from './Map/LayerSelector';

import { showState } from './Map/Preprocess'
import { INITIAL_VIEW_STATE, getView } from './Map/ViewState'
import '../css/StateSelector.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

export const NAMES = ['Maryland', 'Michigan', 'Pennsylvania']

const HomeScreen = (props) =>{

  const [showInfo, setShowInfo] = useState(false)
  const [state, setState] = useState([])
  const [flag, setFlag] = useState([true, true])
  const [stateName, setStateName] = useState('')
  const [view, setView] = useState(INITIAL_VIEW_STATE)
  const [plan, setPlan] = useState(0)
  const [gen, setGen] = useState(false);
  const [saved, setSaved] = useState(false);
  const [all, setAll] = useState([]);
  
  console.log(state)
  
  useEffect(() =>{
    if (localStorage.getItem("All") == null){
      fetch("/api/all",{
        method: 'GET',
        headers:{'Content-Type': 'application/x-www-form-urlencoded'}
      })
        .then(res => res.json()) 
        .then(message => {
          localStorage.setItem("All",JSON.stringify(message));
          var result = message.map(x => JSON.parse(x));
          setAll(result)
        })
    }else{
      var result = JSON.parse(localStorage.getItem("All")).map(x => JSON.parse(x));
      setAll(result)
    }
  },[])

  const showClick = async ( name ) =>{
    setShowInfo(true) 
    //showState(name)
    setState(await showState(name))
    setStateName(NAMES[name])
    setView(getView(name))
  }


  return (
    <div >
      <StateSelector setShowInfo={setShowInfo} showClick={showClick}
        setView={setView} stateName={stateName} setStateName={setStateName}
        />   
      
      { showInfo && (
        <div>
          <InfoMenu stateName={stateName} plan={plan} setPlan={setPlan} setSaved={setSaved} saved={saved}/>
          <LeftBar stateName={stateName} plan={plan} setGen={setGen} 
                state={state} setState={setState} setSaved={setSaved} saved={saved}/>
          <LayerSelector flag={flag} setFlag={setFlag}/>  
        </div>)
      } 
      {
        gen != true ? <div></div>
                    : <div className="spinner-border spinner-border-sm text-info reset" style={{width: "15rem",height: "15rem",position: 'absolute', left: '40%', top: '40%' }}></div>
      }
      <Map flag={flag} state={state} all={all}
          view={view} showClick={showClick} showInfo={showInfo}
          />
    </div>
    );
}
export default HomeScreen;