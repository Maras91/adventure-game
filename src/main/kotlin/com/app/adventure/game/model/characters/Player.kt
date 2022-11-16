package com.app.adventure.game.model.characters

import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.StatsUp
import com.app.adventure.game.model.fight.statistics.CharacterStats
import com.app.adventure.game.model.item.*
import com.app.adventure.game.model.resources.ResourceValue
import com.app.adventure.game.model.resources.ResourceName

class Player(private val resources: MutableMap<ResourceName, ResourceValue>,
             private val characterStats: CharacterStats,
             private val wearingItems: MutableMap<ItemType,NotDisposableItem>,
             private val experience: Experience) {
    val inventory: Inventory = Inventory()
    fun getResources() : Map<ResourceName, ResourceValue> {
        return resources
    }

    fun getExperience() : Experience {
        return experience
    }

    fun getCharacterStats(): CharacterStats {
        return characterStats
    }

    fun win(monster : Monster) {
        addResources(monster.getResources())
        getExperience().addExperience(monster.getExperience())
    }

    fun addResources(rsc: Map<ResourceName, ResourceValue>) {
        rsc.forEach{ (name,rsc)->
            if (resources.containsKey(name)){
                resources[name]?.addValue(rsc.getValue())
            } else {
                resources[name] = ResourceValue(rsc.getValue())
            }
        }

    }

    fun addStatsUp(statsUp: StatsUp) {
        if (isStatsUpValid(statsUp)) {
            characterStats.addLevelUp(statsUp)
        }
    }

    private fun isStatsUpValid(statsUp: StatsUp): Boolean {
        return statsUp.isValid() && getExperience().allStatsPointsToSpend() >= characterStats.allSpentLevelPoints() + statsUp.getAllPoints()
    }

    fun useItem(item: DisposableItem){
        if (inventory.removeItem(item.name) != null) {
            if (item.itemEffects.containsKey(ItemEffects.HP_RECOVERY)) {
                characterStats.getHp().hpRecovery(item.itemEffects[ItemEffects.HP_RECOVERY] ?: 0)
            }
        }
    }
    fun putOn(item: NotDisposableItem) {
        if (inventory.removeItem(item.name) != null) {
            if (wearingItems.containsKey(item.itemType)) {
                inventory.addItem(wearingItems[item.itemType]!!)
            }
            wearingItems[item.itemType] = item
            characterStats.addStatsFromItem(item)
        }
    }
}