package com.app.adventure.game.controller

import com.app.adventure.game.model.dungeon.*
import com.app.adventure.game.view.DungeonMapView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class DungeonController @Autowired constructor(
    val playerPosition: PlayerPosition,
    val dungeonMap: DungeonMap
){

    @PostMapping("/getMap")
    @ResponseBody
    fun getDungeonMap() : DungeonMapView {
        val actionsAvailable = PlayerAvailableActions(dungeonMap.gameMap, playerPosition)
        return DungeonMapView(
            playerPosition,
            dungeonMap.gameMap.map { fieldRow -> fieldRow.map{ field -> field.fieldType } },
            actionsAvailable.getView(),
            dungeonMap.gameMap[playerPosition.axisY][playerPosition.axisX])
    }

    fun Array<Array<MapField>>.copy() = map { it.clone() }.toTypedArray()

    @PostMapping("/playerMove")
    @ResponseBody
    fun getPlayerPosition(@RequestBody direction: String) {
        val actionsAvailable = PlayerAvailableActions(dungeonMap.gameMap, playerPosition)
        if (direction == "up" && actionsAvailable.goUp) {
            playerPosition.goUp()
        }
        if (direction == "down" && actionsAvailable.goDown) {
            playerPosition.goDown()
        }
        if (direction == "left" && actionsAvailable.goLeft) {
            playerPosition.goLeft()
        }
        if (direction == "right" && actionsAvailable.goRight) {
            playerPosition.goRight()
        }
    }
}