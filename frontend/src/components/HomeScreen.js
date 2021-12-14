import React, { useEffect, useState } from 'react';
import InfoMenu from './InfoMenu/InfoMenu';
import LeftBar from './LeftBar/LeftBar';
import Map from './Map/Map';
import StateSelector from './Map/StateSelector';
import LayerSelector from './Map/LayerSelector';

import { showState } from './Map/Preprocess'
import { INITIAL_VIEW_STATE, getView } from './Map/ViewState'
import '../css/StateSelector.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

export const NAMES = ['Maryland', 'Michigan', 'Pennsylvania']

const HomeScreen = (props) =>{

  const [showInfo, setShowInfo] = useState(true)

  const [enactedInfo, setEnactedInfo] = useState({})
  const [enactedGeo, setEnactedGeo] = useState({})
  const [all, setAll] = useState([])
  const [layers, setLayers] = useState({})
  const [flag, setFlag] = useState({'district':true,'county':false,'precinct':true})
 
  const [districtings, setDistrictings] = useState([])
  const [election, setElection] = useState({})
  const [population, setPopulation] = useState({})
  const [vap, setVap]  = useState({})

  const [stateName, setStateName] = useState('')

  const [view, setView] = useState(INITIAL_VIEW_STATE)
  const [plan, setPlan] = useState(0)
  const [gen, setGen] = useState(false);
  const [saved, setSaved] = useState(false);


  
  // console.log(state)
  
  useEffect(() =>{
    if (localStorage.getItem("All") == null){
      fetch("/api/all",{
        method: 'GET',
        headers:{'Content-Type': 'application/x-www-form-urlencoded'}
      })
        .then(res => res.json()) 
        .then(message => {
          localStorage.setItem("All",JSON.stringify(message));
          var result = message.map(x => JSON.parse(x));
          setAll(result)
        })
    }else{
      var result = JSON.parse(localStorage.getItem("All")).map(x => JSON.parse(x));
      setAll(result)
    }
  },[])

  const getLayers = async (id) =>{
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
    return fetch("/api/" + name)
            .then(data => data.json())
  }
  const getState = async (id) =>{
    let state = "MD"
    // switch(id){
    //   case "0":
    //       name = "MD";
    //       break;
    //   case "1":
    //       name = "MI";
    //       break;
    //   case "2":
    //       name = "PA";
    //       break;
    //   default:
    // }
    return fetch("/api/state?" + new URLSearchParams({
              name: state
            }))
            .then(data => data.json())
  }

  const getDistrict = async () =>{
    return fetch("/api/districtgeometry")
            .then(data => data.json())
  }
  const getCounty = async () =>{
    return fetch("/api/countygeometry")
            .then(data => data.json())
  }
  const getPrecinct = async () =>{
    return fetch("/api/precinctgeometry")
            .then(data => data.json())
  }

  const showClick = async (name) =>{
    //showState(name)
    setGen(true)

    const data = await getState(name)
    console.log(data)

    setElection(data.election)
    setPopulation(data.population)
    setVap(data.vap)
    setDistrictings(data.districtings)
    setEnactedInfo(data.enacted)

    const district = await getDistrict()
    const county = await getCounty()
    const precinct = await getPrecinct()

    setLayers({'district':district,'county':county,'precinct':precinct})
    setEnactedGeo(district)

    console.log(data.districting)

    setGen(false)
    console.log(district)
    console.log(county)
    console.log(precinct)
    console.log(layers)
    console.log(enactedInfo)
    console.log(population)
    // setState(data)
    // const temp = await getDistrict(name)
    // console.log(temp)
    // setShowInfo(true) 
    // // showState(state,flag)
    // setStateName(NAMES[name])
    // setView(getView(name))
  }


  return (
    <div >
      <StateSelector stateName={stateName} showClick={showClick}
        setView={setView} setShowInfo={setShowInfo} 
        setLayers={setLayers}  setStateName={setStateName}
        />   
      
      { showInfo && (
        <div>
          <InfoMenu enactedInfo={enactedInfo} saved={saved} stateName={stateName} plan={plan} setPlan={setPlan} setSaved={setSaved}/>
          <LeftBar stateName={stateName} plan={plan} setGen={setGen} 
                layers={layers} setLayers={setLayers} setSaved={setSaved} saved={saved}/>
          <LayerSelector flag={flag} setFlag={setFlag}/>  
        </div>)
      } 
      {
        gen != true ? <div></div>
                    : <div className="spinner-border spinner-border-sm text-info reset" style={{width: "15rem",height: "15rem",position: 'absolute', left: '40%', top: '40%' }}></div>
      }
      <Map flag={flag} layers={layers} enactedInfo={enactedInfo} enactedGeo={enactedGeo} all={all}
          view={view} showClick={showClick} showInfo={showInfo}
          />
    </div>
    );
}
export default HomeScreen;