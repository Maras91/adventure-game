package com.app.adventure.game.model.dungeon

class WallField: DungeonMapField {
    override fun getFieldType(): FieldType {
        return FieldType.WALL
    }

    override fun removeMonster() {
        //TODO throw error
    }

}