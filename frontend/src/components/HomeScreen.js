import React, { useRef, useEffect, useState } from 'react';
import InfoMenu from './InfoMenu';
import LeftBar from './LeftBar';
import Map from './Map';
import StateSelector from './StateSelector';

import { showState } from './Preprocess'
import { INITIAL_VIEW_STATE, getView } from './ViewState'
// const values = {
//   stateSelected: null,
//   dropdownTitle: "Select State"
// }

const HomeScreen = (props) =>{
  const [showInfo, setShowInfo] = useState(false)
  const [state, setState] = useState([])
  const [view, setView] = useState(INITIAL_VIEW_STATE);

  const showClick = ( id ) =>{
    setShowInfo(true) 
    setState(showState(id))
    setView(getView(id))
  }
    return (
      <div>
        <StateSelector setShowInfo={setShowInfo} showClick={showClick}
          setView={setView}
          />     
        { showInfo && (
          <div>
            <InfoMenu/>
            <LeftBar/>
          </div>)
        } 
        <Map showInfo={showInfo} state={state} 
            view={view} showClick={showClick}
            />
      </div>
      );
}
export default HomeScreen;