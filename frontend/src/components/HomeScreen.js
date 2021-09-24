
import React, { useRef, useEffect, useState } from 'react';
import Dropdown from './Dropdown';
import InfoMenu from './InfoMenu';
import Map from './Map';

const HomeScreen = (props) =>{
    return (
      <div>
        <div>
          <Dropdown />     
        </div>
        <div>
          <InfoMenu/>
        </div>
        <Map />
      </div>
      );
}
export default HomeScreen;