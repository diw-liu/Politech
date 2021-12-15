import React, { useState } from 'react';
import {CanvasJSChart} from 'canvasjs-react-charts'

import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';
import '../../css/LeftBar.css';

const AlgoModal = (props) => {
    
    const handlePause = () =>{
        props.pause()
    }

    const handleResume = () =>{
        props.resume()
    }
    
    const handleStop = () =>{
        props.stop()
    }

    const options = {
        animationEnabled: true,
        theme: "light2",
        title:{
            text: "Most Popular Social Networking Sites"
        },
        axisX: {
            title: "Social Network"
        },
        axisY: {
            title: "Monthly Active Users",
            includeZero: true,
        },
        data: [{
            type: "column",
            dataPoints: [
                { label: "Facebook", y:  2200000000 },
                { y:  1800000000, label: "YouTube" },
                { y:  800000000, label: "Instagram" },
                { y:  563000000, label: "Qzone" },
                { y:  376000000, label: "Weibo" },
                { y:  336000000, label: "Twitter" },
                { y:  330000000, label: "Reddit" }
            ]
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
                            <CanvasJSChart options={options}/>
                        </div>
                        <div className="modal-footer">
                            <div>Iterations: </div>
                            <hr/>
                        </div>
                        <div className="modal-footer">
                            <button style={{marginTop:'16px'}} className='btn btn-warning' onClick={handlePause} > Pause </button>
                            <button style={{marginTop:'16px'}} className='btn btn-success' onClick={handleResume} > Resume </button>
                            <button style={{marginTop:'16px'}} className='btn btn-danger' onClick={handleStop} > Stop </button>
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