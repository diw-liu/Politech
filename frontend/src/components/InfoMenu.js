import React, { useState, useEffect } from 'react';
import PlanTable from './PlanTable';
import '../css/InfoMenu.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

export default function InfoMenu() {
  return (
    <div className='InfoMenu'>
      <div class='container'>
        <p>Maryland</p>
        <ul class='nav nav-pills' role='tablist'>
          <li class='nav-item' role='presentation'>
            <button class='nav-link' data-bs-toggle='tab' data-bs-target='#enacted' role='tab' type='button' aria-selected='true'> Enacted </button>
          </li>
          <li class='nav-item' role='presentation'>
            <button class='nav-link' data-bs-toggle='tab' data-bs-target='#generated' role='tab' type='button' aria-selected='false'> Generated </button>
          </li>
        </ul>
        <div class='tab-content' id='pills-tabContent'>
          <div class='tab-pane fade show active' id='enacted' role='tabpanel' aria-labelledby='enacted-tab'>
            <PlanTable/>
          </div>
          <div class='tab-pane fade' id='generated' role='tabpanel' aria-labelledby='generated-tab'>
            <PlanTable/>
          </div>
        </div>
      </div>
    </div>
  );
}