import React, { Component } from 'react';

const PlanStatistics = (props) => {
    var effgap = 2039


    return(
        <div>
          <table style={{ width: '100%' }}>
            <tr>
              <td> Population Equality (%) </td>
              <td style={{ textAlign: 'right' }}> 80 </td> 
            </tr> 
            <tr>
              <td> Racial Deviation (%) </td>
              <td style={{ textAlign: 'right' }}> 36 </td> 
            </tr> 
            <tr>
              <td> Majority-minority districts </td>
              <td style={{ textAlign: 'right' }}> 3 </td> 
            </tr> 
            <tr>
              <td> Efficiency gap </td>
              <td style={{ textAlign: 'right' }}> {effgap.toLocaleString('en-US') } </td> 
            </tr> 
            <tr>
              <td> Split counties </td>
              <td style={{ textAlign: 'right' }}> 7 </td> 
            </tr> 
            <tr>
              <td> Graph Compactness </td>
              <td style={{ textAlign: 'right' }}> 0.307 </td> 
            </tr> 
          </table>
        </div>
    );
    
} 
export default PlanStatistics;