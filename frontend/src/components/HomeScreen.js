import React, { useRef, useEffect, useState } from 'react';
import StateSelector from './StateSelector';
import InfoMenu from './InfoMenu';
import Map from './Map';
import { DataContext, useData } from '../contexts/DataContext';

// const values = {
//   stateSelected: null,
//   dropdownTitle: "Select State"
// }

const HomeScreen = (props) =>{
  const [info, setInfo] = useData(/*DataContext*/);
    return (
      <div>
        <div>
          <StateSelector data={info}/>     
        </div>
        <div>
          {info.stateSelected && <InfoMenu/>}
        </div>
        <Map />
      </div>
      );
}
export default HomeScreen;