package com.app.adventure.game.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class DungeonController {
    var playerPositionAsiX = 1
    var playerPositionAsiY = 0

    final val lineOne = intArrayOf(1,1,1,0,1)
    final val lineTwo = intArrayOf(1,0,1,1,1)
    final val lineThree = intArrayOf(0,1,1,0,0)
    private val dungeonMap = arrayOf(lineOne,lineTwo,lineThree)

    @PostMapping("/getMap")
    @ResponseBody
    fun getDungeonMap() : Array<IntArray> {
        //change int array to object array
        val arrayToSend = dungeonMap.copy()
        arrayToSend[playerPositionAsiY][playerPositionAsiX] = 2
        return arrayToSend
    }

    fun Array<IntArray>.copy() = map { it.clone() }.toTypedArray()

    @PostMapping("/playerMove")
    @ResponseBody
    fun getPlayerPosition(@RequestBody direction: String) {
        if (direction == "up" &&
            playerPositionAsiY>0 &&
            dungeonMap[playerPositionAsiY-1][playerPositionAsiX] == 1
        ) {
            playerPositionAsiY--;
        }
        if (direction == "down" &&
            playerPositionAsiY<dungeonMap.size-1 &&
            dungeonMap[playerPositionAsiY+1][playerPositionAsiX] == 1
        ) {
            playerPositionAsiY++;
        }
        if (direction == "left" &&
            playerPositionAsiX>0 &&
            dungeonMap[playerPositionAsiY][playerPositionAsiX-1] == 1
        ) {
            playerPositionAsiX--;
        }
        if (direction == "right" &&
            playerPositionAsiX<dungeonMap[playerPositionAsiY].size-1 &&
            dungeonMap[playerPositionAsiY][playerPositionAsiX+1] == 1
        ) {
            playerPositionAsiX++;
        }
    }
}