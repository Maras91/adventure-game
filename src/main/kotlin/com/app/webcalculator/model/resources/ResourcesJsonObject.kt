package com.app.webcalculator.model.resources

class ResourcesJsonObject (gold: Double, iron: Double, meat: Double) {
    private val gold : Double = gold
    private val iron : Double = iron
    private val meat : Double = meat

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