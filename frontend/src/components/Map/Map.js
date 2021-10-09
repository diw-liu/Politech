import DeckGL from '@deck.gl/react';
import { GeoJsonLayer } from '@deck.gl/layers';
import { StaticMap } from 'react-map-gl';
import React, { useEffect, useState } from 'react';

const MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoiZGl3bGl1IiwiYSI6ImNrdHQ1M3hjdTFuZWcycXBxczAyYnRud3EifQ.WUk5cILDRQQNOaae60Hb9A";
// const districts = DISTRICT

const Map = (props) => {
    // Set your mapbox access token here
    var base = [];

    for(var i = 0; i < props.all.length; i++){
      var name = props.all[i].features[0].properties.STUSPS20
      base.push(new GeoJsonLayer({
                  id: name,
                  data : props.all[i],
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
                    console.log(info.layer.id)
                    props.showClick(info.layer.id)
                  }
        }));
    }
    
    const layers = props.showInfo ?  props.state
                                  :  base

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