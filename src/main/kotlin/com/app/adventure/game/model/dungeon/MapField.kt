package com.app.adventure.game.model.dungeon


interface MapField {
    val fieldType :FieldType
    val adventure :DungeonField?
    fun isPlayerIn(): Boolean
    fun setPlayer()
}