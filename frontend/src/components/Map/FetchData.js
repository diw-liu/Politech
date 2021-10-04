async function getJson(url) {
    let response = await fetch(url);
    let data = await response.json()
    return data;
}

async function main(url){
    let d = await getJson(url);
    return d;
}

async function getDistrict(promise) {
    let district = await promise.then(function(data) {return data[0]});
    return district;
}

async function getPrecinct(promise) {
    let precinct = await promise.then(function(data) {return data[1]});
    return precinct;
}

const marryland = main("/api/0");
const michigan = main("/api/1");
const pennsylvania = main("/api/2");

export const districtMD = getDistrict(marryland);
export const precintMD = getPrecinct(marryland);
export const districtMI = getDistrict(michigan);
export const precintMI = getPrecinct(michigan);
export const districtPA = getDistrict(pennsylvania);
export const precintPA = getPrecinct(pennsylvania);

      