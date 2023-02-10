package com.app.adventure.game.controller

import com.app.adventure.game.model.dungeon.*
import com.app.adventure.game.model.fight.BattleProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class DungeonController @Autowired constructor(final val battleProperties: BattleProperties) {
    var playerPositionAsiX = 1
    var playerPositionAsiY = 0

    final val lineOne = arrayOf(Tunnel(battleProperties.monsters["goblin"]),Tunnel(battleProperties.monsters["goblin"]),Tunnel(battleProperties.monsters["goblin"]),Wall(),Tunnel(null))
    final val lineTwo = arrayOf(Tunnel(null),Wall(),Tunnel(null),Tunnel(null),Tunnel(null))
    final val lineThree = arrayOf(Wall(),Tunnel(battleProperties.monsters["goblin"]),Tunnel(battleProperties.monsters["goblin"]),Wall(),Wall())
    private val dungeonMap = arrayOf(lineOne,lineTwo,lineThree)

    @PostMapping("/getMap")
    @ResponseBody
    fun getDungeonMap() : Array<Array<MapField>> {
        val arrayToSend = dungeonMap.copy()
        arrayToSend[playerPositionAsiY][playerPositionAsiX] = PlayerField(arrayToSend[playerPositionAsiY][playerPositionAsiX].monster)
        //TODO maybe return the constructor with map and player position as a second parameter
        return arrayToSend
    }

    fun Array<Array<MapField>>.copy() = map { it.clone() }.toTypedArray()

    @PostMapping("/playerMove")
    @ResponseBody
    fun getPlayerPosition(@RequestBody direction: String) {
        if (direction == "up" &&
            playerPositionAsiY>0 &&
            dungeonMap[playerPositionAsiY-1][playerPositionAsiX].fieldType != FiledType.WALL
        ) {
            playerPositionAsiY--;
        }
        if (direction == "down" &&
            playerPositionAsiY<dungeonMap.size-1 &&
            dungeonMap[playerPositionAsiY+1][playerPositionAsiX].fieldType != FiledType.WALL
        ) {
            playerPositionAsiY++;
        }
        if (direction == "left" &&
            playerPositionAsiX>0 &&
            dungeonMap[playerPositionAsiY][playerPositionAsiX-1].fieldType != FiledType.WALL
        ) {
            playerPositionAsiX--;
        }
        if (direction == "right" &&
            playerPositionAsiX<dungeonMap[playerPositionAsiY].size-1 &&
            dungeonMap[playerPositionAsiY][playerPositionAsiX+1].fieldType != FiledType.WALL
        ) {
            playerPositionAsiX++;
        }
    }
}