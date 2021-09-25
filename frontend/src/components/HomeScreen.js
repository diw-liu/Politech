
import React, { useRef, useEffect, useState } from 'react';
import StateSelector from './StateSelector';
import InfoMenu from './InfoMenu';
import Map from './Map';

const values = {
  stateSelected: null,
  dropdownTitle: "Select State"
}

// const [variableData, setVariableData] = useState(initialState);

const HomeScreen = (props) =>{
    return (
      <div>
        <div>
          <StateSelector data={values}/>     
        </div>
        <div>
          {values.stateSelected && <InfoMenu/>}
          
        </div>
        <Map />
      </div>
      );
}
export default HomeScreen;