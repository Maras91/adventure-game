import useRequest from './../../common/UseRequest';
import '../../../App.css';
import React, {useState,useEffect} from 'react';
import _ from 'lodash';
//TODO functions should start from lower case
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
    <div>
        <div>
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
        <div className = "text-center">
            {isMapInArrayLoading ? (<></>): (
                <>
                    <button type = "button" disabled={!moveDirection.up}>UP</button><br/>
                    <button type = "button" disabled={!moveDirection.left}>LEFT</button>
                    <button type = "button" disabled={!moveDirection.right}>RIGHT</button><br/>
                    <button type = "button" disabled={!moveDirection.down}>DOWN</button>
                </>
            )}
        </div>
    </div>
    )

}