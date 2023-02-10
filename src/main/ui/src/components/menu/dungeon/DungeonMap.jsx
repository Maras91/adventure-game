import useRequest from './../../common/UseRequest';
import '../../../App.css';
import React, {useState,useEffect} from 'react';
import _ from 'lodash';

export default function DungeonMap({updateFunction}) {
    const [isLoading, setIsLoading] = useState(true)
    const [mapInArray, setMapInArray] = useState()
    const [moveDirection, setMoveDirection] = useState(
        {
            up: false,
            left: false,
            right: false,
            down: false
        }
    )
    const [isMonster, setIsMonster] = useState(false)

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
            setMapInArray(_.cloneDeep(data))
            setIsLoading(false)
        } catch (e) {
            console.log(e)
            setIsLoading(false)
        }
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
        setButtons()
    }

    function setButtons() {
        let xAxis
        let yAxis
        [yAxis,xAxis] = getPlayersPosition()
        setIsMonster(checkMonster(yAxis,xAxis))
        setMoveDirections(yAxis,xAxis)
    }
    function getPlayersPosition() {
        let xAxis= -1
        let yAxis= -1
        for (let y=0; y<mapInArray.length; y++) {
            console.log("mapInArray: ",mapInArray.length)
            for(let x=0; x<mapInArray[y].length; x++) {
                if (mapInArray[y][x].fieldType === "PLAYER") {
                    xAxis= x
                    yAxis= y
                }
            }
        }
        return [yAxis,xAxis]
    }

    function setMoveDirections(yAxis,xAxis) {
        let directions = _.cloneDeep(moveDirection)
        directions.up = yAxis > 0 && mapInArray[yAxis-1][xAxis].fieldType !== "WALL"
        directions.left = xAxis > 0 && mapInArray[yAxis][xAxis-1].fieldType !== "WALL"
        directions.right = xAxis+1 < mapInArray[yAxis].length && mapInArray[yAxis][xAxis+1].fieldType !== "WALL"
        directions.down = yAxis+1 < mapInArray.length && mapInArray[yAxis+1][xAxis].fieldType !== "WALL"
        setMoveDirection(directions)
    }

    function checkMonster(yAxis,xAxis) {
        console.log("checkMonster: ", mapInArray[yAxis][xAxis])
        if ( mapInArray[yAxis][xAxis].hasOwnProperty('monster') && mapInArray[yAxis][xAxis].monster !== null) {
            console.log("checkMonster return true")
            return true
        }
        console.log("checkMonster return false")
        return false
    }

    useEffect(() => {
      console.log("empty useEffect")
      sendRequest()
    },[])

    useEffect(() => {
        console.log("useEffect with isLoading")
        if (!isLoading) {
            setButtons()
        }
    },[isLoading])

    function setDirections() {

    }

    function setColor(field) {
        if(field.fieldType === "WALL") {
            return "white"
        }
        if(field.fieldType === "TUNNEL") {
            return "yellow"
        }
        if(field.fieldType === "PLAYER") {
                    return "lime"
        }
        return "black"
    }

    function attackMonster() {

    }

    console.log("dungeon map: ",mapInArray)
    return(
    <div className = "row">
        <div className="col-md-3">
            <h3>Dungeon map</h3>
            {   isLoading ? (<div>Loading...</div>) :
                (
                    mapInArray.map((row) => {
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
                )
            }
        </div>
        <div className="col-md-9">
            <div className= "w-75 h-50 overflow-auto border" disabled="true">
              Welcome to the dungeon! The map is on your left side. Green square represents YOU.<br/>
              Here you can see logs what is currently happened. Good luck!!! <br/>
              There is nothing special in this room. You can go on.


            </div>
            <div className = "text-center">
                {isLoading ? (<></>): (
                    <>
                        <p>Your actions: </p>
                        <button type = "button" onClick={() =>playerMove("up")} disabled={!moveDirection.up}>GO UP</button>
                        <button type = "button" onClick={() =>playerMove("left")} disabled={!moveDirection.left}>GO LEFT</button>
                        <button type = "button" onClick={() =>playerMove("right")} disabled={!moveDirection.right}>GO RIGHT</button>
                        <button type = "button" onClick={() =>playerMove("down")} disabled={!moveDirection.down}>GO DOWN</button>
                        <button type = "button" onClick={() =>attackMonster("goblin")} style={{display: isMonster ? "inline" : "none"}}>Attack Monster</button>
                        <button type = "button">RUN AWAY!</button>
                    </>
                )}
            </div>
        </div>
    </div>
    )

}