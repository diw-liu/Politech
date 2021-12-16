import React, { Component } from 'react';

import Plans from '../../data/mockGenerated.js';

const PlanStatistics = (props) => {
  
  const measure = props.measure
  const flag = Object.keys(props.measure).length == 0 ? true : false
  console.log(props.measure);
  // console.log(props.plan);
  
  return(
      <div>
        <table style={{ width: '100%' }}>
          <tr>
            <td> Objective Function </td>
            <td style={{ textAlign: 'right' }}> { measure.objectiveFunction.toFixed(2)} </td> 
          </tr> 
          <tr>
            <td> Opportunity Districts </td>
            <td style={{ textAlign: 'right' }}> {measure.opportunityDistricts.toFixed(2)} </td> 
          </tr> 
          <tr>
            <td> Compactness Measure </td>
            <td style={{ textAlign: 'right' }}> {measure.polsbyPopper.toFixed(2)} </td> 
          </tr> 
          <tr>
            <td> Population Equality </td>
            <td style={{ textAlign: 'right' }}> {measure.populationEquality.toFixed(2)} </td> 
          </tr> 
        </table>
      </div>
  );
    
} 
export default PlanStatistics;