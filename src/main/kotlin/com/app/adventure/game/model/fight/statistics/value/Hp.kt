package com.app.adventure.game.model.fight.statistics.value

class Hp (private val startValue : Int, private var items: Int, private var level :Int, private var damage : Int){

    fun getMaxHp() : Int {
        return items + startValue + level
    }

    fun getValue () : Int {
        return items + startValue + level - damage
    }

    public fun takeDamage(damageTaken : Int) {
        damage += damageTaken
    }

    public fun hpRecovery(hpRecovery :Int) {
        damage -= hpRecovery
        if (damage < 0) {
            damage = 0
        }
    }

    public fun addWhenLevelUp (points : Int) {
        level += points
    }

    public fun addFromItem (points : Int) {
        items = points
    }

    public fun getPointsFromLevelUp () : Int {
        //TODO Level points should be multiplied by 10 + add tests for this
        return level/10
    }
}