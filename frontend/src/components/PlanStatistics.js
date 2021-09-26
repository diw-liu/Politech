import React, { Component } from 'react';

class PlanStatistics extends Component {
    constructor(props){
        super(props);
        this.state = {
          plan: 'Enacted',
          effgap: 2039
        };
    }

    render() {
        return(
            <div>
              <h3> Plan: { this.state.plan } </h3>
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
                  <td style={{ textAlign: 'right' }}> { this.state.effgap.toLocaleString('en-US') } </td> 
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
} export default PlanStatistics;