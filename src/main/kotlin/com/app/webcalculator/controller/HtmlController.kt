package com.app.webcalculator.controller

import com.app.webcalculator.model.characters.MonsterJsonObject
import com.app.webcalculator.model.fight.statistics.FightStats
import com.app.webcalculator.model.resources.Resources
import com.app.webcalculator.model.characters.Player
import com.app.webcalculator.model.characters.PlayerJsonObject
import com.app.webcalculator.model.fight.CombatSimulator
import com.app.webcalculator.model.fight.statistics.FightStatsJsonConverter
import com.app.webcalculator.model.item.PotionJsonObject
import com.app.webcalculator.model.resources.ResourcesJsonConverter
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
    private lateinit var fightStatsJsonConverter : FightStatsJsonConverter
    @Autowired
    private lateinit var resourcesJsonConverter : ResourcesJsonConverter
    @Autowired
    private lateinit var combatSimulator: CombatSimulator


    var number :Double = 0.0
    var player : Player = Player(Resources(0.0,0.0,0.0), FightStats(7,50,3,0))

    @GetMapping("/")
    fun start(): String {
        return "blog"
    }

    @PostMapping("/fight",produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun fight(@RequestBody goblin : MonsterJsonObject): PlayerJsonObject {
        var hpLeft : Int = combatSimulator.fight(player,goblin.getFightStatsJsonObject())
        if (hpLeft >0) {
            player.getResources().addResources(goblin.getResourcesJsonObject())
            player.getFightStats().addExperience(goblin.getFightStatsJsonObject().getExperience())
        }
        return PlayerJsonObject(fightStatsJsonConverter.convertFromFightStatsToJson(player.getFightStats()),resourcesJsonConverter.converterResourcesToJson(player.getResources()))
    }
    @PostMapping("/getPotion")
    @ResponseBody
    fun getPotion () : PlayerJsonObject {
        val potionJsonObject : PotionJsonObject = PotionJsonObject()
        player.getResources().payGold(potionJsonObject.cost)
        player.getFightStats().hpRecovery(potionJsonObject.hpRecovery)
        return PlayerJsonObject(fightStatsJsonConverter.convertFromFightStatsToJson(player.getFightStats()),resourcesJsonConverter.converterResourcesToJson(player.getResources()))
    }
}



