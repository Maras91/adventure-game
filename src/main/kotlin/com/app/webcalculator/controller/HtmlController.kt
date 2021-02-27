package com.app.webcalculator.controller

import com.app.webcalculator.model.FightStats
import com.app.webcalculator.model.Resources
import com.app.webcalculator.model.Player
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HtmlController {
    var number :Double = 0.0

    @GetMapping("/")
    fun blog(model : Model): String {
        model.addAttribute("gold","0")
        return "blog"
    }

    @PostMapping("/json",produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun json(@RequestBody fightStats :FightStats): Resources {
        var goblin :Player = Player(Resources(3.0,0.0,6.0),FightStats(5,50,3))
        var player :Player = Player(Resources(0.0,0.0,0.0),fightStats)
        var hpLeft : Int = player.fight(goblin.getFightStats())
        return if (hpLeft > 0) {
            Resources(3.0,0.0, hpLeft.toDouble())
        } else {
            Resources(0.0,0.0,0.0)
        }
    }
}



