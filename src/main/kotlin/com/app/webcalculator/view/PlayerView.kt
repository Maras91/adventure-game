package com.app.webcalculator.view

import com.app.webcalculator.view.FightStatsView
import com.app.webcalculator.view.ResourcesView

class PlayerView(fightStats : FightStatsView, resources : ResourcesView, experience : ExperienceView) {
    val fightStatsView : FightStatsView = fightStats
    val resourcesView : ResourcesView = resources
    val experienceView : ExperienceView = experience
}