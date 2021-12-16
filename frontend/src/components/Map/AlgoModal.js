import React, { useEffect, useMemo, useState } from 'react';
import {CanvasJSChart} from 'canvasjs-react-charts'

import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';
import '../../css/LeftBar.css';

const AlgoModal = (props) => {

    const [algoGraph, setAlgoGraph] = useState(props.algoGraph)
    const [graphInfo, setGraphInfo] = useState(props.algoGraph["districtPopulations"]);
    const [iterations, setIterations] = useState(props.algoGraph["iterations"]);
    const [popEqList, setPopEqList] = useState([props.algoGraph["currentPopEq"]]);
    const [populations, setPopulations] = useState([]);
    const [resultFlag, setResultFlag] = useState(false);
    const [resultInfo, setResultInfo] = useState({})
    // console.log(Object.keys(props.algoGraph).length == 0);
    // console.log(props.algoGraph["districtPopulations"]);
    // console.log(props);
    // console.log(graphInfo);
    // console.log(iterations);
    // console.log(popEqList);
    // setGraphInfo(props.algoGraph["districtPopulations"])
    // setIterations(props.algoGraph["iterations"])
    // setPopEqList(props.algoGraph["currentPopEq"])

    useEffect(() => {
        setAlgoGraph(props.algoGraph)
        setGraphInfo(props.algoGraph["districtPopulations"])
        setIterations(props.algoGraph["iterations"])
        setPopEqList(props.algoGraph["currentPopEq"])

        var populations1 = [];
        if (graphInfo!= null && Object.keys(graphInfo).length != 0) {
            for (let i = 1; i < Object.keys(graphInfo).length + 1; i++) {
            populations1.push({
                label: "District " + i,
                y: graphInfo[i]
                })
            }
        }
        
        // console.log(populations1)
        setPopulations(populations1);
        // console.log(populations)

    }, [props.algoGraph, graphInfo, iterations, popEqList]);
    
    const handlePause = () =>{
        props.pause()
    }

    const handleResume = () =>{
        props.resume()
    }
    
    const handleStop = async () =>{
        console.log("oooooo")
        await props.stop()
        var okay = await fetch("/job/result").then(data => data.json())
        console.log(okay)
        setResultInfo(okay)
        setResultFlag(true)
    }

    const handleClose = async () => {
        props.close();
    }

    const options = {
        animationEnabled: true,
        theme: "light2",
        title:{
            text: "District Populations"
        },
        axisX: {
            title: "Districts"
        },
        axisY: {
            title: "Population",
            includeZero: true,
        },
        data: [{
            type: "column",
            dataPoints: populations
        }]
    }

    const data = {
    // labels: ["A", "B", "C", "D", "E", "F", "G"],
    datasets: [{
        label: 'My First Dataset',
        data: [0.11, 0.1, 0.12, 0.097, 0.09001, 0.089, 0.1],
        fill: true,
        borderColor: 'rgb(75, 192, 192)',
        tension: 0.1
        }]
    };

    const test = {
        type: 'line',
        data: data,
    };
    
    return(
        <div>
            <div className='algoModal'>
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                        <h4 className="modal-title">Algorithm Status </h4>
                        </div>  
                        <div className="modal-body">
                            {(Object.keys(props.algoGraph).length == 0) ? <div> Waiting for algorithm setup to complete... </div> :
                            <div>

                                <div>
                                    { props.summaryBoolean ? <CanvasJSChart options={options}/> : <></>}
                                </div>
                                
                                <div>
                                    {/* { !resultFlag ? <></> :
                                        <div>
                                            <div>seconds: {resultInfo["seconds"]}</div>
                                            <div>cyclesRan: {resultInfo["cyclesRan"]}</div>
                                            <div>numPrecinctsChanged: {resultInfo["numPrecinctsChanged"]}</div>
                                            <div>popeq: {resultInfo["popeq"]}</div>
                                        </div>
                                    } */}
                                        <div>seconds: {resultFlag ? resultInfo["seconds"] : "In Progress"}</div>
                                        <div>cyclesRan: {resultFlag ? resultInfo["cyclesRan"]: "In Progress"}</div>
                                        <div>numPrecinctsChanged: {resultFlag ? resultInfo["numPrecinctsChanged"]: "In Progress"}</div>
                                        <div>popeq: {resultFlag ? resultInfo["popeq"]: "In Progress"}</div>
                                </div>
                            </div>
                            }
                        </div>
                        <div className="modal-footer">
                            {(Object.keys(props.algoGraph).length == 0) ? null 
                                                                        : 
                                                                        <div>
                                                                            <div>Iterations: {iterations}</div>
                                                                            {/* <div>Current Score: {popEqList} </div> */}
                                                                        </div>
                                                                        }
                            <hr/>
                        </div>
                        <div className="modal-footer">
                            <button style={{marginTop:'16px'}} className='btn btn-warning' onClick={handlePause} > Pause </button>
                            <button style={{marginTop:'16px'}} className='btn btn-success' onClick={handleResume} > Resume </button>
                            <button style={{marginTop:'16px'}} className='btn btn-danger' onClick={handleStop} > Stop </button>
                            <button id="close" style={{marginTop:'16px'}} className='btn btn-info'  onClick={handleClose} > Close </button>
                        </div>
                    </div>
                </div>
            </div>
                {/* // <div className={showAlgoButton ? {} : { display: 'none' }}>
                //                     <button style={{marginTop:'16px'}} className='btn btn-primary' onClick={pause} > Pause </button>
                //                     <button style={{marginTop:'16px'}} className='btn btn-primary' onClick={resume} > Resume </button>
                //                     <button style={{marginTop:'16px'}} className='btn btn-primary' onClick={stop} > Stop </button>
                //                  </div */}
            
        </div>
    )
}

export default AlgoModal;