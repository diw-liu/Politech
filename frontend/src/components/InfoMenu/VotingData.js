import React, { useState } from 'react';

const VotingData = (props) => {
    // const handleOnChange = (index) =>{
    //     let newArr = [...props.flag]; 
    //     newArr[index] = !newArr[index]; 
    //     props.setFlag(newArr);
    // }
    return(
        <div>
            <h5>Voting Data</h5>
            <table style={{ width: '100%' }}>
                <tr className="item">
                    <th style={{ textAlign: 'left' }}>Party</th>
                    <th style={{ textAlign: 'right' }}>Districts</th>
                    <th style={{ textAlign: 'right' }}>Votes</th>
                    <th style={{ textAlign: 'right' }}>District Percentage</th>
                    <th style={{ textAlign: 'right' }}>Vote Percentage</th>
                </tr>
                {/* {
                    parts.map(party => (
                        <tr key={party.id} align="start">
                        <td className="party_name" style={{ textAlign: 'left' }}>{party.id}</td>
                        <td className="party_district" style={{ textAlign: 'right' }}>{party.districts}</td>
                        <td className="party_vote" style={{ textAlign: 'right' }}>{party.votes.toLocaleString('en-US')}</td>
                        <td className="party_dper" style={{ textAlign: 'right' }}>{party.dper.toLocaleString('en-US')}</td>
                        <td className="party_vper" style={{ textAlign: 'right' }}>{party.vper.toLocaleString('en-US')}</td>
                        </tr>
                    ))
                } */}
            </table>
        </div>
    )
}

export default VotingData;