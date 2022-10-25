package com.app.adventure.game.model.characters

import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.view.ResourcesView

class Monster(private val statsView : Map<StatisticsName,Int>, private val resources : ResourcesView, private val experience : Int) {

    fun getResources() : ResourcesView {
        return resources
    }

    fun getStatsView() : Map<StatisticsName,Int> {
        return statsView
    }

    fun getExperience(): Int {
        return experience
    }
}