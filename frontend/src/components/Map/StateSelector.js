import '../../css/StateSelector.css';
import React, { useState } from 'react';

import { Dropdown, DropdownButton, } from 'react-bootstrap';
import { INITIAL_VIEW_STATE } from './ViewState'

const defaultOption = 'Select a state...'

const StateSelector = (props) => {

  const handleStateSelect = (key) => {
    console.log(key)
    // props.setStateName(key)
    props.showClick(key)
    // setSelected(NAMES[key])
  }

  const handleReset = () => {
    // props.setStateName("")
    props.setShowInfo(false)
    props.setView(INITIAL_VIEW_STATE)
    props.setLayers({})
    props.setEnactedInfo({})
    props.setEnactedGeo({})
    // setSelected(defaultOption)
  }

  return (
    <div className="StateSelector">
      <DropdownButton
          variant="outline-secondary"
          id="dropdown-basic-button"
          title={props.stateName ? props.stateName : defaultOption}
          onSelect={handleStateSelect}
      >
        <>
        <Dropdown.Item 
          key={"MD"}
          eventKey={"MD"}
        >
          Maryland
        </Dropdown.Item>
        <Dropdown.Item 
          key={"MI"}
          eventKey={"MI"}
        >
          Michigan
        </Dropdown.Item>
        <Dropdown.Item 
          key={"PA"}
          eventKey={"PA"}
        >
          Pennsylvania
        </Dropdown.Item>
        </>
      </DropdownButton>
      <button type="button" className="reset btn btn-primary" onClick={handleReset}>Reset</button>
    </div>
  );
}
export default StateSelector;