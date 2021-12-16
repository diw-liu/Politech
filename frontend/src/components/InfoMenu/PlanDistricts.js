import React, { useState } from 'react';


const PlanDistricts = (props) => {
    var typeToUse = "";
    if (props.popType == 0) {
        typeToUse = "population"
    } else {
        typeToUse = "vap"
    }
    console.log(props.districtings)
    console.log(props.plan)

    return (
        <div>   
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
                        props.districtings.find(p => p.id === props.plan).districts.map(x => (
                            <tr key={x.cd} style={{ textAlign: 'right' }} align="start">
                                <td className="number" style={{ width: '15%' }}>{x.cd}</td>
                                <td className="population">{x[typeToUse].total.toLocaleString()}</td>
                                <td className="totalVote">{x.election.totalVotes.toLocaleString()}</td>
                                <td className="demVote">{x.election.democraticVotes.toLocaleString()}</td>
                                <td className="repVote">{x.election.republicanVotes.toLocaleString()}</td>
                            </tr>
                        ))   
                    }
                </table>
            </div>
        </div>
    )   
}
export default PlanDistricts