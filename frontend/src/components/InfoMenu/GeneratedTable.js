import React, { useEffect, useState } from 'react';

import Districts from '../../data/mock2.js';
import '../../css/InfoMenu.css'


const GeneratedTable = (props) => {

    // const [plans, setPlans] = useState([]);

    // const request = async () => {
    //     const response = await fetch("/api/plan");
    //     const json = await response.json();
    //     console.log(json);
    // }
    
    // request();

    const [highLight, setHighLight] = useState();
    // var districts = Districts;
    console.log(props.districtings)
    console.log(props.districtings[0])

    const toggleActive = (id) =>{
        setHighLight(id)
        props.setPlan(id)
        console.log(id)
    }

    // useEffect(() =>{
    //     fetch("/api/plan")
    //     .then(res => res.json())
    //     .then(function(data) {
    //         console.log(data);
    //         setPlans(data);
    //     });
    // },[])

    return(
        <div class='table-responsive overflow-scroll'>
            <table class='table table-striped overflow-scroll'>
                <thead> 
                    <tr>
                        <th>Plan</th>
                        <th>Objective Function</th>
                        <th>Opportunity Districts</th>
                        <th>Population Equality</th>
                        <th>PolsbyPopper</th>
                    </tr>
                </thead>
                <tbody>
                {
                    props.districtings.slice(1).map(districting => (
                        <tr key={districting.id} align="start" onClick = {() => toggleActive(districting.id)}
                            style={{background: highLight == districting.id ? '#00afec' : 'white',
                            color: highLight == districting.id ? 'white' : 'black'}}
                            > 
                            <td className="PlanNumber" style={{ textAlign: 'left' }}>{districting.measures.id}</td>
                            <td className="PopulationEquality" style={{ textAlign: 'right' }}>{districting.measures.objectiveFunction.toFixed(2)}</td>
                            <td className="MajorityMinorityDistrictsNumber" style={{ textAlign: 'right' }}>{districting.measures.opportunityDistricts.toFixed(2)}</td>
                            <td className="GraphCompactness" style={{ textAlign: 'right' }}>{districting.measures.polsbyPopper.toFixed(2)}</td>
                            <td className="RacialDeviation" style={{ textAlign: 'right' }}>{districting.measures.populationEquality.toFixed(2)}</td>
                        </tr>
                    ))
                }
                {/* {
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
                } */}
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