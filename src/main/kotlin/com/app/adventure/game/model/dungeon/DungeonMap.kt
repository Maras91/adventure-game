package com.app.adventure.game.model.dungeon

import com.app.adventure.game.model.dungeon.services.RandomMapGeneratorService
import org.springframework.beans.factory.annotation.Autowired

class DungeonMap @Autowired constructor(private val randomMapGeneratorService: RandomMapGeneratorService){

    var gameMap: Array<Array<DungeonMapField>> = Array(1){Array(1){WallField()}}

    fun removeMonster(axisY: Int, axisX: Int) {
        gameMap[axisY][axisX].removeMonster()
    }

    fun generateRandomMap() {
        // TODO height and width have to set as properties
        gameMap = randomMapGeneratorService.generateRandomMap(6,7)
    }
}