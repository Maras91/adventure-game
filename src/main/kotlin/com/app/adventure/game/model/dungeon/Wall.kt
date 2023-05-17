package com.app.adventure.game.model.dungeon


class Wall : MapField{
    override val fieldType = FieldType.WALL
    override val adventure = null
    override fun isPlayerIn(): Boolean {
        return false
    }

    override fun setPlayer() {
        //TODO method have to throws a exception
    }

}