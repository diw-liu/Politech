import React, { useEffect, useState } from 'react';
import InfoMenu from './InfoMenu/InfoMenu';
import LeftBar from './LeftBar/LeftBar';
import Map from './Map/Map';
import StateSelector from './Map/StateSelector';
import LayerSelector from './Map/LayerSelector';
import AlgoModal from './Map/AlgoModal';

import { showState } from './Map/Preprocess'
import { INITIAL_VIEW_STATE, getView } from './Map/ViewState'
import '../css/StateSelector.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';


export const NAMES = ['Maryland', 'Michigan', 'Pennsylvania']

const HomeScreen = (props) =>{

  const [showInfo, setShowInfo] = useState(false)

  const [enactedInfo, setEnactedInfo] = useState({})
  const [enactedGeo, setEnactedGeo] = useState({})
  const [all, setAll] = useState({})
  const [layers, setLayers] = useState({})
  const [flag, setFlag] = useState({'district':true,'county':false,'precinct':true})
 
  const [districtings, setDistrictings] = useState([])
  const [election, setElection] = useState({})
  const [population, setPopulation] = useState({})
  const [vap, setVap]  = useState({})
  const [plots, setPlots] = useState({})

  const [stateName, setStateName] = useState('')

  const [view, setView] = useState(INITIAL_VIEW_STATE)
  const [plan, setPlan] = useState(0)
  const [showModal, setShowModal] = useState(false) // HERE IS WHERE I CHANGED THE FALSE TO TRUE
  
  const [gen, setGen] = useState(false);
  // const [saved, setSaved] = useState(false);
  // const [algoModal, setAlgoModal] = useState(false);
  // const [summaryFetch, setSummaryFetch] = useState(false);
  const [algoGraph, setAlgoGraph] = useState({});

  const [popType, setPopType] = useState(0)

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

    setEnactedInfo(data.enacted)
    setElection(data.election)
    setPopulation(data.population)
    setVap(data.vap)
    setDistrictings(data.districtings)
    setPlots(data.plots)

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
    setShowInfo(true) 
    // // showState(state,flag)
    // setStateName(NAMES[name])
    setView(getView(name))
  }

  const Loading = async () => {
    console.log(showModal)
    console.log("Loading")
    setShowModal(true)
    
    // await getStateTesting()
    
    // setTimeout(function(){
    //   getStartTesting()
    // }, 3000);
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

  const getStartTesting = async () =>{
    return fetch("/job/start?goal=0.09&lower=0&higher=7&age=0", {
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
    if(showModal){
      setTimeout(getSummaryTesting , 2000);
    }
  }, [algoGraph])
  // useEffect(async () =>{
  //   if (showModal == false){
  //     await getPlanTesting()
  //     getStartTesting()
  //     getSummaryTesting()
  //   }
    
  // }, [showModal]);

  // const getStateTesting = async () =>{
  //   return fetch("/api/state?name=MD")
  //           .then(data => data.json())
  // }
  const pause = async () => {
    await getPauseTesting()
  }

  const resume = async () => {
    await getResumeTesting()
  }

  const stop = async () => {
    // controller.abort()
    await getStopTesting()
    setShowModal(false)
  }

  const getPlanTesting = async () =>{
    return fetch("/api/selectplan?id=24PL0")
            .then(data => data.json())
  }

  const getPauseTesting = async() => {
    //if (Object.keys(algoGraph).length != 0){
      return fetch("/job/pause")
              //.then(data => data.json())
    //} 
  }

  const getResumeTesting = async() => {
    //if (Object.keys(algoGraph).length != 0){
      return fetch("/job/resume")
              //.then(data => data.json())
    //} 
  }

  const getStopTesting = async() => {
    return fetch("/job/stop")
  }

 

    // try {

    // }
    // try {
    //   // console.log(algoGraph)
    //   // console.log()
    //   const response = await fetch("/job/summary");
    //   // console.log("summary try")
    //   // console.log(response)
    //   if (response.status === 200) {
    //       const myJson = await response.json(); //extract JSON from the http response
    //       // console.log("IC")
    //       // console.log(myJson);  
    //       console.log(algoGraph)
    //       console.log()
    //       setAlgoGraph(myJson);            
    //   } else {
    //       console.log("not a 200 Summary");
    //   }
    // } catch (err) {
    //     console.log(err);
    // } finally {
    //   console.log(response)
    //   if(showModal){
    //     setTimeout(getSummaryTesting , 10000);
    //   }
    // }
  // }
  // useEffect(async () => {
  //   console.log("Set")
  //   if(showModal){
  //     await getPlanTesting()
  //     getStartTesting()
  //     await getSummaryTesting()
  //   }
  // }, [algoGraph])
  
  return (
    <div >
      <StateSelector stateName={stateName} showClick={showClick}
        setView={setView} setShowInfo={setShowInfo} 
        setLayers={setLayers}  setStateName={setStateName}
        setEnactedGeo={setEnactedGeo} setEnactedInfo={setEnactedInfo}
        />   
      
      { showInfo && (
        <div>
          <InfoMenu enactedInfo={enactedInfo} districtings={districtings} stateName={stateName} plan={plan} setPlan={setPlan} popType={popType} plots={plots} setPlots={setPlots}/>
          <LeftBar stateName={stateName} plan={plan} loading={Loading}
                setGen={setGen} showModal={showModal} setShowModal={setShowModal} enactedInfo={enactedInfo} 
                layers={layers} setLayers={setLayers} popType={popType} setPopType={setPopType}/>
          <LayerSelector flag={flag} setFlag={setFlag}/>  
        </div>)
      } 

      {
        gen != true ? <div></div>
                    : <div className="justify-content-center spinner-border spinner-border-sm text-info reset " style={{width: "15rem",height: "15rem",position: 'absolute', left: '40%', top: '40%' }}></div>
      }

      {
        showModal != true ? <div></div>
                          : <AlgoModal algoGraph={algoGraph} pause={pause} resume={resume} stop={stop} setShowModal={setShowModal}/>
      }

      <Map flag={flag} layers={layers} enactedInfo={enactedInfo} enactedGeo={enactedGeo} all={all}
          view={view} showClick={showClick} showInfo={showInfo}
          />
    </div>
    );
}
export default HomeScreen;