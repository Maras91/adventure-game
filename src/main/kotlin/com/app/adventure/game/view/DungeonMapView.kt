package com.app.adventure.game.view

import com.app.adventure.game.model.dungeon.DungeonMapField
import com.app.adventure.game.model.dungeon.TunnelField
import com.app.adventure.game.model.dungeon.FieldType
import com.app.adventure.game.model.dungeon.PlayerPosition

class DungeonMapView(
    val playerPosition:PlayerPosition,
    val dungeonMap: List<List<FieldType>>,
    val availableActions: PlayerAvailableActionsView,
    val currentAdventure: DungeonMapField
) {

}