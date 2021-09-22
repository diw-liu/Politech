import DeckGL from '@deck.gl/react';
// import {GoogleMapsOverlay} from '@deck.gl/google-maps';
import {LineLayer} from '@deck.gl/layers';
import {StaticMap} from 'react-map-gl';


const HomeScreen = (props) =>{
   // Set your mapbox access token here
    const MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoiZGl3bGl1IiwiYSI6ImNrdHQ1M3hjdTFuZWcycXBxczAyYnRud3EifQ.WUk5cILDRQQNOaae60Hb9A";
    
    // Viewport settings
    const INITIAL_VIEW_STATE = {
        longitude: -98.35,
        latitude: 39.50,
        zoom: 3,
        pitch: 0,
        bearing: 0
    };

    // Data to be used by the LineLayer
    const data = [
        {sourcePosition: [-122.41669, 37.7853], targetPosition: [-122.41669, 37.781]}
    ];

    const layers = [
        new LineLayer({id: 'line-layer', data})
    ];

    return (
        <DeckGL
          initialViewState={INITIAL_VIEW_STATE}
          controller={true}
          layers={layers}
        >
          <StaticMap mapboxApiAccessToken={MAPBOX_ACCESS_TOKEN} />
        </DeckGL>
      );
}
export default HomeScreen;