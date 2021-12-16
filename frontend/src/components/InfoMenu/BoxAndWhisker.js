import React, { useEffect, Component, useState} from 'react';
// import Select from '@material-ui/core/Select';
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";
import {CanvasJSChart} from 'canvasjs-react-charts'

const basisMap = {
        "BLACK" : "African American",
        "DEMOCRATIC" : "Democratic",
        "REPUBLICAN" : "Republican"
}

const REVERSE = {
    "African American" : "BLACK",
    "Democratic" : "DEMOCRATIC",
    "Republican" : "REPUBLICAN"
}

const KEYS = {
    "MD" : "24",
    "MI" : "26",
    "PA" : "42"
}

const BoxAndWhisker = (props) => {

    const [basis, setBasis] = useState(basisMap["BLACK"]);
    const [ensemble, setEnsemble] = useState([]);
    const [enactedData, setEnactedData] = useState([]);
    const [enactedPoints, setEnactedPoints] = useState([]);
    // const [selectedPointsAll, setSelectedPointsAll] = useState({"recombination_of_districts-0": []});
    const [boxes, setBoxes] = useState(props.plots.filter( single => single.basis == 'BLACK').sort((a, b) => (a.min > b.min) ? 1 : -1));

    // const [enactedData,setEnactedData] = useState([]);
    const getEnactedData=()=>{
        var data = require('../../data/' + props.stateName + "/" + REVERSE[basis] + "/" + KEYS[props.stateName] + "PL0.json");
        setEnactedData(Object.values(data).sort((a, b) => (a < b) ? -1 : 1));
    }
    
    useEffect(() => {
        var districtStats = [];
        for (let i = 0; i < boxes.length; i++) {
            districtStats.push({
                label: "District " + (i + 1),
                y: [(boxes[i].min),
                    (boxes[i].lowerQuartile),
                    (boxes[i].upperQuartile),
                    (boxes[i].max),
                    (boxes[i].median)]
            });
        }
        setEnsemble(districtStats);
        console.log(ensemble);
        getEnactedData();
    }, [basis, boxes]);

    const handleBasisSelect = (key) => {
        setBasis(basisMap[key]);
        setBoxes(props.plots.filter(single => single.basis == key).sort((a, b) => (a.max > b.max) ? 1 : -1));
        console.log(boxes);
    }

    

    function getData(data,k){
        let dataPoints = [];
        for (let i = 0; i < Object.keys(data).length; i++)
        {
            dataPoints.push({
                x: i, labelPoints: String(k), y: data[i]
            });
        }
        return dataPoints;
    }

    const options = {
        theme: "light2",
        animationEnabled: true,
        title: {
            text : "Box and Whisker Plot"
        },
        axisY: {
            title : basis,
            gridThickness: 0,
            minimum: 0,
            maximum: 1
        },
        data: [
            {
            type : "boxAndWhisker",
            dataPoints : ensemble
            },
            {
                type: "scatter",
                name: "Enacted",
                color: "steelblue",
                markerType: "circle",
                toolTipContent: "<span style=\"color:steelblue\">{labelPoints}</span>: {y}",
                showInLegend: true,
                dataPoints: getData(enactedData, "Enacted")
            },
        //     {
        //         type: "scatter",
        //         name: "Proposed",
        //         color: "tomato",
        //         markerType: "circle",
        //         toolTipContent: "<span style=\"color:tomato\">{labelPoints}</span>: {y}",
        //         showInLegend: true,
        //         dataPoints: getData(Object.values(selectedPointsAll["recombination_of_districts-"+props.plan]),"Proposed")
        //     }
        ],
    }

    return (
            <>
            <br></br>
                <div className="basis-selector">
                    <DropdownButton
                        title={basis}
                        onSelect={handleBasisSelect}
                    >
                        <>
                        <Dropdown.Item key={"BLACK"} eventKey={"BLACK"}>
                        African American
                        </Dropdown.Item>
                        <Dropdown.Item key={"DEMOCRATIC"}eventKey={"DEMOCRATIC"}>
                        Democratic
                        </Dropdown.Item>
                        <Dropdown.Item key={"REPUBLICAN"} eventKey={"REPUBLICAN"}>
                        Republican
                        </Dropdown.Item>
                        </>
                    </DropdownButton>
                </div>
                <CanvasJSChart options={options} />
            </>
    );
}
export default BoxAndWhisker;
