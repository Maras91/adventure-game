package com.app.adventure.game.model.characters

import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.model.resources.Resource
import com.app.adventure.game.model.resources.ResourceName

class Monster(private val statsView : Map<StatisticsName,Int>,private val resources: Map<ResourceName, Resource>, private val experience : Int) {

    fun getResources() : Map<ResourceName, Resource> {
        return resources
    }

    fun getStatsView() : Map<StatisticsName,Int> {
        return statsView
    }

    fun getExperience(): Int {
        return experience
    }
}