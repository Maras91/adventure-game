package com.app.webcalculator.model.characters

import com.app.webcalculator.model.fight.experience.Experience
import com.app.webcalculator.model.fight.experience.StatsUp
import com.app.webcalculator.model.fight.statistics.FightStats
import com.app.webcalculator.model.resources.Resources

class Player(private var resources: Resources, private var fightStats: FightStats, private var experience: Experience) {

    public fun getResources() : Resources {
        return resources
    }

    public fun getFightStats() : FightStats {
        return fightStats
    }

    public fun getExperience() : Experience {
        return experience
    }

    public fun win(monster : Monster) {
        getResources().addResources(monster.getResources())
        getExperience().addExperience(monster.getExperience())
    }

    public fun addStatsUp(statsUp: StatsUp) {
        if (isStatsUpValid(statsUp)) {
            getFightStats().addLevelUp(statsUp)
        }
    }

    private fun isStatsUpValid(statsUp: StatsUp): Boolean {
        return statsUp.isValid() && getExperience().allStatsPointsToSpend() >= getFightStats().allSpentLevelPoints() + statsUp.getAllPoints()
    }


}