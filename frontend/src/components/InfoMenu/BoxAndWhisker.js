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

const BoxAndWhisker = (props) => {

    
    console.log(props);
    console.log(props.plots);
    const [basis, setBasis] = useState(basisMap["BLACK"]);
    const [ensemble, setEnsemble] = useState([]);
    const [enactedPoints, setEnactedPoints] = useState([]);
    const [selectedPointsAll, setSelectedPointsAll] = useState({"recombination_of_districts-0": []});

    const [plots, setPlots] = useState(props.plots);

    console.log(plots);
    console.log(plots[0].min)
    const handleBasisSelect = (key) => {
        setBasis(basisMap[key]);
    }

    useEffect(() =>{
        console.log("triggered");
        // plots.sort();
        // setPlots(plots);

        // var districtStats = [];
        // for (let i = 0; i < plots.length; i++) {

        // }
        
        // console.log(props.stateName);
        // fetch("/api/ensemble?state="+props.stateName)
        //   .then(res => res.json())
        //   .then(function(data) {
        //       var districtStats = [];
        //       for (let i = 0; i < data.length; i++) {
        //           districtStats.push({
        //             label: "District " + data[i][0],
        //             y: [parseFloat(data[i][1]), // min
        //                 parseFloat(data[i][2]), // q1
        //                 parseFloat(data[i][3]), // q3
        //                 parseFloat(data[i][4]), // max
        //                 parseFloat(data[i][5]), // med (q2)
        //             ]
        //         });
        //       }
        //       districtStats.sort((a,b) => (a.label > b.label) ? 1 : -1);
        //       setEnsemble(districtStats);
        //   });
        //   fetch("/api/plotPoints")
        //   .then(res => res.json())
        //   .then(function(data) {
        //     console.log(data);
        //     setEnactedPoints(Object.values(data["enacted"]));
        //     data["recombination_of_districts-0"] = [];
        //     console.log(data);
        //     setSelectedPointsAll(data);
        //   });
    }, []);

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
            gridThickness: 0
        },
        // data: [
        //     {
        //     type : "boxAndWhisker",
        //     dataPoints : ensemble
        //     },
        //     {
        //         type: "scatter",
        //         name: "Enacted",
        //         color: "steelblue",
        //         markerType: "circle",
        //         toolTipContent: "<span style=\"color:steelblue\">{labelPoints}</span>: {y}",
        //         showInLegend: true,
        //         dataPoints: getData(enactedPoints, "Enacted")
        //     },
        //     {
        //         type: "scatter",
        //         name: "Proposed",
        //         color: "tomato",
        //         markerType: "circle",
        //         toolTipContent: "<span style=\"color:tomato\">{labelPoints}</span>: {y}",
        //         showInLegend: true,
        //         dataPoints: getData(Object.values(selectedPointsAll["recombination_of_districts-"+props.plan]),"Proposed")
        //     }
        // ],
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
