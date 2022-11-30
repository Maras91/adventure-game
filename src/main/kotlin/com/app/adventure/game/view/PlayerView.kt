package com.app.adventure.game.view

import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.NotDisposableItem

class PlayerView(val fightStatsView : Map<String,Int>,
                 val resourcesView : Map<String,Double>,
                 val disposableItemsView: Map<String, DisposableItem>,
                 val notDisposableItemsView: Map<String, NotDisposableItem>,
                 val wearingItems: Map<String, NotDisposableItem>,
                 val experienceView : ExperienceView,
                 val levelUpPoints : Int) {

}