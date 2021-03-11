package com.app.webcalculator.model.characters

import com.app.webcalculator.model.fight.statistics.FightStats
import com.app.webcalculator.model.fight.statistics.FightStatsJsonObject
import com.app.webcalculator.model.resources.Resources

class Player(private var resources: Resources, private var fightStats: FightStats) {

    public fun getResources() : Resources {
        return resources
    }

    public fun getFightStats() : FightStats {
        return fightStats
    }


}