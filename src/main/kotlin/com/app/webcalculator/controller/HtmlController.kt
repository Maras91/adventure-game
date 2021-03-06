package com.app.webcalculator.controller

import com.app.webcalculator.model.FightStats
import com.app.webcalculator.model.Resources
import com.app.webcalculator.model.Player
import com.app.webcalculator.model.Potion
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HtmlController {
    var number :Double = 0.0
    var player :Player = Player(Resources(0.0,0.0,0.0),FightStats(7,50,3))

    @GetMapping("/")
    fun start(): String {
        return "blog"
    }

    @PostMapping("/fight",produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun fight(@RequestBody goblin :Player): Player {
        var hpLeft : Int = player.fight(goblin.getFightStats())
        if (hpLeft >0) {
            player.getResources().addResources(goblin.getResources())
            player.getFightStats().addExperience(goblin.getFightStats().getExperience())
        }
        return player
    }
    @PostMapping("/getPotion")
    @ResponseBody
    fun getPotion () : Player {
        val potion : Potion = Potion()
        player.getResources().payGold(potion.cost)
        player.getFightStats().hpRecovery(potion.hpRecovery)
        return player
    }
}



