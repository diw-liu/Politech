import React, { useState } from 'react';
import ReactTooltip from 'react-tooltip';

import districts from '../../data/mock.js';

const EnactedTable = (props) => {
    // const [districts, setDistricts] = useState(districts);
    // const [numDistricts, setNumDistricts] = useState(Math.round(Math.random() * 5) + 5);
    // console.log(props.enactedInfo)
    console.log(props);
    var typeToUse = "";
    if (props.popType == 0) {
        typeToUse = "population"
    } else {
        typeToUse = "vap"
    }
    return(
        <div>
            <table style={{ width: '100%' }}>
                <tr>
                    <th>District</th>
                    <th style={{ textAlign: 'right' }}>Population</th>
                    <th style={{ textAlign: 'right' }}>Vote</th>
                    <th style={{ textAlign: 'right' }}>Democratic</th>
                    <th style={{ textAlign: 'right' }}>Republican</th>
                </tr>
                {
                    props.enactedInfo.districts.map(x => (
                        <>
                            <ReactTooltip id={x.cd} place="bottom" effect="solid">
                                <p> White: {x[typeToUse].white.toLocaleString()} </p>
                                <p> Black: {x[typeToUse].black.toLocaleString()} </p> 
                                <p> Hispanic: {x[typeToUse].hispanic.toLocaleString()} </p> 
                                <p> Native American: {x[typeToUse].nativ.toLocaleString()} </p> 
                                <p> Asian: {x[typeToUse].asian.toLocaleString()} </p> 
                                <p> Other: {x[typeToUse].other.toLocaleString()} </p> 
                            </ReactTooltip>
                            <tr key={x.cd} style={{ textAlign: 'right' }} align="start">
                                <td className="number" style={{ width: '15%' }}>{x.cd}</td>
                                <td className="population" data-tip data-for={x.cd}>{x[typeToUse].total.toLocaleString()}</td>
                                <td className="totalVote">{x.election.totalVotes.toLocaleString()}</td>
                                <td className="demVote">{x.election.democraticVotes.toLocaleString()}</td>
                                <td className="repVote">{x.election.republicanVotes.toLocaleString()}</td>
                            </tr>
                        </>
                    ))   
                }
            </table>
        </div>
    );
} 
export default EnactedTable;