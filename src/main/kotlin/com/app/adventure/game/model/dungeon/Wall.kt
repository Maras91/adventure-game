package com.app.adventure.game.model.dungeon


class Wall : MapField{
    override val fieldType = FiledType.WALL
    override val monster = null
}