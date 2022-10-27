package com.app.adventure.game.controller

import com.app.adventure.game.model.characters.Player
import com.app.adventure.game.model.fight.BattleProperties
import com.app.adventure.game.view.PlayerView
import com.app.adventure.game.model.fight.CombatSimulator
import com.app.adventure.game.model.fight.experience.StatsUp
import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.view.ExperienceView
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
            combatSimulator.fight(player,monster.getStatsView())
            if (player.getHp().getCurrentHp() >0) {
                player.win(monster)
            }
        }
    }

    @PostMapping("/levelUp",produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun levelUp(@RequestBody statsToUp : Map<String,Int>) : Map<String,Int> {
        //TODO is stats up refactor needed there
        //TODO how can I to prevent wrong statistic name?
        val allStatNames = statsToUp.keys.filter { StatisticsName.values().map { name -> name.attributeName }.contains(it)}
        val statsUp = StatsUp(
        allStatNames.associate{ (StatisticsName.createByAttributeName(it)) to (statsToUp[it] ?: 0) }
        )
        player.addStatsUp(statsUp)
        return player.getStats().map { (k,v) -> k.attributeName to v.getValue() }.toMap()
    }

    @PostMapping("/adventureStats")
    @ResponseBody
    fun getPlayer() : PlayerView {
        return PlayerView(
            player.getStats().map { (k,v) -> k.attributeName to v.getValue() }
                .toMap().plus("currentHp" to player.getHp().getCurrentHp()),
            ResourcesView(player.getResources()),
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



