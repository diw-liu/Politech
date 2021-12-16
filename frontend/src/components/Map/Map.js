import DeckGL from '@deck.gl/react';
import { GeoJsonLayer } from '@deck.gl/layers';
import { StaticMap } from 'react-map-gl';
import React, { useEffect, useState } from 'react';

const MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoiZGl3bGl1IiwiYSI6ImNrdHQ1M3hjdTFuZWcycXBxczAyYnRud3EifQ.WUk5cILDRQQNOaae60Hb9A";
// const districts = DISTRICT

const Map = (props) => {
    // Set your mapbox access token here
    var display = [];

    // let state = fetch("/api/MD").then(function(data) {return data.json()});
    // let precintData = state.then(function(data) {console.log(data[1]); });
    // if (props.layers.length != undefined){
    // for (const variable in props.layers) {
    //   console.log(variable);
    //   console.log(props.layers[variable]);
    // }
    // console.log("enacted info")
    // console.log(props.enactedInfo)
    // console.log("enacted geo")
    // console.log(props.enactedGeo);
    // console.log(Object.keys(props.layers).length == 0);
    // console.log(props.flag)
    
    // }

    if (Object.keys(props.layers).length == 0){
      if(Object.keys(props.all).length != 0){
        // console.log(props.all.features)
        for(var i = 0; i < props.all.features.length; i++){
          var name = props.all.features[i].properties.state
          display.push(new GeoJsonLayer({
                      id: name,
                      data : props.all.features[i],
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
                        // console.log(info.layer.id)
                        props.setStateName(info.layer.id)
                        props.showClick(info.layer.id)
                      }
            }));
          }
      }
    }
    else{
      let map = {};

      const districtMap = (Id) => {
        if(Id in map ){
          return map[Id]
        }
        var temp = [Math.random()*256, Math.random()*256, Math.random()*256];
        map[Id] = temp;
        return temp
      }

      // console.log(props.enactedGeo)
      // console.log(props.flag)
      // console.log(props.layers['district'])
      // delete Object.assign(props.enacted, {['geometry']: props.enacted['geometryString'] })['geometryString'];

      var districtColor = new GeoJsonLayer({
        id: 'districtColor',
        data : props.enactedGeo,
        filled: true,
        stroked: false,
        extruded: false,
        getFillColor: d => districtMap(d.properties.cd),
        pointType: 'circle',
      }) 

      // console.log(props.planInfo);
      if (!('id' in props.planInfo)) {
        districtColor = new GeoJsonLayer({
        id: 'districtColor',
        data : props.planInfo,
        filled: true,
        stroked: false,
        extruded: false,
        getFillColor: d => districtMap(d.properties.cd),
        pointType: 'circle',
      })
      }

      display.push(districtColor)
      
      // Finetune the lines with function
      for (const variable in props.layers) {
        if(props.flag[variable]){
          display.push(new GeoJsonLayer({
                  id: variable,
                  data : props.layers[variable],
                  stroked: true,
                  filled: false,
                  extruded: false,
                  pointType: 'circle',
                  lineWidthScale: 20,
                  lineWidthMinPixels: 2,
                  getLineColor: [228,220,220],
                  getLineWidth: 1,
                }));
          // console.log(variable);
          // console.log(props.layers[variable]);
        }

      }

      // for(var i = 0; i < props.layers.length; i++){
      //   if(props.flag[i]){
      //     display.push(new GeoJsonLayer({
      //       id: "Sublayer",
      //       data : props.layers[i],
      //       stroked: true,
      //       filled: false,
      //       extruded: false,
      //       pointType: 'circle',
      //       lineWidthScale: 20,
      //       lineWidthMinPixels: 2,
      //       getLineColor: [228,220,220],
      //       getLineWidth: 1,
      //     }));
      //   }
      
    }
   
    // let temp = []
    // props.layers.forEach((element,index) => {
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
          layers={display}
          // getTooltip={({object}) => object && (object.properties.name || object.properties.station)}
        >
          <StaticMap mapboxApiAccessToken={MAPBOX_ACCESS_TOKEN} />
        </DeckGL>
    )
}
export default Map;