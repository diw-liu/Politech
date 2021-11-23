// import React from 'react';
// import Plot from 'react-plotly.js';
// import CanvasJS from 'canvasjs';
import React from 'react';
// import Select from '@material-ui/core/Select';
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";
import {CanvasJSChart} from 'canvasjs-react-charts'

const BoxAndWhisker = (props) => {

    function getData(data,k){
        console.log(k);
        let dataPoints = [];
        for(let i = 0;i<Object.keys(data).length; i++)
        {
            dataPoints.push({
                x: i, y: data[i], Val: String(k)
            });
        }
        console.log(dataPoints);
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
            dataPoints : [
                { label: "District 1", y: [0.11817578264644298,0.26186892352349705,0.3386538427870268,
                    0.6741148364125281,
                    
                    0.29505267592963547,
                    
                    
                  ] },
                { label: "District 2", y: [0.08476481960644593,0.1262213115949838,0.290387048713353,
                    0.38839907717361805,
                    
                    0.16081823701153206,
                    
                    
                  ] },
                { label: "District 3", y: [0.10578194040560444,0.18447863413665883,0.2778861367967428,
                    0.5977182618664714,
                    
                    0.21759889965105186,
                    
                    
                  ] },
                { label: "District 4", y: [0.11683488248146463,0.13576081582448257,0.29623645916239105,
                    0.49726258185969197,
                    
                    0.22573645502172918,
                    
                    
                  ] },
                { label: "District 5", y: [0.26763716470254456,0.3295003629276405,0.5111528874334734,
                    0.7475031908281111,
                    
                    0.41378445950357023,
                    
                    
                  ] },
                { label: "District 6", y: [0.12321153236553216,0.15196967547939397,0.41283657985687505,
                    0.6321892051757764,
                    
                    0.19852911311258448,
                    
                    
                  ] },
                { label: "District 7", y: [
                    0.16997944509553742,
                    0.1980824914150978,
                    0.47548915182993384,
                    0.7606464698085185,
                    0.2980050180549707,
                  ] },
                { label: "District 8", y: [
                    0.12326764598675037,
                    0.15040965325116498,
                    0.31256013765221347,
                    0.43521349010115223,
                    0.1987639640156862,
                  ] }],
            },
            {
                type: "scatter",
                name: "Enacted",
                color: "#F15278",
                markerType: "circle",
                toolTipContent: "<span style=\"color:#C0504E\">{Val}</span>: {y}",
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
