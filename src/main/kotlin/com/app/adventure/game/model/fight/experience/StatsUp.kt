package com.app.adventure.game.model.fight.experience


class StatsUp(private val strength: Int, private val hp: Int, private val armor: Int) {

    private val HP_PER_LEVEL : Int = 10

    fun getStrength() : Int {
        return strength
    }

    fun getHp(): Int {
        return  hp
    }

    fun getArmor(): Int {
        return  armor
    }

    fun getAllPoints() :Int {
        return strength+hp/HP_PER_LEVEL+armor
    }
    fun isValid() : Boolean {
        return strength >= 0 && armor >= 0 && hp >= 0
    }
}