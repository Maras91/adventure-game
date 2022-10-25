package com.app.adventure.game.model.fight.statistics

enum class StatisticsName (val attributeName: String){
    STRENGTH("strength"),
    ARMOR("armor"),
    HP("hp");

    companion object {
        fun createByAttributeName(name: String): StatisticsName? {
            return when (name) {
                "strength" -> STRENGTH
                "armor" -> ARMOR
                "hp" -> HP
                else -> null
            }
        }
    }
}