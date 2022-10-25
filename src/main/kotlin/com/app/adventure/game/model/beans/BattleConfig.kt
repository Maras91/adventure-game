package com.app.adventure.game.model.beans

import com.app.adventure.game.model.characters.Monster
import com.app.adventure.game.model.fight.BattleProperties
import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.model.yaml.properties.BattleYaml
import com.app.adventure.game.view.ResourcesView

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BattleConfig @Autowired constructor( val battleConfigYml: BattleYaml) {

    @Bean
    fun createBattleProperties() : BattleProperties{
        //TODO catch IllegalArgumentException
        //TODO valueOf(stat.key.toUpperCase()) needs corrections
        val monsters: Map<String,Monster> =
            battleConfigYml.monsters?.filter { monster -> monster.name != null }?.associate {
                it.name!! to
                        Monster(
                            it.statistics?.mapKeys {stat -> StatisticsName.valueOf(stat.key.toUpperCase()) } ?: emptyMap(),
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