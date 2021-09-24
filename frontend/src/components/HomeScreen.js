
import React, { useRef, useEffect, useState } from 'react';
import StateSelector from './StateSelector';
import InfoMenu from './InfoMenu';
import Map from './Map';

const HomeScreen = (props) =>{
    return (
      <div>
        <div>
          <StateSelector />     
        </div>
        <div>
          <InfoMenu/>
        </div>
        <Map />
      </div>
      );
}
export default HomeScreen;