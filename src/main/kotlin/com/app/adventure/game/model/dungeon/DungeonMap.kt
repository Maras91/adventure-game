package com.app.adventure.game.model.dungeon

class DungeonMap (val gameMap: Array<Array<DungeonMapField>>){
    fun removeMonster(axisY: Int, axisX: Int) {
        gameMap[axisY][axisX].removeMonster()
    }
}