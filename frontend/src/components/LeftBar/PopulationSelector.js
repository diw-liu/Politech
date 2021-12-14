import React, { useState } from 'react';

const PopulationSelector = (props) => {
    // const handleOnChange = (index) =>{
    //     let newArr = [...props.flag]; 
    //     newArr[index] = !newArr[index]; 
    //     props.setFlag(newArr);
    // }
    return(
        <div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="Default" ></input>
                <label class="form-check-label" for="inlineCheckbox1">Population</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option2" ></input>
                <label class="form-check-label" for="inlineCheckbox2">VAP</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="inlineCheckbox3" value="option3" disabled></input>
                <label class="form-check-label" for="inlineCheckbox3">CVAP</label>
            </div>
        </div>
    )
}

export default PopulationSelector;