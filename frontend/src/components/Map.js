import DeckGL from '@deck.gl/react';
import {GeoJsonLayer} from '@deck.gl/layers';
import {PolygonLayer} from '@deck.gl/layers';
import {StaticMap} from 'react-map-gl';
import { useState } from 'react';

let preccintData = require('../maryland.json');
let districtData = require('../bad.json');

const Map = (props) => {
    // Set your mapbox access token here
    const MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoiZGl3bGl1IiwiYSI6ImNrdHQ1M3hjdTFuZWcycXBxczAyYnRud3EifQ.WUk5cILDRQQNOaae60Hb9A";
    var map = {};
    
    const districtMap = (Id) => {
      if(Id in map ){
        return map[Id]
      }
      var temp = [Math.random()*256, Math.random()*256, Math.random()*256];
      map[Id] = temp;
      return temp
    }

    const INITIAL_VIEW_STATE = {
        longitude: -98.35,
        latitude: 39.50,
        zoom: 4,
        pitch: 0,
        bearing: 0
    };
    
    const base = new GeoJsonLayer({
      id: 'geojson-layer',
      data : districtData,
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
      getElevation: 30
    });

    const district = new GeoJsonLayer({
      id: 'geojson-layer',
      data : districtData,
      stroked: true,
      filled: false,
      extruded: false,
      lineWidthScale: 20,
      lineWidthMinPixels: 3,
      getFillColor: d => districtMap(d.properties.districtID),
      getLineColor: [0,0,0],
    })

    const preccint = new GeoJsonLayer({
      id: 'geojson-layer',
      data : preccintData,
      pickable: true,
      stroked: true,
      filled: true,
      extruded: false,
      pointType: 'circle',
      lineWidthScale: 20,
      lineWidthMinPixels: 2,
      getFillColor: d => districtMap(d.properties.districtID),
      getLineColor: [228,220,220],
      getLineWidth: 1,
    })

    // console.log(districtData)
    // console.log(preccintData)

    const layers = [
      preccint,
      district
      
    ]

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