package com.app.adventure.game.view

import com.app.adventure.game.view.FightStatsView
import com.app.adventure.game.view.ResourcesView

class PlayerView(fightStats : FightStatsView, resources : ResourcesView,
                 experience : ExperienceView, levelUpPoints : Int) {
    val fightStatsView : FightStatsView = fightStats
    val resourcesView : ResourcesView = resources
    val experienceView : ExperienceView = experience
    val levelUpPoints : Int = levelUpPoints
}