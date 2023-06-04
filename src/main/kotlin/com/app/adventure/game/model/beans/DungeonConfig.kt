package com.app.adventure.game.model.beans

import com.app.adventure.game.model.dungeon.*
import com.app.adventure.game.model.dungeon.services.RandomMapGeneratorService
import com.app.adventure.game.model.fight.BattleProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DungeonConfig @Autowired constructor(val randomMapGeneratorService: RandomMapGeneratorService){

    @Bean
    fun getPlayerPosition(): PlayerPosition {
        return PlayerPosition(0,0);
    }

    @Bean
    fun getDungeonMap(): DungeonMap {
        //TODO when width is greater then 8 map is not displayed correctly in front end
        //TODO height and width have to set as properties
        return DungeonMap(randomMapGeneratorService.generateRandomMap(7,7))
    }
}