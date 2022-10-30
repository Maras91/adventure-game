package com.app.adventure.game.model.fight.statistics

import com.app.adventure.game.model.exceptions.IncorrectYamlPropertiesException

enum class StatisticsName (val attributeName: String){
    STRENGTH("strength"),
    ARMOR("armor"),
    HP("hp");

    companion object {
        fun getAttributeByName(name: String): StatisticsName {
            return when (name) {
                "strength" -> STRENGTH
                "armor" -> ARMOR
                "hp" -> HP
                else -> throw IncorrectYamlPropertiesException(
                    name,
                    values().map { stat -> stat.attributeName }.toString()
                )
            }
        }
    }
}