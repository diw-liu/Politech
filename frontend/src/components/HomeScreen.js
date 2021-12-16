import React, { useEffect, useState } from 'react';
import InfoMenu from './InfoMenu/InfoMenu';
import LeftBar from './LeftBar/LeftBar';
import Map from './Map/Map';
import StateSelector from './Map/StateSelector';
import LayerSelector from './Map/LayerSelector';
import AlgoModal from './Map/AlgoModal';
import DownloadButton from './Map/DownloadButton';

import { showState } from './Map/Preprocess'
import { INITIAL_VIEW_STATE, getView } from './Map/ViewState'
import '../css/StateSelector.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';


// export const NAMES = ['Maryland', 'Michigan', 'Pennsylvania']

const HomeScreen = (props) =>{

  const [showInfo, setShowInfo] = useState(false)

  const [plan, setPlan] = useState(0)
  const [enactedInfo, setEnactedInfo] = useState({})
  const [planInfo,setPlanInfo] = useState({})
  const [enactedGeo, setEnactedGeo] = useState({})

  const [all, setAll] = useState({})
  const [layers, setLayers] = useState({})
  const [flag, setFlag] = useState({'district':true,'county':false,'precinct':true})
 
  const [districtings, setDistrictings] = useState([])
  const [measure, setMeasure] = useState({})
  const [election, setElection] = useState({})
  const [population, setPopulation] = useState({})
  const [vap, setVap]  = useState({})
  const [plots, setPlots] = useState({})

  var temp = Object.keys(measure).length == 0 ? 0 : (measure.populationEquality.toFixed(2))
  const [popEq, setPopEq] = useState(temp)
  temp = Object.keys(measure).length == 0 ? 0 : (measure.opportunityDistricts.toFixed(2))
  const [oppoDist, setOppoDist] = useState(temp)
  const [popType, setPopType] = useState(0)

  const [stateName, setStateName] = useState('')

  const [view, setView] = useState(INITIAL_VIEW_STATE)
  const [showModal, setShowModal] = useState(false) // HERE IS WHERE I CHANGED THE FALSE TO TRUE
  const [summaryBoolean, setSummaryBoolean] = useState(false)
  
  const [gen, setGen] = useState(false);
  const [algoGraph, setAlgoGraph] = useState({});
  

  // const [saved, setSaved] = useState(false);
  // const [algoModal, setAlgoModal] = useState(false);
  // const [summaryFetch, setSummaryFetch] = useState(false);
  console.log(planInfo)
  console.log(enactedInfo)

  useEffect(() =>{
    fetch("/api/all",{
      method: 'GET',
      headers:{'Content-Type': 'application/x-www-form-urlencoded'}
    })
      .then(res => res.json()) 
      .then(message => {
        setAll(message)
      })
  },[])

  const getState = async (state) =>{
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

  const showClick = async (name) => {
    //showState(name)
    setGen(true)

    const data = await getState(name)
    console.log(data)
    // const district = await getDistrict()
    // const county = await getCounty()
    // const precinct = await getPrecinct()
    const [district, county, precinct] = await Promise.all([
      getDistrict(),
      getCounty(),
      getPrecinct()
    ]);

    setPlan(data.enacted.id)
    setPlanInfo(data.enacted)
    setEnactedInfo(data.enacted)
    setElection(data.election)
    setPopulation(data.population)
    setVap(data.vap)
    setDistrictings(data.districtings)
    setPlots(data.plots)
    setMeasure(data.enacted.measures)

    setLayers({'district':district,'county':county,'precinct':precinct})
    setEnactedGeo(district)

    console.log(data.districting)
    setGen(false)

    // console.log(district)
    // console.log(county)
    // console.log(precinct)
    // console.log(layers)
    // console.log(enactedInfo)
    // console.log(population)
    // setState(data)
    // const temp = await getDistrict(name)
    // console.log(temp)
    setShowInfo(true) 
    // // showState(state,flag)
    // setStateName(NAMES[name])
    setView(getView(name))
  }

  const Loading = async () => {
    console.log(showModal)
    console.log("Loading")
    setShowModal(true)
    setSummaryBoolean(true);
  }
  // fetching start before setShowModal is true
  useEffect(async () => {
    console.log("Set")
    if(showModal){
      await getPlanTesting()
      getStartTesting()
      getSummaryTesting()
    }
  }, [showModal])

  // Pop == goal
  // Oppo = higher
  // Lower = 0 
  // age = radio 
  const getStartTesting = async () =>{
    var url = "/job/start?goal="+popEq+"&lower=0&higher="+oppoDist+"&age="+popType;
    console.log(url)
    return fetch("/job/start?goal=0.07&lower=0&higher=7&age=0", {
      method: 'POST',
      headers: {
        'Content-type': 'application/json'
      }
    })
    .catch((error)=>{
      console.error(error)
      console.log("Does this even work?")
  })
  }

  const getSummaryTesting = async () =>{
    const response = await fetch("/job/summary");
    if (!response.ok) {
      const message = `An error has occured: ${response.status}`;
      console.log(message);
    }
    console.log(response)
    const contentType = response.headers.get("content-type");
    console.log(contentType)
    if (contentType && contentType.indexOf("application/json") !== -1) {
      console.log("ss")
      const myJson = await response.json();
      setAlgoGraph(myJson); 
    } else {
      console.log("se")
      setAlgoGraph({})
    }
  }
  // Lagging behind for the result and unable to terminate before, necessary 
  useEffect(async () => {
    console.log("inside algo")
    console.log(algoGraph)
    if(showModal && summaryBoolean){
      setSummaryBoolean(true);
      setTimeout(getSummaryTesting , 2000);
    }
  }, [algoGraph])

  const pause = async () => {
    await getPauseTesting()
  }

  const resume = async () => {
    await getResumeTesting()
  }

  const stop = async () => {
    // controller.abort()
    setSummaryBoolean(false)
    await getStopTesting()
    // setShowModal(false)
    
  }

  const close = () => {
    setSummaryBoolean(false);
    setShowModal(false);
  }

  const getPlanTesting = async () =>{
    console.log("call")
    var url = "/api/selectplan?id="+plan
    const data = await fetch(url)
                  .then(data => data.json())
    console.log(data)
    // var planDistrict = data;
    // for(const i in planDistrict.districts){
    //   console.log(planDistrict.districts[i])
    //   planDistrict.districts[i].geometry = planDistrict.districts[i].geometryString
    //   delete planDistrict.districts[i].geometryString
    //   console.log(planDistrict.districts[i])
    // }
    setPlanInfo(data)
  }

  const dummyTesting = async (id) =>{
    console.log("call d")
    var url = "/api/selectplan?id="+id
    const data = await fetch(url)
                  .then(data => data.json())
    console.log(data)
    // var planDistrict = data;
    // for(const i in planDistrict.districts){
    //   console.log(planDistrict.districts[i])
    //   planDistrict.districts[i].geometry = planDistrict.districts[i].geometryString
    //   delete planDistrict.districts[i].geometryString
    //   console.log(planDistrict.districts[i])
    // }
    setPlanInfo(data)
  }

  const getPauseTesting = async() => {
    //if (Object.keys(algoGraph).length != 0){
      return fetch("/job/pause")
              //.then(data => data.json())
    //} 
  }

  // var parse = require('wellknown');
  //   fetch("api/geometryString")
  //       .then(data => console.log(data))
  //       .then(data => console.log(parse(data)))

  const getResumeTesting = async() => {
    //if (Object.keys(algoGraph).length != 0){
      return fetch("/job/resume")
              //.then(data => data.json())
    //} 
  }

  const getStopTesting = async() => {
    return fetch("/job/stop")
  }
  
  return (
    <div >
      <StateSelector stateName={stateName} showClick={showClick}
        setView={setView} setShowInfo={setShowInfo} 
        setLayers={setLayers}  setStateName={setStateName}
        setEnactedGeo={setEnactedGeo} setEnactedInfo={setEnactedInfo}
        />   
      {
        showInfo && (  
            <div>
                <DownloadButton measure={measure}/>
            </div>)
      }
      { showInfo && (
        <div>
          <InfoMenu enactedInfo={enactedInfo} districtings={districtings} stateName={stateName} 
                plan={plan} setPlan={setPlan} popType={popType} plots={plots} setPlots={setPlots}
                getPlan={getPlanTesting} setMeasure={setMeasure} dummyTesting={dummyTesting}/>
          <LeftBar stateName={stateName} plan={plan} enactedInfo={enactedInfo} measure={measure}
                  popType={popType} setPopType={setPopType} popEq={popEq} setPopEq={setPopEq}
                  oppoDist={oppoDist} setOppoDist={setOppoDist} loading={Loading}
                  />
          <LayerSelector flag={flag} setFlag={setFlag}/>  
        </div>)
      } 

      {
        gen != true ? <div></div>
                    : <div className="justify-content-center spinner-border spinner-border-sm text-info reset " style={{width: "15rem",height: "15rem",position: 'absolute', left: '40%', top: '40%' }}></div>
      }

      {
        showModal != true ? <div></div>
                          : <AlgoModal algoGraph={algoGraph} pause={pause} resume={resume} stop={stop} popEq={popEq}
                          setShowModal={setShowModal} close={close} summaryBoolean={summaryBoolean} setSummaryBoolean={setSummaryBoolean}/>
      }

      <Map flag={flag} layers={layers} planInfo={planInfo} enactedGeo={enactedGeo} all={all}
          view={view} showClick={showClick} showInfo={showInfo} setStateName={setStateName} stateName={stateName}
          />
    </div>
    );
}
export default HomeScreen;