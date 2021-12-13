import React, { useState } from 'react';

const LayerSelector = (props) => {
    const handleOnChange = (index) =>{
        let newArr = [...props.flag]; 
        newArr[index] = !newArr[index]; 
        props.setFlag(newArr);
    }
    return(
        <div className="LayerSelector">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1" defaultChecked={props.flag[0]} onChange={() => handleOnChange(0)}></input>
                <label class="form-check-label" for="inlineCheckbox1">District</label>
            </div>
            {/* <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option2" defaultChecked={props.flag[1]}></input>
                <label class="form-check-label" for="inlineCheckbox2">County</label>
            </div> */}
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="inlineCheckbox3" value="option3" defaultChecked={props.flag[1]} onChange={() => handleOnChange(1)}></input>
                <label class="form-check-label" for="inlineCheckbox3">Precinct</label>
            </div>
        </div>
    )
}

export default LayerSelector;