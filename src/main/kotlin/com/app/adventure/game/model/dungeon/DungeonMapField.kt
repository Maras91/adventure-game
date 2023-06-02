package com.app.adventure.game.model.dungeon

interface DungeonMapField {
    fun getFieldType(): FieldType
    fun removeMonster()
}