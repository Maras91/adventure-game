package com.app.adventure.game.model.fight.statistics.value

import com.app.adventure.game.model.fight.statistics.StatisticsName

class Hp (startValue: Int) : Statistics(startValue, StatisticsName.HP){

    //TODO add this to yaml properties
    private val HP_PER_LEVEL : Int = 10
    private var damage: Int = 0

    override fun getValue () : Int {
        return notDisposableItems.values.sum() + startValue + levelPoints*HP_PER_LEVEL + disposableItemsBuff
    }

    fun getCurrentHp() : Int {
        return notDisposableItems.values.sum() + startValue + levelPoints*HP_PER_LEVEL + disposableItemsBuff - damage
    }

    override fun addValueWhenLevelUp (points: Int){
        levelPoints += points
    }

    fun hpRecovery(hpRecovery :Int) {
        damage -= hpRecovery
        if (damage < 0) {
            damage = 0
        }
    }

    fun takeDamage(damageTaken : Int) {
        damage += damageTaken
    }
}