package com.app.adventure.game.model.fight.statistics

import com.app.adventure.game.model.fight.statistics.value.Armor
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Statistics
import com.app.adventure.game.model.fight.statistics.value.Strength
import org.springframework.stereotype.Service

@Service
class StatisticsFactory {
    fun createStatistics(name: StatisticsName, value:Int): Statistics {
        return when(name) {
            StatisticsName.STRENGTH -> Strength(value)
            StatisticsName.HP -> Hp(value)
            StatisticsName.ARMOR -> Armor(value)
        }
    }
}