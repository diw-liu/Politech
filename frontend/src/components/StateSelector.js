import '../css/StateSelector.css';
import React, { useState } from 'react';

import { Dropdown, DropdownButton, } from 'react-bootstrap';
import { INITIAL_VIEW_STATE } from './ViewState'
import { NAMES } from './HomeScreen'

const defaultOption = 'Select a state...'

const StateSelector = (props) => {
  const [selected, setSelected] = useState(defaultOption);

  const handleStateSelect = (key) => {
    props.setStateName(key)
    props.showClick(key)
    setSelected(NAMES[key])
  }
  const handleReset = () => {
    props.setShowInfo(false)
    props.setView(INITIAL_VIEW_STATE)
    setSelected(defaultOption)
  }

  return (
    <div className="StateSelector">
      <DropdownButton
          size="sm"
          variant="outline-secondary"
          id="dropdown-basic-button"
          title={selected}
          onSelect={handleStateSelect}
      >
        <>
        <Dropdown.Item 
          key={"Maryland"}
          eventKey={[0]}
        >
          Maryland
        </Dropdown.Item>
        <Dropdown.Item 
          key={"Michigan"}
          eventKey={1}
        >
          Michigan
        </Dropdown.Item>
        <Dropdown.Item 
          key={"Pennsylvania"}
          eventKey={2}
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