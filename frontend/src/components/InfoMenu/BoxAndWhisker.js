// import React from 'react';
// import Plot from 'react-plotly.js';
// import CanvasJS from 'canvasjs';
import React from 'react';
import {CanvasJSChart} from 'canvasjs-react-charts'

const BoxAndWhisker = (props) => {
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
                { label: "Oven", y: [4, 6, 8, 9, 7] },
                { label: "Microwawe", y: [5, 6, 7, 8, 6.5] },
                { label: "PC & Peripherals", y: [6, 8, 10, 11, 9.5] },
                { label: "Air Conditioner", y: [8, 9, 13, 14, 10.5] },
                { label: "Dishwasher", y: [5, 7, 9, 12, 7.5] },
                { label: "Shaver", y: [2, 3, 4, 6, 3.5] },
                { label: "Electric Kettle", y: [4, 6, 8, 9, 7] },
                { label: "Fridge", y: [8, 9, 12, 13, 11] }],
            },
            // {
            //     type: "scatter",
            //     name: "Enacted",
            //     color: "#F15278",
            //     markerType: "cross",
            //     toolTipContent: "<span style=\"color:#C0504E\">{Val}</span>: {y}",
            //     showInLegend: true,
            //     dataPoints: 
            // }
        ],
    }

    return (
            <>
                <CanvasJSChart options={options} />
            </>
    );
}
export default BoxAndWhisker;
