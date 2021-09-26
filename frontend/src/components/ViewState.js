export const INITIAL_VIEW_STATE = {
    longitude: -98.35,
    latitude: 39.50,
    zoom: 4,
    pitch: 0,
    bearing: 0
}

export const MD_STATE = {
    longitude:-76.44,
    latitude: 39,
    zoom: 7.5,
    pitch: 0,
    bearing: 0
}
export const PA_STATE = {
    longitude:-77.19,
    latitude: 40.83,
    zoom: 7.5,
    pitch: 0,
    bearing: 0
}
export const MI_STATE = {
    longitude:-85.60,
    latitude: 44.31,
    zoom: 7.5,
    pitch: 0,
    bearing: 0
}

export const VIEW_STATES = [MD_STATE, MI_STATE, PA_STATE]

export const getView = (index) =>{
    return VIEW_STATES[index]
}