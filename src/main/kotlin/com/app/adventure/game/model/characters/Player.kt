package com.app.adventure.game.model.characters

import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.StatsUp
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Statistics
import com.app.adventure.game.model.item.Item
import com.app.adventure.game.model.item.NotDisposableItem
import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.ItemEffects
import com.app.adventure.game.model.resources.Resources

class Player(private var resources: Resources, listOfStats: List<Statistics>, private var experience: Experience) {
    private var stats :Map<StatisticsName,Statistics> = listOfStats.associateBy { it.name }

    fun getResources() : Resources {
        return resources
    }

    fun getExperience() : Experience {
        return experience
    }

    fun getStats() : Map<StatisticsName,Statistics> {
        return stats
    }

    fun win(monster : Monster) {
        getResources().addResources(monster.getResources())
        getExperience().addExperience(monster.getExperience())
    }

    fun addStatsUp(statsUp: StatsUp) {
        if (isStatsUpValid(statsUp)) {
            addLevelUp(statsUp)
        }
    }

    fun addLevelUp(statsUp : StatsUp) {
        statsUp.statsToLevelUp.forEach { (upStat, value) ->
            if (stats.containsKey(upStat)) {
                stats[upStat]?.addValueWhenLevelUp(value)
            }
        }
    }

    private fun isStatsUpValid(statsUp: StatsUp): Boolean {
        return statsUp.isValid() && getExperience().allStatsPointsToSpend() >= allSpentLevelPoints() + statsUp.getAllPoints()
    }

    fun allSpentLevelPoints(): Int {
        return stats.values.sumBy { it.getPointsFromLevelUp() }
    }
    //TODO throw exception if it is not possible
    fun getHp(): Hp {
        return stats[StatisticsName.HP] as Hp
    }

    fun addStatsFromItem(item: Item){
        if (item is NotDisposableItem) {
            val itemAttributesMap = item.attributes
            stats.values.forEach { it.addValueFromItem(item.itemType,0) }
            itemAttributesMap.forEach {(statName, statValue) ->  stats[statName]?.addValueFromItem(item.itemType,statValue) }

        }
        if (item is DisposableItem) {
            if(item.itemEffects.containsKey(ItemEffects.HP_RECOVERY)) {
                getHp().hpRecovery(item.itemEffects[ItemEffects.HP_RECOVERY] ?:0)
            }
        }
    }
}