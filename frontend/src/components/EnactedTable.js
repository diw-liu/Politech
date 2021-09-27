import React, { Component } from 'react';

import districts from '../data/mock.js';

class EnactedTable extends Component {

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
                            <tr key={district.id} align="start" onClick={() => this.toggleActive(district.id)} 
                                style={{background: this.state.highLight == district.id ? '#00afec' : 'white',
                                color: this.state.highLight == district.id ? 'white' : 'black'}}>
                              <td className="number" style={{ textAlign: 'left' }}>{district.number}</td>
                              <td className="population" style={{ textAlign: 'right' }}>{district.population.toLocaleString('en-US')}</td>
                              <td className="minorities" style={{ textAlign: 'right' }}>{Math.round((district.population - district.whites)/district.population * 10000, 4)/100}</td>
                              <td className="deviation" style={{ textAlign: 'right' }}>{district.deviation}</td>
                            </tr>
                        ))
                    }
                </table>
            </div>
        );
    }
} export default EnactedTable;