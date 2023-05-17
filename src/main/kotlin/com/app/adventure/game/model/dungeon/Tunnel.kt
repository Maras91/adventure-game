package com.app.adventure.game.model.dungeon

class Tunnel(override val adventure: DungeonField) : MapField{
    var playerPresent :Boolean = false
    override val fieldType = FieldType.TUNNEL
    override fun isPlayerIn(): Boolean {
        return  playerPresent
    }

    override fun setPlayer() {
        playerPresent = true
    }
    fun removePlayer() {
        playerPresent = false
    }

}