package com.app.adventure.game.model.item

class DisposableItem (val hasPermanentEffect : Boolean,
                      val timeInTurns : Int,
                      val itemEffects : Map<ItemEffects,Int>,
                      override val name: String,
                      override val bayCost: Double,
                      override val sellCost: Double
                      ): Item{
    override var amount = 1;
    override fun increaseAmount() {
        amount++
    }

    override fun decreaseAmount() {
        amount--
    }

}