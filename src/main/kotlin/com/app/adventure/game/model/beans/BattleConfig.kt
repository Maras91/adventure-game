package com.app.adventure.game.model.beans

import com.app.adventure.game.model.characters.Monster
import com.app.adventure.game.model.fight.BattleProperties
import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.model.resources.Resource
import com.app.adventure.game.model.resources.ResourceName
import com.app.adventure.game.model.yaml.properties.BattleYaml

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
                            it.statistics?.mapKeys {
                                    stat -> StatisticsName.getAttributeByName(stat.key.toLowerCase())
                            } ?: emptyMap(),
                            it.resources?.mapKeys {
                                ResourceName.getResourceName(it.key)
                            }?.mapValues {
                                Resource(it.key,it.value)
                            } ?: emptyMap(),
                            it.experience ?: 0
                        )
            } ?: emptyMap()

        return BattleProperties(monsters)
    }
}