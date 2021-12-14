import React, { Component } from 'react';

import Plans from '../../data/mockGenerated.js';

const PlanStatistics = (props) => {
  var plans = Plans;

  console.log(props.plan);
  // console.log(props.plan);

    return(
        <div>
          <table style={{ width: '100%' }}>
            <tr>
              <td> Population Equality (%) </td>
              <td style={{ textAlign: 'right' }}> {(props.plan == 0) ? 83 : plans[props.plan].pop_eq} </td> 
            </tr> 
            <tr>
              <td> Racial Deviation (%) </td>
              <td style={{ textAlign: 'right' }}> {(props.plan == 0) ? 87 :plans[props.plan].dev} </td> 
            </tr> 
            <tr>
              <td> Majority-Minority districts </td>
              <td style={{ textAlign: 'right' }}> {(props.plan == 0) ? 3 :plans[props.plan].maj_min} </td> 
            </tr> 
            <tr>
              <td> Efficiency Gap </td>
              <td style={{ textAlign: 'right' }}> {(props.plan == 0) ? 77 :plans[props.plan].eff_gap } </td> 
            </tr> 
            <tr>
              <td> Partisan Symmetry </td>
              <td style={{ textAlign: 'right' }}> {(props.plan == 0) ? 11 :plans[props.plan].symmetry} </td> 
            </tr> 
            <tr>
              <td> Graph Compactness </td>
              <td style={{ textAlign: 'right' }}> {(props.plan == 0) ? 13 :plans[props.plan].compactness} </td> 
            </tr> 
          </table>
        </div>
    );
    
} 
export default PlanStatistics;