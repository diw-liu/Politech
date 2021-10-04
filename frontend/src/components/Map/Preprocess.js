import { GeoJsonLayer } from '@deck.gl/layers';
import { precintMD, districtMD, precintMI, districtMI, precintPA, districtPA } from './FetchData'

// let precintMD = require('../../data/maryland.json');
// let districtMD = require('../../data/MDdistricts.json');

// let precintMI = require('../../data/michigan.json');

// let districtMI = require('../../data/MIdistrict.json');

// let precintPA = require('../../data/pennsylvania.json');
// let districtPA = require('../../data/PAdistrict.json');


export const MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoiZGl3bGl1IiwiYSI6ImNrdHQ1M3hjdTFuZWcycXBxczAyYnRud3EifQ.WUk5cILDRQQNOaae60Hb9A";



export const DISTRICT = [districtMD, districtMI, districtPA]

export const PRECINCT = [precintMD, precintMI, precintPA]

export const showState = (index) =>{

    var map = {};
    var precintData = PRECINCT[index]
    var districtData = DISTRICT[index]
    
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
    // var precint = []

    // precintData["features"].foreach(element => {
    //     precint.push(new GeoJsonLayer({
    //         id: 'geojson-layer',
    //         data : element,
    //         pickable: true,
    //         stroked: true,
    //         filled: true,
    //         extruded: false,
    //         pointType: 'circle',
    //         lineWidthScale: 20,
    //         lineWidthMinPixels: 2,
    //         getFillColor: d => districtMap(d.properties.districtID),
    //         getLineColor: [228,220,220],
    //         getLineWidth: 1,
    //         onClick: (info) => { 
    //             console.log(info)
    //             // props.showClick(info.layer.id-1)
    //         }
    //     }))
    // })

    // const precint = []

    // precintData["features"].forEach(element => {
    //   precint.push(new GeoJsonLayer({
    //     id: 'geojson-layer',
    //     data: element,
    //     pickable: true,
    //     stroked: true,
    //     filled: true,
    //     extruded: false,
    //     pointType: 'circle',
    //     lineWidthScale: 20,
    //     lineWidthMinPixels: 2,
    //     getFillColor: d => districtMap(d.properties.districtID),
    //     getLineColor: [228,220,220],
    //     getLineWidth: 1,
    //     onClick: (info) => { 
    //         console.log(info)
    //         // props.showClick(info.layer.id-1)
    //     }
    //   }))
    // })

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