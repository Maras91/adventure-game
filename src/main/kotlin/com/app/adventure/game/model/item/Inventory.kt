package com.app.adventure.game.model.item

class Inventory {
    private val inventory: MutableMap<String,Item> = mutableMapOf()

    fun addItem(item: Item) {
        if (inventory.containsKey(item.name)) {
            inventory[item.name]?.increaseAmount()
        } else {
            inventory[item.name] = item
        }
    }

    fun getItem(name: String): Item? {
        return inventory[name]
    }

    fun removeItem(name: String) : Item? {
        val amount = inventory[name]?.amount
        return if(amount != null && amount > 1) {
            inventory[name]?.decreaseAmount()
            inventory[name]
        } else {
            inventory.remove(name)
        }
    }
    fun getItems(): Map<String,Item> {
        return inventory.toMap()
    }
}