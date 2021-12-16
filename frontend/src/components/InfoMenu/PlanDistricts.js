import React, { useState } from 'react';
import ReactTooltip from 'react-tooltip';


const PlanDistricts = (props) => {
<<<<<<< HEAD
    console.log(props)

    // popType
    // getPlan
    // districtings
    // stateName

    // var countVote = {
    //     Democratic: {total:0, districts:0},
    //     Republican:  {total:0, districts:0},
    //     Other:  {total:0, districts:0},
    //     Total: {total:0, districts:0},
    // }

    // var countDemo = {
    //     White: 0,
    //     African_American: 0,
    //     Hispanic: 0,
    //     Asian_American: 0,
    //     Native_American: 0,
    //     Other: 0,
    //     Total: 0
    // }

    // var districting = props.districtings.filter(function(d) {
    //     if (d.id == props.plan) {
    //         console.log(d.id);
    //         return d.id == props.plan;
    //     }
    // })

    // const length = props.districtings[0].districts.length

    // if(Object.keys(props.enactedInfo).length != 0){
    //     for(var i = 0; i< length; i++){
    //         var party = ""
    //         var vote = 0
    //         for(const variable in ElectionEnum){
    //             const temp = districting[0].districts[i].election[variable]
    //             if(temp > vote){
    //                 party = variable
    //                 vote = temp
    //             }
    //             countVote[ElectionEnum[variable]]['total'] += temp
    //             countVote['Total']['total'] += temp
    //         }
    //         if(party != ""){
    //             countVote[ElectionEnum[party]]['districts']+= 1
    //             countVote['Total']['districts'] += 1
    //         }
    //     }
    // }

    // if (Object.keys(props.enactedInfo).length != 0){ 
    //     for(var i = 0; i< length; i++){
    //         for(const variable in PopulationEnum){ 
    //             if (props.popType === 0) 
    //                 countDemo[PopulationEnum[variable]] += districting[0].districts[i].population[variable]
    //             else if (props.popType === 1)
    //                 countDemo[PopulationEnum[variable]] += districting[0].districts[i].vap[variable]
    //         }
    //     }
    // }

    return(
        <div>
            {/* {<div>
            <h3>Voting Data</h3>
            <table style={{ width: '100%' }}>
                <tr className="item">
                    <th style={{ textAlign: 'left' }}>Party</th>
                    <th style={{ textAlign: 'right' }}>Districts</th>
                    <th style={{ textAlign: 'right' }}>Votes</th>
                    <th style={{ textAlign: 'right' }}>District Percentage</th>
                    <th style={{ textAlign: 'right' }}>Vote Percentage</th>
                </tr>
                {
                    Object.keys(countVote).slice(0, -1).map(party =>(
                        <tr className="item">
                            <td style={{ textAlign: 'left' }}>{party}</td>
                            <td style={{ textAlign: 'right' }}>{countVote[party]['districts']}</td>
                            <td style={{ textAlign: 'right' }}>{countVote[party].total.toLocaleString()}</td>
                            <td style={{ textAlign: 'right' }}>{((countVote[party].districts/countVote.Total.districts)*100).toFixed(2)+"%"}</td>
                            <td style={{ textAlign: 'right' }}>{((countVote[party].total/countVote.Total.total)*100).toFixed(2)+"%"}</td>
                        </tr>
                    ))
                }
            </table>
            </div> }

            <hr/>
{
            <div>
            <h3>Demographic Data </h3>
            <table style={{ width: '100%' }}>
                <tr className="item">
                <th style={{ textAlign: 'left' }}>Demographic</th>
                <th style={{ textAlign: 'right' }}>Population</th>
                <th style={{ textAlign: 'right' }}>Population Percentage</th>
                </tr>
                {
                    Object.keys(countDemo).slice(0, -1).map(popu =>(
                        <tr className="item">
                            <td style={{ textAlign: 'left' }}>{popu}</td>
                            <td style={{ textAlign: 'right' }}>{countDemo[popu].toLocaleString()}</td>
                            <td style={{ textAlign: 'right' }}>{((countDemo[popu]/countDemo.Total)*100).toFixed(2)+"%"}</td>
                        </tr>
                    ))
                }
                <tr>
                    <td style={{ textAlign: 'left' }}>Total</td>
                    <td style={{ textAlign: 'right' }}>{countDemo["Total"].toLocaleString()}</td>
                    <td style={{ textAlign: 'right' }}>{}</td>
                </tr>
            </table>
            </div> } */}
=======
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
>>>>>>> 0cc026181a3ca71609228594d3417a9d8b467327
        </div>
    )   
}
export default PlanDistricts