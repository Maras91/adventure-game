import useRequest from './../../common/UseRequest';
import '../../../App.css';
import React, {useState,useEffect} from 'react';
import _ from 'lodash';

let dungeonMap = [[]]

export default function DungeonMap({updateFunction}) {
    const [isLoading, setIsLoading] = useState(true)
    const [dungeonView, setDungeonView] = useState()

    async function createRandomMap() {
        const requestOptions = {
                            method: "POST",
                            headers: {'Content-Type': 'application/json'},
                            body: JSON.stringify()
                        }
       try{
           let response = await fetch("/createRandomMap", requestOptions)
       } catch (e) {
           console.log(e)
       }
       sendRequest()
    }

    async function sendRequest() {
        setIsLoading(true)
        const requestOptions = {
                    method: "POST",
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify()
                }
        try{
            let response = await fetch("/getMap", requestOptions)
            let data = await response.json()
            setDungeonView(_.cloneDeep(data))
            setDungeonMap(data)
            setIsLoading(false)
        } catch (e) {
            console.log(e)
            setIsLoading(false)
        }
    }
    function setDungeonMap(dungeonView){
         dungeonMap = dungeonView.dungeonMap
         dungeonMap[dungeonView.playerPosition.axisY][dungeonView.playerPosition.axisX] = "PLAYER"
         console.log("dungeonMap: ", dungeonMap)
    }
    async function playerMove(direction) {
        const requestOptions = {
                    method: "POST",
                    headers: {'Content-Type': 'application/json'},
                    body: direction
                }
        try{
            let response = await fetch("/playerMove", requestOptions)
            let data = await response.json()
        } catch (e) {
            console.log(e)
        }
        sendRequest()
     }

     function checkMonster(currentAdventure) {
        console.log("currentAdventure: ", currentAdventure)
        if (currentAdventure.hasOwnProperty('monsterName') && currentAdventure.monsterName !== null) {
            console.log("checkMonster return true")
            return true
        }
        console.log("checkMonster return false")
        return false
     }

    useEffect(() => {
      createRandomMap()
    },[])

    function setDirections() {

    }

    function setColor(field) {
        if(field === "WALL") {
            return "white"
        }
        if(field === "TUNNEL") {
            return "yellow"
        }
        if(field === "PLAYER") {
            return "lime"
        }
        return "black"
    }

    async function attackMonster(monsterName) {
        await fetch('/fight',
        {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: monsterName
        }
        )
        updateFunction()
        sendRequest()
    }

    console.log("dungeon view object: ",dungeonView)
    return(
    <div className = "row">
        <div className="col-md-4">
            <h3>Dungeon map</h3>
            {   isLoading ? (<div>Loading...</div>) :
                (
                    <div style={{width: 50*dungeonMap[0].length}}>
                        {
                            dungeonMap.map((row) => {
                               return (
                                   <div className = "board-row" >
                                        {
                                            row.map( (field) => {
                                                return (<><button className = "square" style={{backgroundColor: setColor(field)}}/></>)
                                            })
                                        }
                                   </div>
                               )
                            })
                        }
                    </div>
                )
            }
        </div>
        <div className="col-md-8">
            <div className= "w-75 h-50 overflow-auto border" disabled="true">
              Welcome to the dungeon! The map is on your left side. Green square represents YOU.<br/>
              Here you can see logs what is currently happened. Good luck!!! <br/>
              There is nothing special in this room. You can go on.


            </div>
            <div className = "text-center">
                {isLoading ? (<></>): (
                    <>
                        <p>Your actions: </p>
                        <button type = "button" onClick={() =>playerMove("up")} disabled={!dungeonView.availableActions.goUp}>GO UP</button>
                        <button type = "button" onClick={() =>playerMove("left")} disabled={!dungeonView.availableActions.goLeft}>GO LEFT</button>
                        <button type = "button" onClick={() =>playerMove("right")} disabled={!dungeonView.availableActions.goRight}>GO RIGHT</button>
                        <button type = "button" onClick={() =>playerMove("down")} disabled={!dungeonView.availableActions.goDown}>GO DOWN</button>
                        <button type = "button" onClick={() =>attackMonster(dungeonView.currentAdventure.monsterName)} style={{display: checkMonster(dungeonView.currentAdventure) ? "inline" : "none"}}>Attack {dungeonView.currentAdventure.monsterName}</button>
                        <button type = "button">RUN AWAY!</button>
                    </>
                )}
            </div>
        </div>
    </div>
    )

}