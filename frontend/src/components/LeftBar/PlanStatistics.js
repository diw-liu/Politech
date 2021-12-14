import React, { Component } from 'react';

import Plans from '../../data/mockGenerated.js';

const PlanStatistics = (props) => {
  var plans = Plans;

  console.log("TETETTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
  console.log(props.plan);
  // console.log(props.plan);

    return(
        <div>
          {props.plan == 0 ? <div>  </div> : <table style={{ width: '100%' }}>
            <tr>
              <td> Objective Function </td>
              <td style={{ textAlign: 'right' }}> {plans[props.plan].objectiveFunction} </td> 
            </tr> 
            <tr>
              <td> Opportunity Districts </td>
              <td style={{ textAlign: 'right' }}> {plans[props.plan].opportunityDistricts} </td> 
            </tr> 
            <tr>
              <td> Compactness Measure </td>
              <td style={{ textAlign: 'right' }}> {plans[props.plan].polsbyPopper } </td> 
            </tr> 
            <tr>
              <td> Population Equality </td>
              <td style={{ textAlign: 'right' }}> {plans[props.plan].populationEquality} </td> 
            </tr> 
          </table>}
        </div>
    );
    
} 
export default PlanStatistics;