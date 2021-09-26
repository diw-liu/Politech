import React, { Component } from 'react';

class PlanStatistics extends Component {
    constructor(props){
        super(props);
        this.state = {
          plan: 'Enacted'
        };
    }

    render() {
        return(
            <div>
              <h3> Plan: { this.state.plan } </h3>
              <table style={{ width: '100%' }}>
                <tr>
                  <td> Population Equality (%) </td>
                  <td> 80 </td> 
                </tr> 
                <tr>
                  <td> Split counties </td>
                  <td> 8 </td> 
                </tr> 
                <tr>
                  <td> Deviation </td>
                  <td> 8 </td> 
                </tr> 
              </table>
            </div>
        );
    }
} export default PlanStatistics;