package com.app.adventure.game.model.item

interface Item {
    val attributes : Map<ItemAttribute,Int>
    val name: String
    val bayCost: Double
    val sellCost: Double
}