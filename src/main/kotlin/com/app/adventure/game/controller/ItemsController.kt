package com.app.adventure.game.controller

import com.app.adventure.game.model.characters.Player
import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.ItemAttribute
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ItemsController @Autowired constructor(
    var player : Player,
    val items : Map<String,DisposableItem>
)
{
    @PostMapping("/getItems")
    @ResponseBody
    fun getAllItems(): Map<String,DisposableItem> {
        return items
    }

    @PostMapping("/buyItem")
    @ResponseBody
    fun buyItems(@RequestBody name :String) {
        val item : DisposableItem? = items[name];
        if (item != null && item.bayCost <= player.getResources().getGold()) {
            player.getResources().payGold(item.bayCost)
            player.getFightStats().addStatsFromDisposableItem(item)
        }
    }
}