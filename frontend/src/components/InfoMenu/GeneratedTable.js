import React, { useState } from 'react';

import Districts from '../../data/mock2.js';
import '../../css/InfoMenu.css'

const GeneratedTable = (props) => {

    const [highLight, setHighLight] = useState();
    var districts = Districts

    const toggleActive = (id) =>{
        setHighLight(id)
        props.setPlan(id)
        console.log(id)
    }

    return(
        <div class='table-responsive overflow-scroll'>
            <table class='table table-striped overflow-scroll'>
                <thead> 
                    <tr>
                        <th>Plan</th>
                        <th>Population Equality</th>
                        <th>Majority Minority Districts</th>
                        <th>Graph Compactness %</th>
                        <th>Racial Deviation %</th>
                    </tr>
                </thead>
                <tbody>
                {
                    // districts.map(district => (
                    //     <tr key={district.id} align="start" onClick = {() => toggleActive(district.id)}
                    //         style={{background: highLight == district.id ? '#00afec' : 'white',
                    //         color: highLight == district.id ? 'white' : 'black'}}
                    //         > 
                    //         <td className="PlanNumber" style={{ textAlign: 'left' }}>{district.plan}</td>
                    //         <td className="PopulationEquality" style={{ textAlign: 'right' }}>{district.population}</td>
                    //         <td className="MajorityMinorityDistrictsNumber" style={{ textAlign: 'right' }}>{district.majorityMinority}</td>
                    //         <td className="GraphCompactness" style={{ textAlign: 'right' }}>{district.graphCompactness}</td>
                    //         <td className="RacialDeviation" style={{ textAlign: 'right' }}>{district.racialDeviation}</td>
                    //     </tr>
                    // ))
                }
                {
                    Array.from({length: 30}, (elem, index) => elem = index + 1).map((i) => (
                        <tr key={i} align="start" onClick = {() => toggleActive(i)}
                            style={{background: highLight == i ? '#00afec' : 'white',
                            color: highLight == i ? 'white' : 'black'}}
                        > 
                            <td className="PlanNumber" style={{ textAlign: 'right' }}>{i}</td>
                            <td className="PopulationEquality" style={{ textAlign: 'right' }}>{Math.round(Math.random()*100)/100}</td>
                            <td className="MajorityMinorityDistrictsNumber" style={{ textAlign: 'right' }}>{Math.round(Math.random() * 10) % 6 + 2}</td>
                            <td className="GraphCompactness" style={{ textAlign: 'right' }}>{Math.round(Math.random()*100)}</td>
                            <td className="RacialDeviation" style={{ textAlign: 'right' }}>{Math.round(Math.random()*100)}</td>
                        </tr>
                    ))
                }
                {
                    // districts.map(district => (
                    //     <tr key={district.id} align="start" onClick={toggleActive(district.id)} 
                            
                    //         >
                    //         <td className="PlanNumber" style={{ textAlign: 'left' }}>{district.plan}</td>
                    //         <td className="PopulationEquality" style={{ textAlign: 'right' }}>{district.population}</td>
                    //         <td className="MajorityMinorityDistrictsNumber" style={{ textAlign: 'right' }}>{district.majorityMinority}</td>
                    //         <td className="GraphCompactness" style={{ textAlign: 'right' }}>{district.graphCompactness}</td>
                    //         <td className="RacialDeviation" style={{ textAlign: 'right' }}>{district.racialDeviation}</td>
                    //     </tr>
                    // )) 
                }
                </tbody>
            </table>
        </div>
    );
}
 
export default GeneratedTable;