package com.app.adventure.game.model.characters

import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.model.resources.ResourceValue
import com.app.adventure.game.model.resources.ResourceName

class Monster(private val statsView : Map<StatisticsName,Int>, private val resources: Map<ResourceName, ResourceValue>, private val experience : Int) {

    fun getResources() : Map<ResourceName, ResourceValue> {
        return resources
    }

    fun getStatsView() : Map<StatisticsName,Int> {
        return statsView
    }

    fun getExperience(): Int {
        return experience
    }
}