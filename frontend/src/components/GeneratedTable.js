import React, { Component } from 'react';

import districts from '../data/mock2.js';

class GeneratedTable extends Component {

    constructor(props){
        super(props);
        this.state = {            
            districts: districts,
            highLight: Number    
        };
    }

    toggleActive(id) {
        this.setState({
            highLight: id,
        });
        console.log(this.state.highLight)
      }

    render() {
        const {districts} = this.state;
        const {highLight} = this.state;
        return(
            <div>
                <table style={{ width: '100%'}}>
                    <tr className="item" >
                      <th style={{ textAlign: 'left' }}>Plan #</th>
                      <th style={{ textAlign: 'right' }}>Population Equality</th>
                      <th style={{ textAlign: 'right' }}>Majority Minority Districts</th>
                      <th style={{ textAlign: 'right' }}>Graph Compactness %</th>
                      <th style={{ textAlign: 'right' }}>Racial Deviation %</th>
                    </tr>
                    {
                        districts.map(district => (
                            <tr key={district.id} align="start" onClick={() => this.toggleActive(district.id)} 
                                style={{background: this.state.highLight == district.id ? '#00afec' : 'white',
                                color: this.state.highLight == district.id ? 'white' : 'black'}}>
                              <td className="PlanNumber" style={{ textAlign: 'left' }}>{district.plan}</td>
                              <td className="PopulationEquality" style={{ textAlign: 'right' }}>{district.population}</td>
                              <td className="MajorityMinorityDistrictsNumber" style={{ textAlign: 'right' }}>{district.majorityMinority}</td>
                              <td className="GraphCompactness" style={{ textAlign: 'right' }}>{district.graphCompactness}</td>
                              <td className="RacialDeviation" style={{ textAlign: 'right' }}>{district.racialDeviation}</td>
                            </tr>
                        ))
                    }
                    {
                        
                    }
                </table>
                
            </div>
            
        );
    }
} export default GeneratedTable;