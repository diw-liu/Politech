import React, { useState, useEffect } from 'react';
import '../css/StateDropdown.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

export default function Dropdown() {
  // const [count, setCount] = useState(0);

  // // Similar to componentDidMount and componentDidUpdate:
  // useEffect(() => {
  //   // Update the document title using the browser API
  //   document.title = `You clicked ${count} times`;
  // });

  return (
    <div className='StateDropdown'>
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