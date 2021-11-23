import React, { useEffect, Component, useState} from 'react';
// import Select from '@material-ui/core/Select';
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";
import {CanvasJSChart} from 'canvasjs-react-charts'

const BoxAndWhisker = (props) => {

    const [ensemble, setEnsemble] = useState([]);
    const [enactedPoints, setEnactedPoints] = useState([]);
    const [selectedPoints, setSelectedPoints] = useState([]);

    useEffect(() =>{
        console.log(props.stateName);
        fetch("/api/ensemble?state="+props.stateName)
          .then(res => res.json())
          .then(function(data) {
              var districtStats = [];
              for (let i = 0; i < data.length; i++) {
                  districtStats.push({
                    label: "District " + data[i][0],
                    y: [parseFloat(data[i][1]), // min
                        parseFloat(data[i][2]), // q1
                        parseFloat(data[i][3]), // q3
                        parseFloat(data[i][4]), // max
                        parseFloat(data[i][5]), // med (q2)
                    ]
                });
              }
              districtStats.sort((a,b) => (a.label > b.label) ? 1 : -1);
              setEnsemble(districtStats);
          });
      }, []);

    function getData(data,k){
        let dataPoints = [];
        for (let i = 0; i < Object.keys(data).length; i++)
        {
            dataPoints.push({
                x: i, labelPoint: String(k), y: data[i]
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
            title : "African American Population",
            gridThickness: 0
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
                toolTipContent: "<span style=\"color:steelblue\">{labelPoint}</span>: {y}",
                showInLegend: true,
                dataPoints: getData([
                    0.11817578264644298, 
                     0.3555277246215432,
                     0.23051393121659627,
                     0.49726258185969197,
                     0.38176139928260616,
                     0.1384291987326869,
                     0.48658170211435536,
                     0.12326764598675037
                   ], "Enacted")
            }
        ],
    }

    return (
            <>
                <CanvasJSChart options={options} />
                {/* <VariableSelectionDropdown
				// setName={setVarName}
				currentName={"African American"}
                listOfNames={"Test", "Test 2"}
			/> */}
            </>
    );
}
export default BoxAndWhisker;
