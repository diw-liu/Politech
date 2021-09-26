import '../css/StateSelector.css';
import React, { useState } from 'react';

import { Dropdown, DropdownButton, } from 'react-bootstrap';
import { INITIAL_VIEW_STATE } from './ViewState'

const StateSelector = (props) => {
  const [selected, setSelected] = useState();

  const handleStateSelect = (key) => {
    props.showClick(key)
    setSelected(key)
  }
  const handleReset = () => {
    props.setShowInfo(false)
    props.setView(INITIAL_VIEW_STATE)
  }

  return (
    <>
      <DropdownButton
            className="StateSelector"
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
      <button type="button" className="btn btn-primary z-index" onClick={handleReset}>Reset</button>
    </>
  );
}
export default StateSelector;