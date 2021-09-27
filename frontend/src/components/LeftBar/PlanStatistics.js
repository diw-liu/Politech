import React, { Component } from 'react';

const PlanStatistics = (props) => {

    return(
        <div>
          <table style={{ width: '100%' }}>
            <tr>
              <td> Population Equality (%) </td>
              <td style={{ textAlign: 'right' }}> {Math.round(Math.random() * 100)} </td> 
            </tr> 
            <tr>
              <td> Racial Deviation (%) </td>
              <td style={{ textAlign: 'right' }}> {Math.round(Math.random() * 100)} </td> 
            </tr> 
            <tr>
              <td> Majority-minority districts </td>
              <td style={{ textAlign: 'right' }}> {Math.round(Math.random() * 5) + 5} </td> 
            </tr> 
            <tr>
              <td> Efficiency gap </td>
              <td style={{ textAlign: 'right' }}> {Math.round(Math.random() * 10000).toLocaleString('en-US') } </td> 
            </tr> 
            <tr>
              <td> Split counties </td>
              <td style={{ textAlign: 'right' }}> {Math.round(Math.random() * 100)} </td> 
            </tr> 
            <tr>
              <td> Graph Compactness </td>
              <td style={{ textAlign: 'right' }}> {Math.round(Math.random() * 100)/100} </td> 
            </tr> 
          </table>
        </div>
    );
    
} 
export default PlanStatistics;