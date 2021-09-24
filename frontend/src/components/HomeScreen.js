import React, { useRef, useEffect, useState } from 'react';

import StateSelector from './StateSelector';
import Map from './Map';

const initialState = {
  stateSelected: null
}

const HomeScreen = (props) =>{

    return (
      <div>
        <div>
          <StateSelector data={initialState}/>
          <Map />
        </div>
        
      </div>
      );
}
export default HomeScreen;