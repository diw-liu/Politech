import DeckGL from '@deck.gl/react';
import { GeoJsonLayer } from '@deck.gl/layers';
import { StaticMap } from 'react-map-gl';
import { useState } from 'react';
import { Districts, showState } from './preprocess'

const districts = Districts

const INITIAL_VIEW_STATE = {
  longitude: -98.35,
  latitude: 39.50,
  zoom: 4,
  // Following coordinates are for my own record
  // longitude:-76.44,
  // latitude: 39,
  // zoom: 7.5,
  pitch: 0,
  bearing: 0
};

const Map = (props) => {
    // Set your mapbox access token here
    
    const MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoiZGl3bGl1IiwiYSI6ImNrdHQ1M3hjdTFuZWcycXBxczAyYnRud3EifQ.WUk5cILDRQQNOaae60Hb9A";
    const [showDistrict, setShowDistrict] = useState(false)
    const [state, setState] = useState([])
    var base = [];

    for(var i = 0; i < districts.length; i++){
      base.push(new GeoJsonLayer({
        id: i+1,
        data : districts[i],
        pickable: true,
        stroked: false,
        filled: true,
        extruded: true,
        pointType: 'circle',
        lineWidthScale: 20,
        lineWidthMinPixels: 2,
        getFillColor: [160, 160, 180, 200],
        getLineColor: [80, 80, 80],
        getPointRadius: 100,
        getLineWidth: 1,
        getElevation: 30,
        onClick: (info) => { 
          setShowDistrict(!showDistrict) 
          setState(showState(info.layer.id-1))
        }
      }));
    }
    
    const layers = showDistrict ?  state
                                :  base

    return(
        <DeckGL
          initialViewState={INITIAL_VIEW_STATE}
          controller={true}
          layers={layers}
          // getTooltip={({object}) => object && (object.properties.name || object.properties.station)}
        >
          <StaticMap mapboxApiAccessToken={MAPBOX_ACCESS_TOKEN} />
        </DeckGL>
    )
}
export default Map;