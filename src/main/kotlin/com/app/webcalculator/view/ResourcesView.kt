package com.app.webcalculator.view

import com.app.webcalculator.model.resources.Resources

class ResourcesView {
    private val gold : Double
    private val iron : Double
    private val meat : Double

    constructor(resources : Resources) {
        this.gold = resources.getGold()
        this.iron = resources.getIron()
        this.meat = resources.getMeat()
    }

    constructor(gold: Double, iron: Double, meat :Double) {
        this.gold = gold
        this.iron = iron
        this.meat = meat
    }

    public fun getGold() : Double {
        return gold
    }
    public fun getIron() : Double {
        return iron
    }
    public fun getMeat() : Double {
        return meat
    }

}