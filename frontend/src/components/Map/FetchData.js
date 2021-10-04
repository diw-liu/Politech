async function getJson(url) {
    let data = await fetch(url).then(function(data) {return data.json()});
    return data;
}

async function getDistrict(promise) {
    let district = await promise.then(function(data) {return data[0]});
    return district;
}

async function getPrecinct(promise) {
    let precinct = await promise.then(function(data) {return data[1]});
    return precinct;
}

async function getDistrict2(promise, i) {
    let district = await promise.then(function(data) {return data[i]});
    return district;
}


// function foo(callback) {
//     httpRequest = new XMLHttpRequest();
//     httpRequest.onreadystatechange = function () {
//         if (httpRequest.readyState === 4) { // request is done
//             if (httpRequest.status === 200) { // successfully
//                 callback(httpRequest.responseText); // we're calling our method
//             }
//         }
//     };
//     httpRequest.open('GET', "/echo/json");
//     httpRequest.send();
// }

// foo(function (result) {
//     document.body.innerHTML = result;
// });

const all = getJson("/api/all")


let marryland =  getJson("/api/0");
let michigan = getJson("/api/1");
let pennsylvania = getJson("/api/2");




// export const districtMD = getDistrict(marryland);
// export const precintMD = getPrecinct(marryland);
// export const districtMI = getDistrict(michigan);
// export const precintMI = getPrecinct(michigan);
// export const districtPA = getDistrict(pennsylvania);
// export const precintPA = getPrecinct(pennsylvania);

export const districtMD = getDistrict2(all,0)
export const precintMD = getPrecinct(marryland);
export const districtMI = getDistrict2(all,1)
export const precintMI = getPrecinct(michigan);
export const districtPA = getDistrict2(all,2)
export const precintPA = getPrecinct(pennsylvania);