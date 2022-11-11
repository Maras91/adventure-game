package com.app.adventure.game.controller

import com.app.adventure.game.model.characters.Player
import com.app.adventure.game.model.fight.BattleProperties
import com.app.adventure.game.view.PlayerView
import com.app.adventure.game.model.fight.CombatSimulator
import com.app.adventure.game.model.fight.experience.LevelService
import com.app.adventure.game.view.ExperienceView
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
    val levelService: LevelService
    )
{

    @PostMapping("/fight",produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun fight(@RequestBody monsterName : String) {
        val monster = battleProperties.monsters[monsterName]
        if (monster != null) {
            combatSimulator.fight(player,monster.getStatsView())
            if (player.getHp().getCurrentHp() >0) {
                player.win(monster)
            }
        }
    }

    @PostMapping("/levelUp",produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun levelUp(@RequestBody statsToUp : Map<String,Int>) : Map<String,Int> {
        //TODO how can I to prevent wrong statistic name?
        levelService.levelUp(statsToUp, player)
        return player.getStats().map { (k,v) -> k.attributeName to v.getValue() }.toMap()
    }

    @PostMapping("/adventureStats")
    @ResponseBody
    fun getPlayer() : PlayerView {
        //TODO Do I need to add some mappers?
        return PlayerView(
            player.getStats().map { (k,v) -> k.attributeName to v.getValue() }
                .toMap().plus("currentHp" to player.getHp().getCurrentHp()),
            player.getResources().map { (k,v) -> k.rscName to v.getValue() }.toMap(),
            player.inventory.getAllDisposableItem(),
            player.inventory.getAllNotDisposableItem(),
            ExperienceView(player.getExperience()),
            player.getExperience().allStatsPointsToSpend() -
                    player.allSpentLevelPoints())
    }

    @PostMapping("/allMonsters")
    @ResponseBody
    fun getAllMonsters() : List<String> {
        return battleProperties.monsters.keys.toList()
    }
}



