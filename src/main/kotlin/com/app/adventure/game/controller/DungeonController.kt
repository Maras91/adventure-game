package com.app.adventure.game.controller

import com.app.adventure.game.model.dungeon.*
import com.app.adventure.game.model.fight.BattleProperties
import com.app.adventure.game.view.DungeonMapView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class DungeonController @Autowired constructor(final val battleProperties: BattleProperties) {
    var playerPosition = PlayerPosition(1,0)
    final val lineOne: Array<DungeonField> = arrayOf(
        DungeonField("goblin",battleProperties,FieldType.TUNNEL),
        DungeonField("goblin",battleProperties,FieldType.TUNNEL),
        DungeonField("goblin",battleProperties,FieldType.TUNNEL),
        DungeonField(null,battleProperties,FieldType.WALL),
        DungeonField(null,battleProperties,FieldType.TUNNEL))
    final val lineTwo: Array<DungeonField> = arrayOf(
        DungeonField(null,battleProperties,FieldType.TUNNEL),
        DungeonField(null,battleProperties,FieldType.WALL),
        DungeonField(null,battleProperties,FieldType.TUNNEL),
        DungeonField(null,battleProperties,FieldType.TUNNEL),
        DungeonField(null,battleProperties,FieldType.TUNNEL))
    final val lineThree: Array<DungeonField> = arrayOf(
        DungeonField(null,battleProperties,FieldType.WALL),
        DungeonField("goblin",battleProperties,FieldType.TUNNEL),
        DungeonField("goblin",battleProperties,FieldType.TUNNEL),
        DungeonField(null,battleProperties,FieldType.WALL),
        DungeonField(null,battleProperties,FieldType.WALL),)
    private val dungeonMap = arrayOf(lineOne,lineTwo,lineThree)

    @PostMapping("/getMap")
    @ResponseBody
    fun getDungeonMap() : DungeonMapView {
        val actionsAvailable = PlayerAvailableActions(dungeonMap, playerPosition)
        return DungeonMapView(
            playerPosition,
            dungeonMap.map { fieldRow -> fieldRow.map{ field -> field.fieldType } },
            actionsAvailable.getView(),
            dungeonMap[playerPosition.axisY][playerPosition.axisX])
    }

    fun Array<Array<MapField>>.copy() = map { it.clone() }.toTypedArray()

    @PostMapping("/playerMove")
    @ResponseBody
    fun getPlayerPosition(@RequestBody direction: String) {
        val actionsAvailable = PlayerAvailableActions(dungeonMap, playerPosition)
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