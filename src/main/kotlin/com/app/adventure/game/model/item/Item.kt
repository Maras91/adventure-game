package com.app.adventure.game.model.item

interface Item {
    val name: String
    val bayCost: Double
    val sellCost: Double
    var amount: Int

    fun increaseAmount()
    fun decreaseAmount()
}