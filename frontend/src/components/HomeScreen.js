import DeckGL from '@deck.gl/react';
import {GoogleMapsOverlay} from '@deck.gl/google-maps';
import {LineLayer} from '@deck.gl/layers';
import {StaticMap} from 'react-map-gl';

const HomeScreen = (props) =>{
    const [viewport, setViewport] = useState({
        latitude: 37.7577,
        longitude: -122.4376,
        zoom: 8
    });
    
    
    const data = [
        {sourcePosition: [-122.41669, 37.7853], targetPosition: [-122.41669, 37.781]}
    ];
    
    // const layers = [
    //     new LineLayer({id: 'line-layer', data})
    // ];

    return (
        <DeckGL
            initialViewState={INITIAL_VIEW_STATE}
            controller={true}
            layers={layers}
        >
            <StaticMap mapboxApiAccessToken={MAPBOX_ACCESS_TOKEN} />
        </DeckGL>
        
    )
}
export default HomeScreen;