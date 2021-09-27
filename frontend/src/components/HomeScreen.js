import React, { useState } from 'react';
import InfoMenu from './InfoMenu';
import LeftBar from './LeftBar';
import Map from './Map';
import StateSelector from './StateSelector';

import { showState } from './Preprocess'
import { INITIAL_VIEW_STATE, getView } from './ViewState'

export const NAMES = ['Maryland', 'Michigan', 'Pennsylvania']

const HomeScreen = (props) =>{

  const [showInfo, setShowInfo] = useState(false)
  const [state, setState] = useState([])
  const [stateName, setStateName] = useState('')
  const [view, setView] = useState(INITIAL_VIEW_STATE)
  const [plan, setPlan] = useState('Enacted')
  // const []
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
          <InfoMenu stateName={stateName}/>
          <LeftBar stateName={stateName}/>
        </div>)
      } 
      <Map showInfo={showInfo} state={state} 
          view={view} showClick={showClick}
          />
    </div>
    );
}
export default HomeScreen;