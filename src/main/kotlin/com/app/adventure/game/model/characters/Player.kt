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
import com.app.adventure.game.model.resources.Resource
import com.app.adventure.game.model.resources.ResourceName

class Player(private var resources: MutableMap<ResourceName, Resource>,
             private var stats:Map<StatisticsName,Statistics>,
             private var experience: Experience) {
    //TODO add test and init to maintain the consistency of the stats Map
    fun getResources() : Map<ResourceName, Resource> {
        return resources
    }

    fun getExperience() : Experience {
        return experience
    }

    fun getStats() : Map<StatisticsName,Statistics> {
        return stats
    }

    fun win(monster : Monster) {
        addResources(monster.getResources())
        getExperience().addExperience(monster.getExperience())
    }

    fun addResources(rsc: Map<ResourceName, Resource>) {
        rsc.forEach{ (name,rsc)->
            if (resources.containsKey(name)){
                resources[name]?.addValue(rsc.getValue())
            } else {
                resources[name] = Resource(name,rsc.getValue())
            }
        }

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

    fun getHp(): Hp {
        return stats[StatisticsName.HP] as Hp
    }

    fun addStatsFromItem(item: Item){
        if (item is NotDisposableItem) {
            val itemAttributesMap = item.attributes
            stats.values.forEach { it.addValueFromItem(item.itemType,0) }
            itemAttributesMap.forEach {
                    (statName, statValue) ->  stats[statName]?.addValueFromItem(item.itemType,statValue)
            }
        }
        if (item is DisposableItem) {
            if(item.itemEffects.containsKey(ItemEffects.HP_RECOVERY)) {
                getHp().hpRecovery(item.itemEffects[ItemEffects.HP_RECOVERY] ?:0)
            }
        }
    }
}