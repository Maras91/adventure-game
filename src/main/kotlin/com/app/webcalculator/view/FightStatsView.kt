package com.app.webcalculator.view

import com.app.webcalculator.model.fight.statistics.FightStats

class FightStatsView {
    private val strength :Int
    private val hp :Int
    private val hpMax :Int
    private val armor :Int


    constructor(strength: Int, hp: Int, hpMax :Int, armor: Int) {
        this.strength = strength
        this.hp = hp
        this.hpMax = hpMax
        this.armor = armor
    }

    constructor(fightStats : FightStats) {
        this.strength = fightStats.getStrengthNumber()
        this.hp = fightStats.getHpNumber()
        this.hpMax = fightStats.getHp().getMaxHp()
        this.armor = fightStats.getArmorNumber()
    }

    public fun getStrength() : Int {
        return strength
    }

    public fun getHp(): Int {
        return  hp
    }

    public fun getHpMax(): Int {
        return  hpMax
    }

    public fun getArmor(): Int {
        return  armor
    }

}