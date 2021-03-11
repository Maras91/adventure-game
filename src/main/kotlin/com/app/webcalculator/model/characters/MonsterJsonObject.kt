package com.app.webcalculator.model.characters

import com.app.webcalculator.model.fight.statistics.FightStatsJsonObject
import com.app.webcalculator.model.resources.ResourcesJsonObject

class MonsterJsonObject(fightStats : FightStatsJsonObject, resources : ResourcesJsonObject) {
    private val fightStatsJsonObject : FightStatsJsonObject = fightStats
    private val resourcesJsonObject : ResourcesJsonObject = resources

    public fun getResourcesJsonObject() : ResourcesJsonObject {
        return resourcesJsonObject
    }

    public fun getFightStatsJsonObject() : FightStatsJsonObject {
        return fightStatsJsonObject
    }
}