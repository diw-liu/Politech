import { GeoJsonLayer } from '@deck.gl/layers';

let precintMD = require('../data/maryland.json');
let districtMD = require('../data/MDdistrict.json');

let precintMI = require('../data/michigan.json');
let districtMI = require('../data/MIdistrict.json');

let precintPA = require('../data/pennsylvania.json');
let districtPA = require('../data/PAdistrict.json');

export const Districts = [districtMD, districtMI, districtPA]

const Precint = [precintMD, precintMI, precintPA]

export const showState = (index) =>{
    var map = {};
    var precintData = Precint[index]
    var districtData = Districts[index]

    console.log("Shows")
    console.log(precintData)
    console.log(districtData)

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
    })

    return [ precint, district ]
}