import React, { useState } from 'react';

const PopulationEnum = {
    white: 'White',
    hispanic: 'Hispanic',
    black: 'African_American',
    asian: 'Asian_American',
    nativ: 'Native_American',
    other: 'Other',
    total: 'Total'
}

const DemoData = (props) => {
    // const handleOnChange = (index) =>{
    //     let newArr = [...props.flag]; 
    //     newArr[index] = !newArr[index]; 
    //     props.setFlag(newArr);
    // }
    var count = {
        White: 0,
        Hispanic: 0,
        African_American: 0,
        Asian_American: 0,
        Native_American: 0,
        Other: 0,
        Total: 0
    }
    const length = props.enactedInfo.districts.length
    
    if(Object.keys(props.enactedInfo).length != 0){ 
        console.log(props.enactedInfo)
        for(var i = 0; i< length; i++){
            for(const variable in PopulationEnum){ 
                count[PopulationEnum[variable]] += props.enactedInfo.districts[i].population[variable]
            // var party = ""
            // var vote = 0
            // for(const variable in ElectionEnum){
            //     // console.log(props.enactedInfo.districts[i].election["democraticVotes"])
            //     const temp = props.enactedInfo.districts[i].election[variable]
            //     if(temp > vote){
            //         party = variable
            //         vote = temp
            //     }
            //     console.log(count[ElectionEnum[variable]]['total'])
            //     count[ElectionEnum[variable]]['total'] += temp
            //     count['Total']['total'] += temp
            // }
            // count[ElectionEnum[party]]['districts']+= 1
            // count['Total']['districts'] += 1
            // console.log(props.enactedInfo.districts[i])
            // console.log(party+" "+ vote)
            }
        }
    }
    console.log(count)
    return(
        <div>
            <h5>Demographic Data </h5>
            (Total Population: )
            <table style={{ width: '100%' }}>
                <tr className="item">
                <th style={{ textAlign: 'left' }}>Demographic</th>
                <th style={{ textAlign: 'right' }}>Population</th>
                <th style={{ textAlign: 'right' }}>Population Percentage</th>
                </tr>
                {
                    Object.keys(count).slice(0, -1).map(popu =>(
                        <tr className="item">
                            <th style={{ textAlign: 'left' }}>{popu}</th>
                            <th style={{ textAlign: 'right' }}>{count[popu]}</th>
                            <th style={{ textAlign: 'right' }}>{((count[popu]/count.Total)*100).toFixed(2)+"%"}</th>
                        </tr>
                    ))
                }
            </table>
        </div>
    )
}

export default DemoData;