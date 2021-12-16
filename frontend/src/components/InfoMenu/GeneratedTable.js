import React, { useEffect, useState } from 'react';

import Districts from '../../data/mock2.js';
import '../../css/InfoMenu.css'


const GeneratedTable = (props) => {

    const [highLight, setHighLight] = useState();
    const [preview, setPreview] = useState();
    // var districts = Districts;
    console.log(props.districtings)
    console.log(props.districtings[0])

    const toggleActive = async (id, measure) =>{
        setHighLight(id)
        props.setPlan(id)
        props.setMeasure(measure)
        console.log(await props.getPlan())
        // console.log(id)
    }

    return(
        <div class='table-responsive overflow-scroll'>
            {/* <img style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center"
            }} src="localhost:8080/api/preview/MD/24PL2"></img> */}
            <table class='table table-striped overflow-scroll' style={{tableLayout: "fixed"}}>
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
                        <tr key={districting.id} align="start" onClick = {() => toggleActive(districting.id, districting.measures)}
                            style={{background: highLight == districting.id ? '#0d6efd' : 'white',
                            color: highLight == districting.id ? 'white' : 'black'}}
                            > 
                            <td className="PlanNumber" style={{ textAlign: 'left' }}>{districting.measures.id}</td>
                            <td className="PopulationEquality" style={{ textAlign: 'right' }}>{districting.measures.objectiveFunction.toFixed(2)}</td>
                            <td className="MajorityMinorityDistrictsNumber" style={{ textAlign: 'right' }}>{districting.measures.opportunityDistricts}</td>
                            <td className="GraphCompactness" style={{ textAlign: 'right' }}>{districting.measures.polsbyPopper.toFixed(2)}</td>
                            <td className="RacialDeviation" style={{ textAlign: 'right' }}>{districting.measures.populationEquality.toFixed(2)}</td>
                        </tr>
                    ))
                }
                </tbody>
            </table>
        </div>
    );
}
 
export default GeneratedTable;