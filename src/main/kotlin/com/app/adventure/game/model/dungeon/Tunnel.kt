package com.app.adventure.game.model.dungeon

import com.app.adventure.game.model.characters.Monster

class Tunnel(override val monster : Monster?) : MapField{
    override val fieldType = FiledType.TUNNEL

}