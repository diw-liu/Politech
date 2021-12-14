import React, { useState } from 'react';

const LayerSelector = (props) => {

    const handleOnChange = (level) =>{
        let newObj = {...props.flag}; 
        newObj[level] = !newObj[level]; 
        props.setFlag(newObj);
    }

    return(
        <div className="LayerSelector">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1" defaultChecked={props.flag["district"]} onChange={() => handleOnChange('district')}></input>
                <label class="form-check-label" for="inlineCheckbox1">District</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option2" defaultChecked={props.flag['county']} onChange={() => handleOnChange('county')}></input>
                <label class="form-check-label" for="inlineCheckbox2">County</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="inlineCheckbox3" value="option3" defaultChecked={props.flag['precinct']} onChange={() => handleOnChange('precinct')}></input>
                <label class="form-check-label" for="inlineCheckbox3">Precinct</label>
            </div>
        </div>
    )
}

export default LayerSelector;