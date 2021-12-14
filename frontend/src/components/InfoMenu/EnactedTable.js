import React, { useState } from 'react';

import districts from '../../data/mock.js';

const EnactedTable = (props) => {
    // const [districts, setDistricts] = useState(districts);
    const [numDistricts, setNumDistricts] = useState(Math.round(Math.random() * 5) + 5);
    console.log(props.enactedInfo)
    return(
        <div>
            <table style={{ width: '100%' }}>
                <tr>
                    <th>District</th>
                    <th>Population</th>
                    <th>Vote</th>
                </tr>
                {
                    props.enactedInfo.districts.map(x => (
                        <tr key={x.cd} style={{ textAlign: 'right' }} align="start">
                            <td className="number" style={{ width: '15%' }}>{x.cd}</td>
                            <td className="population">{x.population.total}</td>
                            <td className="minorities">{x.election.totalVotes}</td>
                        </tr>
                    )) 
                    
                }
            </table>
        </div>
    );
} 
export default EnactedTable;