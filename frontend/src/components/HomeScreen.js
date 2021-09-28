import React, { useState } from 'react';
import InfoMenu from './InfoMenu/InfoMenu';
import LeftBar from './LeftBar/LeftBar';
import Map from './Map/Map';
import StateSelector from './Map/StateSelector';

import { showState } from './Map/Preprocess'
import { INITIAL_VIEW_STATE, getView } from './Map/ViewState'
import '../css/StateSelector.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

export const NAMES = ['Maryland', 'Michigan', 'Pennsylvania']

const HomeScreen = (props) =>{

  const [showInfo, setShowInfo] = useState(false)
  const [state, setState] = useState([])
  const [stateName, setStateName] = useState('')
  const [view, setView] = useState(INITIAL_VIEW_STATE)
  const [plan, setPlan] = useState(0)
  const [gen, setGen] = useState(false);
  const [saved, setSaved] = useState(false);

  const showClick = ( id ) =>{
    setShowInfo(true) 
    setState(showState(id))
    setStateName(NAMES[id])
    setView(getView(id))
  }

  return (
    <div>
      <StateSelector setShowInfo={setShowInfo} showClick={showClick}
        setView={setView} stateName={stateName} setStateName={setStateName}
        />     
      { showInfo && (
        <div>
          <InfoMenu stateName={stateName} setPlan={setPlan} setSaved={setSaved} saved={saved}/>
          <LeftBar stateName={stateName} plan={plan} setGen={setGen} setSaved={setSaved} saved={saved}/>
        </div>)
      } 
      {
        gen != true ? <div></div>
                    : <div className="spinner-border spinner-border-sm text-info reset" style={{width: "15rem",height: "15rem",position: 'absolute', left: '40%', top: '40%' }}></div>
      }
      <Map showInfo={showInfo} state={state} 
          view={view} showClick={showClick}
          />
    </div>
    );
}
export default HomeScreen;