package com.app.adventure.game.model.dungeon

import com.app.adventure.game.view.PlayerAvailableActionsView

class PlayerAvailableActions (dungeonMap: Array<Array<DungeonField>>, playerPosition: PlayerPosition){
    val goLeft: Boolean
    val goRight: Boolean
    val goUp: Boolean
    val goDown: Boolean
    val monsterFight: Boolean
    val getTreasure: Boolean

    init {
        goUp = playerPosition.axisY>0 &&
                dungeonMap[playerPosition.axisY-1][playerPosition.axisX].fieldType != FieldType.WALL;
        goDown = playerPosition.axisY<dungeonMap.size-1 &&
                dungeonMap[playerPosition.axisY+1][playerPosition.axisX].fieldType != FieldType.WALL
        goLeft = playerPosition.axisX>0 &&
                dungeonMap[playerPosition.axisY][playerPosition.axisX-1].fieldType != FieldType.WALL
        goRight = playerPosition.axisX<dungeonMap[playerPosition.axisY].size-1 &&
                dungeonMap[playerPosition.axisY][playerPosition.axisX+1].fieldType != FieldType.WALL
        monsterFight = dungeonMap[playerPosition.axisY][playerPosition.axisX].monster != null
        getTreasure = false
    }

    fun getView() : PlayerAvailableActionsView {
        return PlayerAvailableActionsView(goLeft,goRight,goUp,goDown,monsterFight,getTreasure)
    }
}