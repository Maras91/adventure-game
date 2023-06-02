package com.app.adventure.game.model.beans

import com.app.adventure.game.model.dungeon.*
import com.app.adventure.game.model.fight.BattleProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DungeonConfig @Autowired constructor(final val battleProperties: BattleProperties){

    @Bean
    fun getPlayerPosition(): PlayerPosition {
        return PlayerPosition(1,0);
    }

    @Bean
    fun getDungeonMap(): DungeonMap {
        val lineOne: Array<DungeonMapField> = arrayOf(
            TunnelField("goblin"),
            TunnelField("goblin"),
            TunnelField("goblin"),
            WallField(),
            TunnelField(null)
        )
        val lineTwo: Array<DungeonMapField> = arrayOf(
            TunnelField(null),
            WallField(),
            TunnelField(null),
            TunnelField(null),
            TunnelField(null)
        )
        val lineThree: Array<DungeonMapField> = arrayOf(
            WallField(),
            TunnelField("goblin"),
            TunnelField("goblin"),
            WallField(),
            TunnelField(null)
        )
        return DungeonMap(arrayOf(lineOne,lineTwo,lineThree))
    }
}