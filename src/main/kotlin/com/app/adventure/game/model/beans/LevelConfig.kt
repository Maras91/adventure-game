package com.app.adventure.game.model.beans

import com.app.adventure.game.model.fight.experience.LevelProperties
import com.app.adventure.game.model.yml.properties.LevelYml
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class LevelConfig @Autowired constructor(val levelYml :LevelYml){

    @Bean
    fun createLevelProperties(): LevelProperties{
        return LevelProperties( TreeSet(levelYml.experienceRequiredPerLevel?.filterNotNull() ?: emptySet()),levelYml.pointsPerLevel ?: 0)
    }
}