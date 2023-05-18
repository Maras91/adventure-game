package com.app.adventure.game.model.dungeon

import com.app.adventure.game.model.characters.Monster
import com.app.adventure.game.model.fight.BattleProperties
import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.NotDisposableItem
import com.app.adventure.game.model.resources.ResourceName
import org.springframework.beans.factory.annotation.Autowired

class DungeonField (val monsterName: String?,
                    val battleProperties: BattleProperties,
                    val fieldType: FieldType
) {

    val monster : Monster? = battleProperties.monsters[monsterName]
    val resources : Map<ResourceName,Double>? = null
    val disposableItems : List<DisposableItem>? = null
    val notDisposableItems : List<NotDisposableItem>? = null
}