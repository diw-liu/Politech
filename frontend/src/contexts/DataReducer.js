const DataReducer = (state, action) => {
    switch (action.type) {
        case 'SELECT_STATE':
            // console.log(action.stateSelected);
            return {
                ...state,
                stateSelected: action.stateSelected,
                dropdownTitle: action.stateSelected
            }
        default:
            return state
    }
}

export default DataReducer;