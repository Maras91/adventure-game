package com.app.webcalculator.model.characters

import com.app.webcalculator.view.FightStatsView
import com.app.webcalculator.view.ResourcesView

class Monster(fightStats : FightStatsView, resources : ResourcesView, experience : Int) {
    private val fightStats : FightStatsView = fightStats
    private val resources : ResourcesView = resources
    private val experience : Int = experience

    public fun getResources() : ResourcesView {
        return resources
    }

    public fun getFightStats() : FightStatsView {
        return fightStats
    }

    fun getExperience(): Int {
        return experience
    }
}