package com.app.webcalculator.model

class Resources {
    private var gold : Double
    private var iron : Double
    private var meat : Double

    constructor(gold: Double, iron: Double, meat: Double) {
        this.gold = gold
        this.iron = iron
        this.meat = meat
    }

    fun addResources(resourcesToAdd : Resources) {
        this.gold += resourcesToAdd.getGold()
        this.iron += resourcesToAdd.getIron()
        this.meat += resourcesToAdd.getMeat()
    }

    fun payGold(prise :Int) {
        gold -= prise
    }

    fun payIron(prise :Int) {
        iron -= prise
    }

    fun payMeat(prise :Int) {
        meat -= prise
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