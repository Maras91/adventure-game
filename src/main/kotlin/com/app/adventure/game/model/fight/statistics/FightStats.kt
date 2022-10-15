package com.app.adventure.game.model.fight.statistics

import com.app.adventure.game.model.fight.experience.StatsUp
import com.app.adventure.game.model.fight.statistics.value.Armor
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Strength
import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.Item
import com.app.adventure.game.model.item.ItemAttribute
import com.app.adventure.game.model.item.NotDisposableItem

class FightStats(private val strength: Strength, private val hp: Hp, private val armor: Armor) {

    fun takeDamage(damageTaken : Int) {
        hp.takeDamage(damageTaken)
    }

    fun getStrengthNumber() : Int {
        return strength.getValue()
    }

    fun getHpNumber(): Int {
        return  hp.getValue()
    }

    fun getArmorNumber(): Int {
        return  armor.getValue()
    }

    fun getStrength() : Strength {
        return strength
    }

    fun getHp(): Hp {
        return  hp
    }

    fun getArmor(): Armor {
        return  armor
    }

    fun addStatsFromItem(item: Item){
        val itemAttributesMap = item.attributes
        //TODO there is probably a cleaner way to do that
        //TODO maybe use Statistics class to add this vales
        if (item is NotDisposableItem) {
            if (itemAttributesMap.containsKey(ItemAttribute.STRENGTH)) {
                strength.addValueFromItem(item.itemType,itemAttributesMap.getValue(ItemAttribute.STRENGTH))
            } else {
                strength.addValueFromItem(item.itemType,0)
            }
            if (itemAttributesMap.containsKey(ItemAttribute.ARMOR)) {
                armor.addValueFromItem(item.itemType,itemAttributesMap.getValue(ItemAttribute.ARMOR))
            } else {
                armor.addValueFromItem(item.itemType,0)
            }
            if (itemAttributesMap.containsKey(ItemAttribute.HP)) {
                hp.addValueFromItem(item.itemType,itemAttributesMap.getValue(ItemAttribute.HP))
            } else {
                hp.addValueFromItem(item.itemType,0)
            }
        }
        if (item is DisposableItem) {
            if (itemAttributesMap.containsKey(ItemAttribute.STRENGTH)) {
                strength.setBuff(itemAttributesMap.getValue(ItemAttribute.STRENGTH))
            }
            if (itemAttributesMap.containsKey(ItemAttribute.ARMOR)) {
                armor.setBuff(itemAttributesMap.getValue(ItemAttribute.ARMOR))
            }
            if (itemAttributesMap.containsKey(ItemAttribute.HP)) {
                hp.setBuff(itemAttributesMap.getValue(ItemAttribute.HP))
            }
            if (itemAttributesMap.containsKey(ItemAttribute.HP_RECOVERY)) {
                hp.hpRecovery(itemAttributesMap.getValue(ItemAttribute.HP_RECOVERY))
            }
        }
    }

    fun allSpentLevelPoints(): Int {
        return strength.getPointsFromLevelUp() + hp.getPointsFromLevelUp() + armor.getPointsFromLevelUp()
    }

    fun addLevelUp(statsUp : StatsUp) {
        strength.addValueWhenLevelUp(statsUp.getStrength())
        hp.addValueWhenLevelUp(statsUp.getHp())
        armor.addValueWhenLevelUp(statsUp.getArmor())
    }
}