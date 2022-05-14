package com.app.adventure.game.model.beans

import com.app.adventure.game.controller.AdventureController
import com.app.adventure.game.model.characters.Player
import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.statistics.FightStats
import com.app.adventure.game.model.fight.statistics.value.Armor
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Strength
import com.app.adventure.game.model.resources.Resources
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.annotation.RequestScope

@Configuration
class Config {
    @Bean
    fun createPlayer() : Player {
        return Player(
            Resources(0.0,0.0,0.0),
            FightStats(
                Strength(7,0,0),
                Hp(50,0,0,0),
                Armor(3,0,0)
            ), Experience()
        )
    }
}