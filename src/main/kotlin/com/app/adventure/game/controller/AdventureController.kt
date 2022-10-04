package com.app.adventure.game.controller

import com.app.adventure.game.model.characters.Player
import com.app.adventure.game.model.fight.BattleProperties
import com.app.adventure.game.view.PlayerView
import com.app.adventure.game.model.fight.CombatSimulator
import com.app.adventure.game.model.fight.experience.StatsUp
import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.ItemAttribute
import com.app.adventure.game.view.ExperienceView
import com.app.adventure.game.view.FightStatsView
import com.app.adventure.game.view.ResourcesView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class AdventureController @Autowired constructor(
    val combatSimulator: CombatSimulator,
    val battleProperties: BattleProperties,
    var player : Player,
    )
{

    @PostMapping("/fight",produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun fight(@RequestBody monsterName : String) {
        val monster = battleProperties.monsters[monsterName]
        if (monster != null) {
            val hpLeft : Int = combatSimulator.fight(player,monster.getFightStats())
            if (hpLeft >0) {
                player.win(monster)
            }
        }
    }

    @PostMapping("/levelUp",produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun levelUp(@RequestBody statsUp : StatsUp) : FightStatsView {
        player.addStatsUp(statsUp)
        return FightStatsView(player.getFightStats())
    }

    @PostMapping("/adventureStats")
    @ResponseBody
    fun getPlayer() : PlayerView {
        return PlayerView(
            FightStatsView(player.getFightStats()),
            ResourcesView(player.getResources()),
            ExperienceView(player.getExperience()),
            player.getExperience().allStatsPointsToSpend() -
                    player.getFightStats().allSpentLevelPoints())
    }

    @PostMapping("/allMonsters")
    @ResponseBody
    fun getAllMonsters() : List<String> {
        return battleProperties.monsters.keys.toList()
    }
}



