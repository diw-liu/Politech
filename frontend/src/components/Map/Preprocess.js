import { GeoJsonLayer } from '@deck.gl/layers';
// let precintMD = require('../../data/maryland.json');
// let districtMD = require('../../data/MDdistricts.json');

// let precintMI = require('../../data/michigan.json');

// let districtMI = require('../../data/MIdistrict.json');

// let precintPA = require('../../data/pennsylvania.json');
// let districtPA = require('../../data/PAdistrict.json');

export const showState = (id) =>{
    let name = "MD"
    switch(id){
        case "0":
            name = "MD";
            break;
        case "1":
            name = "MI";
            break;
        case "2":
            name = "PA";
            break;
        default:
    }
    let map = {};
    let state = fetch("/api/" + name).then(function(data) {return data.json()});
    let districtData = state.then(function(data) {return data[0]});
    let precintData = state.then(function(data) {return data[1]});
    
    const districtMap = (Id) => {
        if(Id in map ){
          return map[Id]
        }
        var temp = [Math.random()*256, Math.random()*256, Math.random()*256];
        map[Id] = temp;
        return temp
    }

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

    const precint = new GeoJsonLayer({
        id: 'geojson-layer',
        data : precintData,
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
        onClick: (info) => { 
            console.log(info)
            // props.showClick(info.layer.id-1)
        }
    })

    return [ precint, district ]
}