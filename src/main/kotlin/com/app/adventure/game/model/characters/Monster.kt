package com.app.adventure.game.model.characters

import com.app.adventure.game.view.FightStatsView
import com.app.adventure.game.view.ResourcesView

class Monster(fightStats : FightStatsView, resources : ResourcesView, experience : Int) {
    private val fightStats : FightStatsView = fightStats
    private val resources : ResourcesView = resources
    private val experience : Int = experience

    fun getResources() : ResourcesView {
        return resources
    }

    fun getFightStats() : FightStatsView {
        return fightStats
    }

    fun getExperience(): Int {
        return experience
    }
}