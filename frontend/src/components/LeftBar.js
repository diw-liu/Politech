import React, { useState, useEffect } from 'react';
import '../css/LeftBar.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

export default function LeftBar() {
  return (
    <div className='LeftBar'>
      <div class='container'>
        <p> Maryland </p>
        <p> Population Equality: 80% </p>
        <p> Split counties: 8 </p>
        <p> Deviation: 12 </p>
      </div>
    </div>
  );
}