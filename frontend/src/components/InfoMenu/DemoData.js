import React, { useState } from 'react';

const DemoData = (props) => {
    // const handleOnChange = (index) =>{
    //     let newArr = [...props.flag]; 
    //     newArr[index] = !newArr[index]; 
    //     props.setFlag(newArr);
    // }
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
                {/* {
                    demos.map(demo => (
                        <tr key={demo.id} align="start">
                        <td className="demo_name" style={{ textAlign: 'left' }}>{demographicMapping[demo.type]}</td>
                        <td className="demo_pop" style={{ textAlign: 'right' }}>{demo.population.toLocaleString('en-US')}</td>
                        <td className="demo_pper" style={{ textAlign: 'right' }}>{parseFloat(((demo.population / totalPopDem) * 100).toFixed(2))}</td>
                        </tr>
                    ))
                } */}
            </table>
        </div>
    )
}

export default DemoData;