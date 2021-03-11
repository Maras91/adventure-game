package com.app.webcalculator.model.characters

import com.app.webcalculator.model.fight.statistics.FightStatsJsonObject
import com.app.webcalculator.model.resources.ResourcesJsonObject

class PlayerJsonObject(fightStats : FightStatsJsonObject, resources : ResourcesJsonObject) {
    val fightStatsJsonObject : FightStatsJsonObject = fightStats
    val resourcesJsonObject : ResourcesJsonObject = resources
}