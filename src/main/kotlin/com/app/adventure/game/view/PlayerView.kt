package com.app.adventure.game.view

import com.app.adventure.game.model.item.Item

class PlayerView(val fightStatsView : Map<String,Int>,
                 val resourcesView : Map<String,Double>,
                 val inventoryView: Map<String, Item>,
                 val experienceView : ExperienceView,
                 val levelUpPoints : Int) {

}