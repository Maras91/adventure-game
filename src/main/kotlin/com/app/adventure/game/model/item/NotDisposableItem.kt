package com.app.adventure.game.model.item

import com.app.adventure.game.model.fight.statistics.StatisticsName

class NotDisposableItem (val itemType :ItemType,
                         val attributes : Map<StatisticsName,Int>,
                         override val name: String,
                         override val bayCost: Double,
                         override val sellCost: Double
                        ) : Item {

}