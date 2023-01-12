import useRequest from './../../common/UseRequest';
import '../../../App.css';
import React, {useState,useEffect} from 'react';
import _ from 'lodash';

export default function DungeonMap() {
    const [mapInArray, isMapInArrayLoading] = useRequest("/getMap")
    const [moveDirection, setMoveDirection] = useState(
        {
            up: false,
            left: false,
            right: false,
            down: false
        }
    )
    function setMoveDirections() {
        let xAxis= -1
        let yAxis= -1
        for (let y=0; y<mapInArray.length; y++) {
            console.log("mapInArray: ",mapInArray.length)
            for(let x=0; x<mapInArray[y].length; x++) {
                if (mapInArray[y][x] === 2) {
                    xAxis= x
                    yAxis= y
                }
            }
        }
        console.log(yAxis)
        console.log(xAxis)
        let directions = _.cloneDeep(moveDirection)
        directions.up = yAxis > 0 && mapInArray[yAxis-1][xAxis] === 1
        directions.left = xAxis > 0 && mapInArray[yAxis][xAxis-1] === 1
        directions.right = xAxis+1 < mapInArray[yAxis].length && mapInArray[yAxis][xAxis+1] === 1
        directions.down = yAxis+1 < mapInArray.length && mapInArray[yAxis+1][xAxis] === 1
        setMoveDirection(directions)

    }

    useEffect(() => {
        if (!isMapInArrayLoading) {
            setMoveDirections()
        }
    },[isMapInArrayLoading])

    function setDirections() {

    }

    function setColor(colorNumber: Integer) {
        if (colorNumber === 1) {
            return "yellow"
        }
        if (colorNumber === 2) {
            return "lime"
        }
        return "white"
    }
    console.log("isMapInArrayLoading: ", isMapInArrayLoading)
    console.log("mapInArray: ", mapInArray)

    return(
    <div className = "row">
        <div className="col-md-3">
            <h3>Dungeon map</h3>
            {   isMapInArrayLoading ? (<div>Loading...</div>) :
                (
                    mapInArray.map((row) => {
                       return (
                           <div className = "board-row">
                                {
                                    row.map( (colorNumber) => {
                                        return (<><button className = "square" style={{backgroundColor: setColor(colorNumber)}}/></>)
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
                {isMapInArrayLoading ? (<></>): (
                    <>
                        <p>Your actions: </p>
                        <button type = "button" style={{display: !moveDirection.up ? "none" : "inline"}}>GO UP</button>
                        <button type = "button" style={{display: !moveDirection.left ? "none" : "inline"}}>GO LEFT</button>
                        <button type = "button" style={{display: !moveDirection.right ? "none" : "inline"}}>GO RIGHT</button>
                        <button type = "button" style={{display: !moveDirection.down ? "none" : "inline"}}>GO DOWN</button>
                        <button type = "button" style={{display: !moveDirection.up ? "none" : "inline"}}>RUN AWAY!</button>
                    </>
                )}
            </div>
        </div>
    </div>
    )

}