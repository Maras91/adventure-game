package com.app.adventure.game.model.dungeon

class DungeonMap (val gameMap: Array<Array<DungeonField>>){
    fun removeMonster(axisY: Int, axisX: Int) {
        gameMap[axisY][axisX].removeMonster()
    }
}