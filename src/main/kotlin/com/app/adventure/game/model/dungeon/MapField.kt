package com.app.adventure.game.model.dungeon

import com.app.adventure.game.model.characters.Monster

interface MapField {
    val fieldType :FiledType
    val monster :Monster?
}