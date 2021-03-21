package com.app.webcalculator.model.fight.statistics

class FightStatsJsonObject {
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