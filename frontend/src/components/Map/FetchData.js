// async function getJson(url) {
//     let data = await fetch(url).then(function(data) {return data.json()});
//     return data;
// }

// async function getDistrict(promise) {
//     let district = await promise.then(function(data) {return data[0]});
//     return district;
// }

// async function getPrecinct(promise) {
//     let precinct = await promise.then(function(data) {return data[1]});
//     return precinct;
// }

// async function getDistrict2(promise, i) {
//     let district = await promise.then(function(data) {return data[i]});
//     return district;
// }



// let marryland =  getJson("/api/MD");
// let michigan = getJson("/api/MI");
// let pennsylvania = getJson("/api/PA");


// export const districtMD = getDistrict(marryland)
// export const precintMD = getPrecinct(marryland);
// export const districtMI = getDistrict(michigan)
// export const precintMI = getPrecinct(michigan);
// export const districtPA = getDistrict(pennsylvania)
// export const precintPA = getPrecinct(pennsylvania);