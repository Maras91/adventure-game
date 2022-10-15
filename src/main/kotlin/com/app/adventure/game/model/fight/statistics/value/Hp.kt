package com.app.adventure.game.model.fight.statistics.value

class Hp (startValue : Int) : Statistics(startValue){
    private var damage : Int = 0

    fun getMaxHp() : Int {
        return notDisposableItems.values.sum() + startValue + levelPoints + disposableItemsBuff
    }

    override fun getValue () : Int {
        return notDisposableItems.values.sum() + startValue + levelPoints + disposableItemsBuff - damage
    }

    fun takeDamage(damageTaken : Int) {
        damage += damageTaken
    }

    fun hpRecovery(hpRecovery :Int) {
        damage -= hpRecovery
        if (damage < 0) {
            damage = 0
        }
    }

    override fun getPointsFromLevelUp () : Int {
        return levelPoints/10
    }
}