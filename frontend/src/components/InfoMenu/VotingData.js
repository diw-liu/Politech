import React, { useState } from 'react';

const ElectionEnum = {
    democraticVotes: 'Democratic',
    republicanVotes: 'Republican',
    otherVotes: 'Other'
}

const VotingData = (props) => {
    // const handleOnChange = (index) =>{
    //     let newArr = [...props.flag]; 
    //     newArr[index] = !newArr[index]; 
    //     props.setFlag(newArr);
    // }
    var count = {
        Democratic: {total:0, districts:0},
        Republican:  {total:0, districts:0},
        Other:  {total:0, districts:0},
        Total: {total:0, districts:0},
    }
    const length = props.enactedInfo.districts.length
    
    if(Object.keys(props.enactedInfo).length != 0){ 
        // console.log(props.enactedInfo)
        for(var i = 0; i< length; i++){
            var party = ""
            var vote = 0
            for(const variable in ElectionEnum){
                // console.log(props.enactedInfo.districts[i].election["democraticVotes"])
                const temp = props.enactedInfo.districts[i].election[variable]
                if(temp > vote){
                    party = variable
                    vote = temp
                }
                count[ElectionEnum[variable]]['total'] += temp
                count['Total']['total'] += temp
            }
            count[ElectionEnum[party]]['districts']+= 1
            count['Total']['districts'] += 1
            // console.log(props.enactedInfo.districts[i])
            // console.log(party+" "+ vote)
        }
    }
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
                {
                    Object.keys(count).slice(0, -1).map(party =>(
                        <tr className="item">
                            <th style={{ textAlign: 'left' }}>{party}</th>
                            <th style={{ textAlign: 'right' }}>{count[party]['districts']}</th>
                            <th style={{ textAlign: 'right' }}>{count[party].total}</th>
                            <th style={{ textAlign: 'right' }}>{count[party].districts/count.Total.districts}</th>
                            <th style={{ textAlign: 'right' }}>{((count[party].total/count.Total.total)*100).toFixed(2)+"%"}</th>
                        </tr>
                    ))
                }
            </table>
        </div>
    )
}

export default VotingData;