import React, { useEffect, useState, useMemo } from 'react';
import ReactTooltip from 'react-tooltip';
import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";
import ReactTable from "react-table";
import { useTable, useSortBy } from 'react-table'
import XMLParser from 'react-xml-parser';
import SVG from 'react-inlinesvg';
import Districts from '../../data/mock2.js';
import '../../css/InfoMenu.css';

const PlansTable = (props) => {

    console.log(props);

    const [highLight, setHighLight] = useState();
    const [preview, setPreview] = useState();
    console.log(props);
    // const data = useState(() => props.props.districtings.map(datum => {
    //     return {
    //         id: datum.measures.id.split("PL")[1],
    //         objectiveFunction: datum.measures.objectiveFunction.toFixed(2),
    //         opportunityDistricts: datum.measures.opportunityDistricts,
    //         polsbyPopper: datum.measures.polsbyPopper.toFixed(2),
    //         populationEquality: datum.measures.populationEquality.toFixed(2),
    //         }
    // }));
    // const columns = useState([
    //     {
    //         Header: "Plan",
    //         accessor: 'plan',
    //     },
    //     {
    //         Header: "Objective Function",
    //         accessor: 'objFunc',
    //     },
    //     {
    //         Header: "Opportunity Districts",
    //         accessor: 'oppDist',
    //     },
    //     {
    //         Header: "Compactness",
    //         accessor: 'compactness',
    //     },
    //     {
    //         Header: "Population Equality",
    //         accessor: 'popEq',
    //     },
    // ]);
    // console.log(columns);   
    // console.log(data);

    // // var districts = Districts;
    // console.log(props.props.districtings)
    // console.log(props.props.districtings[0])

    const getPreview = async (id) => {    
        return fetch('/api/preview/' + props.props.stateName + '/' + id) // check if the stateName thing works
                .then((data) => data.text())
                .then((data) => setPreview(data))
                .catch((error) => {
                    console.log(error);
                });
    }

    // useEffect(() => {
    //     let tempData = props.districtings.map(datum => {
    //     return {
    //         id: datum.measures.id.split("PL")[1],
    //         objectiveFunction: datum.measures.objectiveFunction.toFixed(2),
    //         opportunityDistricts: datum.measures.opportunityDistricts,
    //         polsbyPopper: datum.measures.polsbyPopper.toFixed(2),
    //         populationEquality: datum.measures.populationEquality.toFixed(2),
    //         }
    //     });
    //     setData(tempData);
    //     console.log(data);
    // }, [props.districtings]);
    
    const toggleActive = async (id, measure) =>{
        setHighLight(id)
        props.props.setPlan(id)
        props.props.setMeasure(measure)
        console.log(await props.props.getPlan())
        getPreview(id)
        // console.log(id)
    }

    return(
        <>
        <div class='table-responsive overflow-scroll'>
            <button data-tip data-for='preview'>Preview ... </button>
            <ReactTooltip id='preview' place='left'><SVG width={240} src={preview}/></ReactTooltip>
            <table class='table table-striped overflow-scroll' style={{tableLayout: "fixed", height: "60%"}}>
                <thead> 
                    <tr>
                        <th>Plan</th>
                        <th>Objective Function</th>
                        <th>Opportunity Districts</th>
                        <th>Polsby Popper</th>
                        <th>Population Equality</th>
                    </tr>
                </thead>
                <tbody>
                {
                    props.props.districtings.slice(1).map(districting => (
                        <tr key={districting.id} align="start" onClick = {() => toggleActive(districting.id, districting.measures)}
                            style={{background: highLight == districting.id ? '#0d6efd' : 'white',
                            color: highLight == districting.id ? 'white' : 'black'}}
                            > 
                            <td className="PlanNumber" style={{ textAlign: 'left' }}>{districting.measures.id.split("PL")[1]}</td>
                            <td className="ObjectiveFunction" style={{ textAlign: 'right' }}>{districting.measures.objectiveFunction.toFixed(2)}</td>
                            <td className="OpportunityDistricts" style={{ textAlign: 'right' }}>{districting.measures.opportunityDistricts}</td>
                            <td className="GraphCompactness" style={{ textAlign: 'right' }}>{districting.measures.polsbyPopper.toFixed(2)}</td>
                            <td className="PopulationEquality" style={{ textAlign: 'right' }}>{districting.measures.populationEquality.toFixed(2)}</td>
                        </tr>
                    ))
                }
                </tbody>
            </table>
        </div>
        </>
    );
    
}
export default PlansTable