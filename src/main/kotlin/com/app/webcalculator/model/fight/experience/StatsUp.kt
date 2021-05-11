package com.app.webcalculator.model.fight.experience


class StatsUp {
    private val HP_PER_LEVEL : Int = 10
    private val strength :Int
    private val hp :Int
    private val armor :Int

    constructor(strength: Int, hp: Int, armor: Int) {
        this.strength = strength
        this.hp = hp
        this.armor = armor
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

    public fun getAllPoints() :Int {
        return strength+hp/HP_PER_LEVEL+armor
    }
    public fun isValid() : Boolean {
        return strength >= 0 && armor >= 0 && hp >= 0
    }
}