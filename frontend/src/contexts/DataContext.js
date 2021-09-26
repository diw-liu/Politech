import React, {createContext, useReducer, useState, useMemo, useContext} from "react";
import DataReducer from './DataReducer'

const initialState = {
    stateSelected: null,
    dropdownTitle: "Select State"
}

// export const DataContext = React.createContext({
//     state: initialState,
//     dispatch: () => null
// });
const DataContext = React.createContext(initialState);

const DataProvider = ({children}) => {
    // const [state, dispatch] = React.useReducer(DataReducer, initialState);
    const [state, setState] = useState(initialState);
    const value = useMemo(() => [state, setState], [state])
    return (
        <DataContext.Provider value={/*[state, dispatch]*/value}>
            {children}
        </DataContext.Provider>
    )
}

function useData() {
    return useContext(DataContext);
}

export { useData, DataContext }
export default DataProvider