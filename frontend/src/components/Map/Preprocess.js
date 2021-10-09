import { GeoJsonLayer } from '@deck.gl/layers';

export const showState = async (name) =>{
    console.log(name)
    fetch("/api/"+name,{
        method: 'GET',
        headers:{'Content-Type': 'application/x-www-form-urlencoded'}
      }) 
      .then(res => res.json()) 
      .then(message => console.log(message))
//     var map = {};

//     const temp = await getState("/api/0");
//     console.log(temp)

//     const districtData = temp[0]
//     const precintData = temp[1]
//     console.log(districtData)

//     const districtMap = (Id) => {
//         if(Id in map ){
//           return map[Id]
//         }
//         var temp = [Math.random()*256, Math.random()*256, Math.random()*256];
//         map[Id] = temp;
//         return temp
//     }

//     const district = new GeoJsonLayer({
//         id: 'geojson-layer',
//         data : districtData,
//         stroked: true,
//         filled: false,
//         extruded: false,
//         lineWidthScale: 20,
//         lineWidthMinPixels: 3,
//         getFillColor: d => districtMap(d.properties.districtID),
//         getLineColor: [0,0,0],
//     })
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

    // const precint = new GeoJsonLayer({
    //     id: 'geojson-layer',
    //     data : precintData,
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
    // })

    // return [ precint, district ]
    // return [district]
}