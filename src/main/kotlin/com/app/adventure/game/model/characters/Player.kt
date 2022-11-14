package com.app.adventure.game.model.characters

import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.StatsUp
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Statistics
import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.model.item.*
import com.app.adventure.game.model.resources.Resource
import com.app.adventure.game.model.resources.ResourceName

class Player(private val resources: MutableMap<ResourceName, Resource>,
             private val stats:Map<StatisticsName,Statistics>,
             private val wearingItems: MutableMap<ItemType,NotDisposableItem>,
             private val experience: Experience) {
    val inventory: Inventory = Inventory()
    //TODO  1 add test and init to maintain the consistency of the stats Map
    // change map stats, resources, wearingItems like inventory
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

    fun useItem(item: DisposableItem){
        if (inventory.removeItem(item.name) != null) {
            if (item.itemEffects.containsKey(ItemEffects.HP_RECOVERY)) {
                getHp().hpRecovery(item.itemEffects[ItemEffects.HP_RECOVERY] ?: 0)
            }
        }
    }
    fun putOn(item: NotDisposableItem) {
        if (inventory.removeItem(item.name) != null) {
            if (wearingItems.containsKey(item.itemType)) {
                inventory.addItem(wearingItems[item.itemType]!!)
            }
            wearingItems[item.itemType] = item
            addStatsFromItem(item)
        }
    }
    private fun addStatsFromItem(item: NotDisposableItem){
        val itemAttributesMap = item.attributes
        stats.values.forEach { it.addValueFromItem(item.itemType,0) }
        itemAttributesMap.forEach {
                (statName, statValue) ->  stats[statName]?.addValueFromItem(item.itemType,statValue)
        }
    }
}