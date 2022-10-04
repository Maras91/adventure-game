package com.app.adventure.game.model.fight.statistics.value

import com.app.adventure.game.model.item.ItemType

abstract class  Statistics (val startValue: Int){
    val notDisposableItems: MutableMap<ItemType, Int> = ItemType.values()
        .associateWith {0} as MutableMap<ItemType, Int>
    var levelPoints :Int = 0
    var disposableItemsBuff :Int =0

    open fun getValue(): Int {
        return notDisposableItems.values.sum() + startValue + levelPoints + disposableItemsBuff
    }

    fun addValueWhenLevelUp(points: Int){
        levelPoints += points
    }

    fun addValueFromItem(itemType: ItemType, value: Int){
        notDisposableItems[itemType] = value
    }

    open fun getPointsFromLevelUp(): Int{
        return levelPoints
    }

    fun setBuff(value : Int) {
        disposableItemsBuff = value
    }
}