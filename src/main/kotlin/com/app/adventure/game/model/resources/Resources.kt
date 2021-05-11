package com.app.adventure.game.model.resources

import com.app.adventure.game.view.ResourcesView

class Resources (startGold: Double, startIron: Double, startMeat: Double) {
    private val gold : MutableList<Double> = mutableListOf(startGold)
    private val iron : MutableList<Double> = mutableListOf(startIron)
    private val meat : MutableList<Double> = mutableListOf(startMeat)


    fun addResources(resourcesToAdd : ResourcesView) {
        this.gold.add(resourcesToAdd.getGold())
        this.iron.add(resourcesToAdd.getIron())
        this.meat.add(resourcesToAdd.getMeat())
    }

    fun payGold(prise :Double) {
        gold.add(prise*(-1))
    }

    fun payIron(prise :Double) {
        iron.add(prise*(-1))
    }

    fun payMeat(prise :Double) {
        meat.add(prise*(-1))
    }
    fun getGold() : Double {
        return gold.reduce { acc, d -> acc+ d}
    }
    fun getIron() : Double {
        return iron.reduce { acc, d -> acc+ d}
    }
    fun getMeat() : Double {
        return meat.reduce { acc, d -> acc+ d}
    }
}