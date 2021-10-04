import DeckGL from '@deck.gl/react';
import { GeoJsonLayer } from '@deck.gl/layers';
import { StaticMap } from 'react-map-gl';
import { DISTRICT, MAPBOX_ACCESS_TOKEN } from './Preprocess'

const districts = DISTRICT
  
const Map = (props) => {
    // Set your mapbox access token here
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
          props.showClick(info.layer.id-1)
        }
      }));
    }
    
    const layers = props.showInfo ? props.state : base;

    return(
      <DeckGL
        initialViewState={props.view}
        controller={true}
        layers={layers}
        // getTooltip={({object}) => object && (object.properties.name || object.properties.station)}
      >
        <StaticMap mapboxApiAccessToken={MAPBOX_ACCESS_TOKEN} />
      </DeckGL>
  )

}
export default Map;