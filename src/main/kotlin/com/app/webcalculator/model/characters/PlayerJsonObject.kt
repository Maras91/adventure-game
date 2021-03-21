package com.app.webcalculator.model.characters

import com.app.webcalculator.model.fight.statistics.FightStatsJsonObject
import com.app.webcalculator.model.resources.ResourcesJsonObject

class PlayerJsonObject(fightStats : FightStatsJsonObject, resources : ResourcesJsonObject, experience : Int, experienceToNextLevel :String, level :Int) {
    val fightStatsJsonObject : FightStatsJsonObject = fightStats
    val resourcesJsonObject : ResourcesJsonObject = resources
    val experience : Int = experience
    val experienceToNextLevel = experienceToNextLevel
    val level : Int = level

}