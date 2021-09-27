import React, { Component, useState } from 'react';

import Districts from '../data/mock2.js';

const GeneratedTable = (props) => {

    const [highLight, setHighLight] = useState();
    var districts = Districts

    const toggleActive = (id) =>{
        setHighLight(id)
        props.setPlan(id)
        console.log(id)
    }

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
                        <tr key={district.id} align="start" onClick = {() => toggleActive(district.id)}
                            style={{background: highLight == district.id ? '#00afec' : 'white',
                            color: highLight == district.id ? 'white' : 'black'}}
                            > 
                            <td className="PlanNumber" style={{ textAlign: 'left' }}>{district.plan}</td>
                            <td className="PopulationEquality" style={{ textAlign: 'right' }}>{district.population}</td>
                            <td className="MajorityMinorityDistrictsNumber" style={{ textAlign: 'right' }}>{district.majorityMinority}</td>
                            <td className="GraphCompactness" style={{ textAlign: 'right' }}>{district.graphCompactness}</td>
                            <td className="RacialDeviation" style={{ textAlign: 'right' }}>{district.racialDeviation}</td>
                        </tr>
                    ))
                }
                {/* /* {
                    districts.map(district => (
                        <tr key={district.id} align="start" onClick={toggleActive(district.id)} 
                            
                            >
                            <td className="PlanNumber" style={{ textAlign: 'left' }}>{district.plan}</td>
                            <td className="PopulationEquality" style={{ textAlign: 'right' }}>{district.population}</td>
                            <td className="MajorityMinorityDistrictsNumber" style={{ textAlign: 'right' }}>{district.majorityMinority}</td>
                            <td className="GraphCompactness" style={{ textAlign: 'right' }}>{district.graphCompactness}</td>
                            <td className="RacialDeviation" style={{ textAlign: 'right' }}>{district.racialDeviation}</td>
                        </tr>
                    )) */}

            </table>
        </div>
        );
}
 
export default GeneratedTable;