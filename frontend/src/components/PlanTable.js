import React, { Component } from 'react';

import districts from '../data/mock.js';

class PlanTable extends Component {

    constructor(props){
        super(props);
        this.state = {            
            districts: districts            
        };
    }

    render() {
        const {districts} = this.state;
        return(
            <div>
                <table style={{ width: '100%' }}>
                    <tr className="item">
                      <th style={{ textAlign: 'left' }}>District #</th>
                      <th style={{ textAlign: 'right' }}>Population</th>
                      <th style={{ textAlign: 'right' }}>Minority %</th>
                      <th style={{ textAlign: 'right' }}>Deviation</th>
                    </tr>
                    {
                        districts.map(district => (
                            <tr key={district.id} align="start">
                              <td className="number" style={{ textAlign: 'left' }}>{district.number}</td>
                              <td className="population" style={{ textAlign: 'right' }}>{district.population}</td>
                              <td className="minorities" style={{ textAlign: 'right' }}>{Math.round((district.population - district.whites)/district.population * 10000, 4)/100}</td>
                              <td className="deviation" style={{ textAlign: 'right' }}>{district.deviation}</td>
                            </tr>
                        ))
                    }
                </table>
            </div>
        );
    }
} export default PlanTable;