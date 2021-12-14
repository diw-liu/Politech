import React, { useState } from 'react';

const SliderSection = (props) => {

    const handleOnChange = (value) =>{
        props.setPopType(value)
    }

    return(
        <div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" defaultChecked={props.popType == 0} onChange={() => handleOnChange(0)}></input>
                <label class="form-check-label" for="flexRadioDefault1">
                    Population
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" defaultChecked={props.popType == 1} onChange={() => handleOnChange(1)}></input>
                <label class="form-check-label" for="flexRadioDefault2">
                    Voting Population
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" disabled></input>
                <label class="form-check-label" for="flexRadioDefault2">
                    Citizen Voting Population
                </label>
            </div>
        </div>
    )
}

export default SliderSection;