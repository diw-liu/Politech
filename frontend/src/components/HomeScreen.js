import React, { useRef, useEffect, useState } from 'react';
import InfoMenu from './InfoMenu';
import LeftBar from './LeftBar';
import Map from './Map';
import StateSelector from './StateSelector';
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
          {info.stateSelected && (<div><InfoMenu/><LeftBar/></div>)} {/* conditional rendering of InfoMenu and LeftBar */}
        </div>
        <Map />
      </div>
      );
}
export default HomeScreen;