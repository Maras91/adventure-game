package com.app.adventure.game.model.fight.statistics.value

import com.app.adventure.game.model.item.ItemType
import com.app.adventure.game.model.fight.statistics.StatisticsName

abstract class  Statistics (val startValue: Int, val name: StatisticsName){
    val notDisposableItems: MutableMap<ItemType, Int> = ItemType.values()
        .associateWith {0} as MutableMap<ItemType, Int>
    var levelPoints :Int = 0
    var disposableItemsBuff :Int =0

    open fun getValue(): Int {
        return notDisposableItems.values.sum() + startValue + levelPoints + disposableItemsBuff
    }

    open fun addValueWhenLevelUp(points: Int){
        levelPoints += points
    }

    fun addValueFromItem(itemType: ItemType, value: Int){
        notDisposableItems[itemType] = value
    }

    fun getPointsFromLevelUp(): Int{
        return levelPoints
    }

    fun setBuff(value : Int) {
        disposableItemsBuff = value
    }
}