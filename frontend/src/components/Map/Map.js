import DeckGL from '@deck.gl/react';
import { GeoJsonLayer } from '@deck.gl/layers';
import { StaticMap } from 'react-map-gl';
import { MAPBOX_ACCESS_TOKEN } from './Preprocess'
import React, { useEffect, useState } from 'react';

// const districts = DISTRICT

const Map = (props) => {
    // Set your mapbox access token here
    var base = [];

    for(var i = 0; i < props.all.length; i++){
      base.push(new GeoJsonLayer({
                  id: i+1,
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
                    props.showClick(info.layer.id-1)
                  }
        }));
    }
    // useEffect(() => {
    //   fetch("/api/all")
    //     .then(res => res.json())   
    //     .then(message => {
    //       
    //         console.log(message[i])
    //         
    //       }
    //     })
    // },[])
    

    // var temp = await getState("/api/all")
    // console.log(temp)
    
    
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