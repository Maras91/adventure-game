package com.app.adventure.game.model.fight.experience

import com.app.adventure.game.model.characters.Player
import com.app.adventure.game.model.fight.statistics.StatisticsName
import org.springframework.stereotype.Service

@Service
class LevelService {
    fun levelUp(statsToUp: Map<String, Int>, player: Player) {
        val allStatNames = statsToUp.keys.filter { StatisticsName.values().map { name -> name.attributeName }.contains(it)}
        val statsUp = StatsUp(
            allStatNames.associate{ (StatisticsName.getAttributeByName(it)) to (statsToUp[it] ?: 0) }
        )
        player.addStatsUp(statsUp)
    }

}