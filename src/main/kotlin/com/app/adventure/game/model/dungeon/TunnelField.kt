package com.app.adventure.game.model.dungeon

import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.NotDisposableItem
import com.app.adventure.game.model.resources.ResourceName

class TunnelField (var monsterName: String?): DungeonMapField {
    val resources : Map<ResourceName,Double>? = null
    val disposableItems : List<DisposableItem>? = null
    val notDisposableItems : List<NotDisposableItem>? = null

    override fun removeMonster() {
        monsterName = null
    }
    override fun getFieldType(): FieldType {
        return FieldType.TUNNEL
    }
}