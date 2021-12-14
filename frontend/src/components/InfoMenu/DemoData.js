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
            }
        }
    }
    console.log(count)
    return(
        <div>
            <h3>Demographic Data </h3>
            <table style={{ width: '100%' }}>
                <tr className="item">
                <th style={{ textAlign: 'left' }}>Demographic</th>
                <th style={{ textAlign: 'right' }}>Population</th>
                <th style={{ textAlign: 'right' }}>Population Percentage</th>
                </tr>
                {
                    Object.keys(count).slice(0, -1).map(popu =>(
                        <tr className="item">
                            <td style={{ textAlign: 'left' }}>{popu.replace("_", " ")}</td>
                            <td style={{ textAlign: 'right' }}>{count[popu].toLocaleString()}</td>
                            <td style={{ textAlign: 'right' }}>{((count[popu]/count.Total)*100).toFixed(2)+"%"}</td>
                        </tr>
                    ))
                }
            (Total Population: {count["Total"]})
            </table>
        </div>
    )
}

export default DemoData;