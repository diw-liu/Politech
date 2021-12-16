import React, { useState } from 'react';

const ElectionEnum = {
    democraticVotes: 'Democratic',
    republicanVotes: 'Republican',
    otherVotes: 'Other'
}

const PopulationEnum = {
    white: 'White',
    hispanic: 'Hispanic',
    black: 'African American',
    asian: 'Asian American',
    nativ: 'Native American',
    other: 'Other',
    total: 'Total'
}

const PlanDistricts = (props) => {

    // const popType = props.popType;

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

    // const lengthVote = props.districtings.districts.length
    // const lengthDemo = props.districtings.districts.length

    // if(Object.keys(props.enactedInfo).length != 0){
    //     for(var i = 0; i< length; i++){
    //         var party = ""
    //         var vote = 0
    //         for(const variable in ElectionEnum){
    //             const temp = props.districtings.districts[i].election[variable]
    //             if(temp > vote){
    //                 party = variable
    //                 vote = temp
    //             }
    //             count[ElectionEnum[variable]]['total'] += temp
    //             count['Total']['total'] += temp
    //         }
    //         if(party != ""){
    //             count[ElectionEnum[party]]['districts']+= 1
    //             count['Total']['districts'] += 1
    //         }
    //     }
    // }

    return(
        <div>

        </div>
    )
    
}
export default PlanDistricts