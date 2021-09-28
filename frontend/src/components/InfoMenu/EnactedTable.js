import React, { useState } from 'react';

import districts from '../../data/mock.js';

const EnactedTable = (props) => {
    // const [districts, setDistricts] = useState(districts);
    const [numDistricts, setNumDistricts] = useState(Math.round(Math.random() * 5) + 5);

    return(
        <div>
            <table style={{ width: '100%' }}>
                <tr className="item" style={{ textAlign: 'right' }}>
                    <th>District</th>
                    <th>Population</th>
                    <th>Minority %</th>
                    <th>Deviation</th>
                </tr>
                {
                    Array.from({length: numDistricts - 1}, (elem, index) => elem = index + 1).map((i) => (
                        <tr key={i} style={{ textAlign: 'right' }} align="start">
                            <td className="number" style={{ width: '15%' }}>{i}</td>
                            <td className="population">{(200000 + Math.round(Math.random()*50000)).toLocaleString('en-US')}</td>
                            <td className="minorities">{Math.round(Math.random()*100)}</td>
                            <td className="deviation">{Math.round(Math.random()*100)/100}</td>
                        </tr>
                    ))
                }
            </table>
        </div>
    );
} 
export default EnactedTable;