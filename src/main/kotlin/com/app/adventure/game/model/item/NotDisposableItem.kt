package com.app.adventure.game.model.item

class NotDisposableItem (val itemType :ItemType,
                         override val attributes : Map<ItemAttribute,Int>,
                         override val name: String,
                         override val bayCost: Double,
                         override val sellCost: Double
                        ) : Item {

}