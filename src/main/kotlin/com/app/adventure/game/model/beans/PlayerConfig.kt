package com.app.adventure.game.model.beans

import com.app.adventure.game.model.characters.Player
import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.LevelProperties
import com.app.adventure.game.model.fight.statistics.FightStats
import com.app.adventure.game.model.fight.statistics.value.Armor
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Strength
import com.app.adventure.game.model.resources.Resources
import com.app.adventure.game.model.yaml.properties.PlayerResourcesValuesYaml
import com.app.adventure.game.model.yaml.properties.PlayerStatisticsValuesYaml
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PlayerConfig @Autowired constructor (
    val startStatsValues: PlayerStatisticsValuesYaml,
    val startResourcesValues: PlayerResourcesValuesYaml,
    val levelProperties: LevelProperties
    )
{
    @Bean
    fun createPlayer() : Player {
        return Player(
            Resources((startResourcesValues.gold ?: 0.0),
                startResourcesValues.iron ?: 0.0,
                startResourcesValues.meat ?: 0.0),
            FightStats(
                Strength(startStatsValues.strength ?:0),
                Hp(startStatsValues.maxHP ?: 0),
                Armor(startStatsValues.armor ?: 0)
            ), Experience(levelProperties)
        )
    }

}