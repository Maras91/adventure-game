package com.app.adventure.game.model.beans

import com.app.adventure.game.model.characters.Player
import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.LevelProperties
import com.app.adventure.game.model.fight.statistics.CharacterStats
import com.app.adventure.game.model.fight.statistics.StatisticsFactory
import com.app.adventure.game.model.resources.ResourceValue
import com.app.adventure.game.model.resources.ResourceName
import com.app.adventure.game.model.yaml.properties.PlayerResourcesValuesYaml
import com.app.adventure.game.model.yaml.properties.PlayerStatisticsValuesYaml
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PlayerConfig @Autowired constructor (
    val startStatsValues: PlayerStatisticsValuesYaml,
    val startResourcesValues: PlayerResourcesValuesYaml,
    val levelProperties: LevelProperties,
    val statisticsFactory: StatisticsFactory
    )
{
    @Bean
    fun createPlayer() : Player {
        return Player(
            startResourcesValues.resources.mapKeys {
                ResourceName.getResourceByName(it.key)
            }.mapValues {
                ResourceValue(it.value) } as MutableMap<ResourceName, ResourceValue>,
            CharacterStats(startStatsValues.statistics,statisticsFactory),
            mutableMapOf(), //TODO add the ability to get stat items
            Experience(levelProperties)
        )
    }

}