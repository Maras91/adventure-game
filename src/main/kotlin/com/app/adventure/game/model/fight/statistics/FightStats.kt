package com.app.adventure.game.model.fight.statistics

import com.app.adventure.game.model.fight.experience.StatsUp
import com.app.adventure.game.model.fight.statistics.value.Armor
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Strength

class FightStats(private val strength: Strength, private val hp: Hp, private val armor: Armor) {

    public fun takeDamage(damageTaken : Int) {
        hp.takeDamage(damageTaken)
    }

    public fun hpRecovery(hpRecovery :Int) {
        hp.hpRecovery(hpRecovery)
    }

    public fun getStrengthNumber() : Int {
        return strength.getValue()
    }

    public fun getHpNumber(): Int {
        return  hp.getValue()
    }

    public fun getArmorNumber(): Int {
        return  armor.getValue()
    }

    public fun getStrength() : Strength {
        return strength
    }

    public fun getHp(): Hp {
        return  hp
    }

    public fun getArmor(): Armor {
        return  armor
    }

    public fun allSpentLevelPoints(): Int {
        return strength.getPointsFromLevelUp() + hp.getPointsFromLevelUp() + armor.getPointsFromLevelUp()
    }

    public fun addLevelUp(statsUp : StatsUp) {
        strength.addWhenLevelUp(statsUp.getStrength())
        hp.addWhenLevelUp(statsUp.getHp())
        hp.hpRecovery(statsUp.getHp())
        armor.addWhenLevelUp(statsUp.getArmor())
    }
}