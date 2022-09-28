package com.app.adventure.game.model.item

class DisposableItem (val attributes : Map<ItemAttribute,Int>,
                      val hasPermanentEffect : Boolean,
                      val timeInTurns : Int,
                      override val name: String,
                      override val bayCost: Double,
                      override val sellCost: Double
): Item{

}