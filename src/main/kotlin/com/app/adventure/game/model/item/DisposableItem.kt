package com.app.adventure.game.model.item

class DisposableItem (val hasPermanentEffect : Boolean,
                      val timeInTurns : Int,
                      override val attributes : Map<ItemAttribute,Int>,
                      override val name: String,
                      override val bayCost: Double,
                      override val sellCost: Double
                      ): Item{

}