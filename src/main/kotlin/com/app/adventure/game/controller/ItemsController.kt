package com.app.adventure.game.controller

import com.app.adventure.game.model.characters.Player
import com.app.adventure.game.model.item.DisposableItem
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
    val disposableItems : Map<String,DisposableItem>,
    val notDisposableItems : Map<String, NotDisposableItem>
)
{
    @PostMapping("/getDisposableItems")
    @ResponseBody
    fun getAllDisposableItems(): Map<String,DisposableItem> {
        return disposableItems
    }

    @PostMapping("/getAllItems")
    @ResponseBody
    fun getAllItems(): Map<String, Item> {
        return notDisposableItems.plus(disposableItems)
    }

    @PostMapping("/buyItem")
    @ResponseBody
    fun buyItems(@RequestBody name :String) {
        val item : Item? = notDisposableItems.plus(disposableItems)[name]
        if (item != null && item.bayCost <= (player.getResources()[ResourceName.GOLD]?.getValue() ?: 0.0)) {
            player.getResources()[ResourceName.GOLD]?.pay(item.bayCost)
            player.inventory.addItem(item)
        }
    }

    @PostMapping("/putOnItem")
    @ResponseBody
    fun putOnItem(@RequestBody name :String) {
        val item : NotDisposableItem? = notDisposableItems[name];
        if (item != null) {
            player.putOnItem(item)
        }
    }

    @PostMapping("/getInventoryItems")
    @ResponseBody
    fun getInventoryItems(): Map<String,Item>  {
        return player.inventory.getItems()
    }
}