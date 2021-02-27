package com.app.webcalculator.model

class FightStats {
    private val strength :Int
    private val hp :Int
    private val armor :Int

    constructor(strength: Int, hp :Int, armor :Int) {
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


}