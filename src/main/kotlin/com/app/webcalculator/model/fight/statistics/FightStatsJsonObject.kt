package com.app.webcalculator.model.fight.statistics

class FightStatsJsonObject {
    private val strength :Int
    private val hp :Int
    private val armor :Int
    private val experience :Int

    constructor(strength: Int, hp: Int, armor: Int, experience: Int) {
        this.strength = strength
        this.hp = hp
        this.armor = armor
        this.experience = experience
    }

    fun getExperience(): Int {
        return experience
    }

    public fun getStrength() : Int {
        return strength
    }

    public fun getHp(): Int {
        return  hp
    }

    public fun getArmor(): Int {
        return  armor
    }
}