package com.app.adventure.game.controller

import com.app.adventure.game.model.characters.Player
import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.Inventory
import com.app.adventure.game.model.item.Item
import com.app.adventure.game.model.item.NotDisposableItem
import com.app.adventure.game.model.resources.ResourceName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ItemsController @Autowired constructor(
    var player : Player,
    val allItems : Inventory
)
{
    @PostMapping("/getAllItems")
    @ResponseBody
    fun getAllItems(): Map<String, Item> {
        return allItems.getItems()
    }

    @PostMapping("/buyItem")
    @ResponseBody
    fun buyItems(@RequestBody name :String) {
        val item : Item? = allItems.getItems()[name]
        if (item != null && item.bayCost <= (player.getResources()[ResourceName.GOLD]?.getValue() ?: 0.0)) {
            player.getResources()[ResourceName.GOLD]?.pay(item.bayCost)
            player.inventory.addItem(item)
        }
    }

    @PostMapping("/useItem")
    @ResponseBody
    fun useItem(@RequestBody name :String) {
        val item : Item? = allItems.getItems()[name];
        if (item != null) {
            if (item is NotDisposableItem) {
                player.putOn(item)
            }
            if (item is DisposableItem) {
                player.useItem(item)
            }
        }
    }

    @PostMapping("/getInventoryItems")
    @ResponseBody
    fun getInventoryItems(): Map<String,Item>  {
        return player.inventory.getItems()
    }
}