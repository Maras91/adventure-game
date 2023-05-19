package com.app.adventure.game.model.beans

import com.app.adventure.game.model.dungeon.DungeonField
import com.app.adventure.game.model.dungeon.DungeonMap
import com.app.adventure.game.model.dungeon.FieldType
import com.app.adventure.game.model.dungeon.PlayerPosition
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
        val lineOne: Array<DungeonField> = arrayOf(
            DungeonField("goblin", battleProperties, FieldType.TUNNEL),
            DungeonField("goblin",battleProperties,FieldType.TUNNEL),
            DungeonField("goblin",battleProperties,FieldType.TUNNEL),
            DungeonField(null,battleProperties,FieldType.WALL),
            DungeonField(null,battleProperties,FieldType.TUNNEL))
        val lineTwo: Array<DungeonField> = arrayOf(
            DungeonField(null,battleProperties,FieldType.TUNNEL),
            DungeonField(null,battleProperties,FieldType.WALL),
            DungeonField(null,battleProperties,FieldType.TUNNEL),
            DungeonField(null,battleProperties,FieldType.TUNNEL),
            DungeonField(null,battleProperties, FieldType.TUNNEL))
        val lineThree: Array<DungeonField> = arrayOf(
            DungeonField(null,battleProperties,FieldType.WALL),
            DungeonField("goblin",battleProperties,FieldType.TUNNEL),
            DungeonField("goblin",battleProperties,FieldType.TUNNEL),
            DungeonField(null,battleProperties,FieldType.WALL),
            DungeonField(null,battleProperties,FieldType.WALL),)
        return DungeonMap(arrayOf(lineOne,lineTwo,lineThree))
    }
}