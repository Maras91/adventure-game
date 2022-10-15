package com.app.adventure.game.model.beans

import com.app.adventure.game.model.characters.Monster
import com.app.adventure.game.model.fight.BattleProperties
import com.app.adventure.game.model.yaml.properties.BattleYaml
import com.app.adventure.game.view.FightStatsView
import com.app.adventure.game.view.ResourcesView

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BattleConfig @Autowired constructor( val battleConfigYml: BattleYaml) {

    @Bean
    fun createBattleProperties() : BattleProperties{
        val monsters: Map<String,Monster> =
            battleConfigYml.monsters?.filter { monster -> monster.name != null }?.associate {
                it.name!! to
                        Monster(
                            FightStatsView(
                                it.statistics?.strength ?: 0,
                                it.statistics?.maxHP ?: 0,
                                it.statistics?.maxHP ?: 0,
                                it.statistics?.armor ?: 0
                            ),
                            ResourcesView(
                                it.resources?.gold ?: 0.0,
                                it.resources?.iron ?: 0.0,
                                it.resources?.meat ?: 0.0
                            ),
                            it.experience ?: 0
                        )
            } ?: emptyMap()
        return BattleProperties(monsters)
    }
}