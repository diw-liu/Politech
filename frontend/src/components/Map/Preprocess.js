import { GeoJsonLayer } from '@deck.gl/layers';
// let precintMD = require('../../data/maryland.json');
// let districtMD = require('../../data/MDdistricts.json');

// let precintMI = require('../../data/michigan.json');

// let districtMI = require('../../data/MIdistrict.json');

// let precintPA = require('../../data/pennsylvania.json');
// let districtPA = require('../../data/PAdistrict.json');
const getJSON = async (name) => {
    return fetch("/api/" + name)
            .then(data => data.json())
            .then(data => data)
}

export const showState = async (state, flag) =>{
    console.log(state)
    // let name = "MD"
    // switch(id){
    //     case "0":
    //         name = "MD";
    //         break;
    //     case "1":
    //         name = "MI";
    //         break;
    //     case "2":
    //         name = "PA";
    //         break;
    //     default:
    // }
    
    // let map = {};
    // Work with some issue
    // let array = await getJSON(name)
    // console.log(array)
    // let array = fetch("/api/" + name)
    //                 .then(data => data.json())
    //                 .then(data => {return data})
    // let state = fetch("/api/" + name).then(data => data.json());
    // let districtData = state.then(function(data) {console.log(data[1]);return data[0]});
    // let precintData = state.then(function(data) {return data[1]});
    // let countyData = state.then(function(data) {return data[2]});
    // fetch("/api/" + name)
    //     .then(data => data.json())
    //     .then(data => array = data)
        
    // console.log(array)
    // console.log(data)
    // // console.log(precintData)

    
    
    // const districtMap = (Id) => {
    //     if(Id in map ){
    //       return map[Id]
    //     }
    //     var temp = [Math.random()*256, Math.random()*256, Math.random()*256];
    //     map[Id] = temp;
    //     return temp
    // }

    // const district = new GeoJsonLayer({
    //     id: 'district',
    //     data : districtData,
    //     stroked: true,
    //     filled: true,
    //     extruded: false,
    //     lineWidthScale: 20,
    //     lineWidthMinPixels: 3,
    //     getFillColor: d => districtMap(d.properties.CD116FP),
    //     getLineColor: [0,0,0],
    // })

    // // const county = new GeoJsonLayer({
    // //     id: 'geojson-layer',
    // //     data : countyData,
    // //     pickable: true,
    // //     stroked: true,
    // //     filled: true,
    // //     extruded: false,
    // //     pointType: 'circle',
    // //     lineWidthScale: 20,
    // //     lineWidthMinPixels: 2,
    // //     getFillColor: d => districtMap(d.properties.districtID),
    // //     getLineColor: [228,220,220],
    // //     getLineWidth: 1,
    // //     // onClick: (info) => { 
    // //     //     console.log(info)
    // //     //     // props.showClick(info.layer.id-1)
    // //     // }
    // // })

    // const precint = new GeoJsonLayer({
    //     id: 'precint',
    //     data : precintData,
    //     pickable: true,
    //     stroked: true,
    //     filled: false,
    //     extruded: false,
    //     pointType: 'circle',
    //     lineWidthScale: 20,
    //     lineWidthMinPixels: 2,
    //     getLineColor: [228,220,220],
    //     getLineWidth: 1,
    //     // onClick: (info) => { 
    //     //     console.log(info)
    //     //     // props.showClick(info.layer.id-1)
    //     // }
    // })

    // return [ district, precint ]
}

export const redistrict = (object, id) =>{
    console.log("okay")
    console.log(object)
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
        data : object,
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
    console.log(district)
    return [ precint, district ]
}