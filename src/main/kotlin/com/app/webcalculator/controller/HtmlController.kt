package com.app.webcalculator.controller

import com.app.webcalculator.model.characters.Monster
import com.app.webcalculator.model.fight.statistics.FightStats
import com.app.webcalculator.model.resources.Resources
import com.app.webcalculator.model.characters.Player
import com.app.webcalculator.view.PlayerView
import com.app.webcalculator.model.fight.CombatSimulator
import com.app.webcalculator.model.fight.experience.Experience
import com.app.webcalculator.model.fight.statistics.value.Armor
import com.app.webcalculator.model.fight.statistics.value.Hp
import com.app.webcalculator.model.fight.statistics.value.Strength
import com.app.webcalculator.model.item.PotionJsonObject
import com.app.webcalculator.view.ExperienceView
import com.app.webcalculator.view.FightStatsView
import com.app.webcalculator.view.ResourcesView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HtmlController {

    @Autowired
    private lateinit var combatSimulator: CombatSimulator


    var player : Player = Player(Resources(0.0,0.0,0.0),
            FightStats(Strength(7,0,0),
                       Hp(50,0,0,0),
                       Armor(3,0,0)), Experience())

    @GetMapping("/")
    fun start(): String {
        return "blog"
    }

    @PostMapping("/fight",produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun fight(@RequestBody monster : Monster): PlayerView {
        val hpLeft : Int = combatSimulator.fight(player,monster.getFightStats())
        if (hpLeft >0) {
            player.win(monster)
        }

        return PlayerView(FightStatsView(player.getFightStats()),
                ResourcesView(player.getResources()),
                ExperienceView(player.getExperience()))
    }

    @PostMapping("/getPotion")
    @ResponseBody
    fun getPotion () : PlayerView {
        val potionJsonObject : PotionJsonObject = PotionJsonObject()
        player.getResources().payGold(potionJsonObject.cost)
        player.getFightStats().hpRecovery(potionJsonObject.hpRecovery)
        return PlayerView(
                FightStatsView(player.getFightStats()),
                ResourcesView(player.getResources()),
                ExperienceView(player.getExperience()))
    }
}



