async function getJson(url) {
    let response = await fetch(url);
    let data = await response.json()
    return data;
}
async function main(url){
    var d = await getJson(url);
    return d;
}

export const districtMD = main("/api/0");
export const precintMD = main("/api/1");
export const districtMI = main("/api/2");
export const precintMI = main("/api/3");
export const districtPA = main("/api/4");
export const precintPA = main("/api/5");

      