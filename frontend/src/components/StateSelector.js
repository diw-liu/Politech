import React, { useState, useEffect } from 'react';
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem, Selection } from 'reactstrap';

import '../css/StateSelector.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

export default function StateSelector(data) {
  console.log(data);
  const [stateSelected, setStateSelected] = useState(data);
  const [count, setCount] = useState(0);

  // // Similar to componentDidMount and componentDidUpdate:
  // useEffect(() => {
  //   // Update the document title using the browser API
  //   document.title = `You clicked ${count} times`;
  // });

  return (
    // <Dropdown style={{zIndex:'1', position: 'absolute'}}>
    //   {/* <p>You clicked {count} times</p>
    //   <button onClick={() => setCount(count + 1)}>
    //     Click me
    //   </button> */}
    // </Dropdown>
    <div className='StateSelector'>
      State
      <hr/>
      <select>
        <option value='maryland'>Maryland</option>
        <option value='michigan'>Michigan</option>
        <option value='pennsylvania'>Pennsylvania</option>
      </select>
    </div>
  );
}