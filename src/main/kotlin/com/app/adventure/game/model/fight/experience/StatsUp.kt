package com.app.adventure.game.model.fight.experience

import com.app.adventure.game.model.fight.statistics.StatisticsName


class StatsUp(val statsToLevelUp: Map<StatisticsName,Int>) {

    fun getAllPoints() :Int {
        return statsToLevelUp.values.sum()
    }

    fun isValid() : Boolean {
        return statsToLevelUp.values.stream().allMatch { it>=0 }
    }
}