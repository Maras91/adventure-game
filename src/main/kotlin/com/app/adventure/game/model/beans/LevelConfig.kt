package com.app.adventure.game.model.beans

import com.app.adventure.game.model.fight.experience.LevelProperties
import com.app.adventure.game.model.yaml.properties.LevelYaml
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class LevelConfig @Autowired constructor(val levelYaml : LevelYaml){

    @Bean
    fun createLevelProperties(): LevelProperties{
        return LevelProperties( TreeSet(levelYaml.experienceRequiredPerLevel?.filterNotNull() ?: emptySet()),levelYaml.pointsPerLevel ?: 0)
    }
}