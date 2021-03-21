package com.app.webcalculator.model.fight.statistics

import com.app.webcalculator.model.fight.statistics.value.Armor
import com.app.webcalculator.model.fight.statistics.value.Hp
import com.app.webcalculator.model.fight.statistics.value.Strength

class FightStats(private val strength: Strength, private val hp: Hp, private val armor: Armor) {

    public fun takeDamage(damageTaken : Int) {
        hp.takeDamage(damageTaken)
    }

    public fun hpRecovery(hpRecovery :Int) {
        hp.hpRecovery(hpRecovery)
    }

    public fun getStrengthNumber() : Int {
        return strength.getStrength()
    }

    public fun getHpNumber(): Int {
        return  hp.getHp()
    }

    public fun getArmorNumber(): Int {
        return  armor.getArmor()
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
}