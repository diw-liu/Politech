import DeckGL from '@deck.gl/react';
import { GeoJsonLayer } from '@deck.gl/layers';
import { StaticMap } from 'react-map-gl';
import React, { useEffect, useState } from 'react';

const MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoiZGl3bGl1IiwiYSI6ImNrdHQ1M3hjdTFuZWcycXBxczAyYnRud3EifQ.WUk5cILDRQQNOaae60Hb9A";
// const districts = DISTRICT

const Map = (props) => {
    // Set your mapbox access token here
    var layers = [];
    console.log(props.state)

    if (props.state.length == 0){
      for(var i = 0; i < props.all.length; i++){
        var name = props.all[i].features[0].properties.STUSPS20
        layers.push(new GeoJsonLayer({
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
    }else{
      let map = {};

      const districtMap = (Id) => {
        if(Id in map ){
          return map[Id]
        }
        var temp = [Math.random()*256, Math.random()*256, Math.random()*256];
        map[Id] = temp;
        return temp
      }

      const districtColor = new GeoJsonLayer({
        id: 'districtColor',
        data : props.state[0],
        filled: true,
        extruded: false,
        getFillColor: d => districtMap(d.properties.CD116FP),
      })

      layers.push(districtColor)

      for(var i = 0; i < props.state.length; i++){
        if(props.flag[i]){
          layers.push(new GeoJsonLayer({
            id: "Sublayer",
            data : props.state[i],
            stroked: true,
            filled: false,
            extruded: false,
            pointType: 'circle',
            lineWidthScale: 20,
            lineWidthMinPixels: 2,
            getLineColor: [228,220,220],
            getLineWidth: 1,
          }));
        }
      }
    }
   
    // let temp = []
    // props.state.forEach((element,index) => {
    //     if(props.flag[index]){
    //       temp.push(element)
    //     }
    // });
    // console.log(temp)
    // const layers = props.showInfo ?  temp
    //                               :  base

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