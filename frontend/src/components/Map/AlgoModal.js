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
    
    const handleStop = () =>{
        props.stop()
    }

    const handleClose = () => {
        props.setShowModal(false);
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
                                    
                                    <CanvasJSChart options={options}/>
                                </div>}
                        </div>
                        <div className="modal-footer">
                            {(Object.keys(props.algoGraph).length == 0) ? null : <div>Iterations: {iterations}</div>}
                            <hr/>
                        </div>
                        <div className="modal-footer">
                            <button style={{marginTop:'16px'}} className='btn btn-warning' onClick={handlePause} > Pause </button>
                            <button style={{marginTop:'16px'}} className='btn btn-success' onClick={handleResume} > Resume </button>
                            <button style={{marginTop:'16px'}} className='btn btn-danger' onClick={handleStop} > Stop </button>
                            <button style={{marginTop:'16px'}} className='btn btn-info' onClick={handleClose} > Close </button>
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