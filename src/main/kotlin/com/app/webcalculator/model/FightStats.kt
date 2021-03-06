package com.app.webcalculator.model

class FightStats {
    private val strength :Int
    private var hp :Int
    private val armor :Int
    private var experience :Int = 0

    constructor(strength: Int, hp :Int, armor :Int) {
        this.strength = strength
        this.hp = hp
        this.armor = armor
    }

    public fun takeDamage(damage : Int) {
        this.hp -= damage
        if (hp < 0) {
            hp = 0
        }
    }

    public fun hpRecovery(hpRecovery :Int) {
        this.hp += hpRecovery
        if (hp > 50) {
            hp = 50
        }
    }

    public fun addExperience (experience : Int) {
        this.experience += experience
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