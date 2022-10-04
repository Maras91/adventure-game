package com.app.adventure.game.model.characters

import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.StatsUp
import com.app.adventure.game.model.fight.statistics.FightStats
import com.app.adventure.game.model.resources.Resources

class Player(private var resources: Resources, private var fightStats: FightStats, private var experience: Experience) {

    fun getResources() : Resources {
        return resources
    }

    fun getFightStats() : FightStats {
        return fightStats
    }

    fun getExperience() : Experience {
        return experience
    }

    fun win(monster : Monster) {
        getResources().addResources(monster.getResources())
        getExperience().addExperience(monster.getExperience())
    }

    fun addStatsUp(statsUp: StatsUp) {
        if (isStatsUpValid(statsUp)) {
            getFightStats().addLevelUp(statsUp)
        }
    }

    private fun isStatsUpValid(statsUp: StatsUp): Boolean {
        return statsUp.isValid() && getExperience().allStatsPointsToSpend() >= getFightStats().allSpentLevelPoints() + statsUp.getAllPoints()
    }

}