package com.app.adventure.game.model.dungeon

import com.app.adventure.game.model.characters.Monster
import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.NotDisposableItem
import com.app.adventure.game.model.resources.ResourceName

class DungeonField (val monster : Monster?,
                    val fieldType: FieldType
){
    val resources : Map<ResourceName,Double>? = null
    val disposableItems : List<DisposableItem>? = null
    val notDisposableItems : List<NotDisposableItem>? = null
}