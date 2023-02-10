package com.app.adventure.game.model.dungeon

import com.app.adventure.game.model.characters.Monster

class PlayerField(override val monster : Monster?) : MapField {
    override val fieldType = FiledType.PLAYER
}