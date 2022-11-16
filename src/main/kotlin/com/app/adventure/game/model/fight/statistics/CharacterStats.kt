package com.app.adventure.game.model.fight.statistics

import com.app.adventure.game.model.fight.experience.StatsUp
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Statistics
import com.app.adventure.game.model.item.NotDisposableItem
import org.springframework.beans.factory.annotation.Autowired

class CharacterStats @Autowired constructor(mapOfProperties: Map<String, Int>, statisticsFactory: StatisticsFactory) {
    private val stats:Map<StatisticsName,Statistics>
    init {
        stats =
            mapOfProperties.mapKeys {
                StatisticsName.getAttributeByName(it.key)
            }.mapValues {
                statisticsFactory.createStatistics(it.key,it.value)
            }
    }

    fun getStats() : Map<StatisticsName,Statistics> {
        return stats
    }

    fun getHp(): Hp {
        return stats[StatisticsName.HP] as Hp
    }

    fun allSpentLevelPoints(): Int {
        return stats.values.sumBy { it.getPointsFromLevelUp() }
    }

    fun addLevelUp(statsUp : StatsUp) {
        statsUp.statsToLevelUp.forEach { (upStat, value) ->
            if (stats.containsKey(upStat)) {
                stats[upStat]?.addValueWhenLevelUp(value)
            }
        }
    }

    fun addStatsFromItem(item: NotDisposableItem){
        val itemAttributesMap = item.attributes
        stats.values.forEach { it.addValueFromItem(item.itemType,0) }
        itemAttributesMap.forEach {
                (statName, statValue) ->  stats[statName]?.addValueFromItem(item.itemType,statValue)
        }
    }
}