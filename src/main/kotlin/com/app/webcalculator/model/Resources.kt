package com.app.webcalculator.model

class Resources {
    private val gold : Double
    private val iron : Double
    private val meat : Double

    constructor(gold: Double, iron: Double, meat: Double) {
        this.gold = gold
        this.iron = iron
        this.meat = meat
    }
    fun getGold() : Double {
        return gold
    }
    fun getIron() : Double {
        return iron
    }
    fun getMeat() : Double {
        return meat
    }
}