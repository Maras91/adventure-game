package com.app.adventure.game.controller

import com.app.adventure.game.model.characters.Monster
import com.app.adventure.game.model.fight.statistics.FightStats
import com.app.adventure.game.model.resources.Resources
import com.app.adventure.game.model.characters.Player
import com.app.adventure.game.view.PlayerView
import com.app.adventure.game.model.fight.CombatSimulator
import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.StatsUp
import com.app.adventure.game.model.fight.statistics.value.Armor
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Strength
import com.app.adventure.game.model.item.PotionJsonObject
import com.app.adventure.game.view.ExperienceView
import com.app.adventure.game.view.FightStatsView
import com.app.adventure.game.view.ResourcesView
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
                ExperienceView(player.getExperience()),
                player.getExperience().allStatsPointsToSpend() -
                        player.getFightStats().allSpentLevelPoints())
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
                ExperienceView(player.getExperience()),
                player.getExperience().allStatsPointsToSpend() -
                        player.getFightStats().allSpentLevelPoints())
    }

    @PostMapping("/levelUp",produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun levelUp(@RequestBody statsUp : StatsUp) : FightStatsView {
        player.addStatsUp(statsUp)
        return FightStatsView(player.getFightStats())
    }
}



