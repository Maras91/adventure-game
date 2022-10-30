package com.app.adventure.game.model.resources

import com.app.adventure.game.model.exceptions.IncorrectYamlPropertiesException
import com.app.adventure.game.model.fight.statistics.StatisticsName

enum class ResourceName (val rscName: String)  {
    GOLD("gold"),
    IRON("iron"),
    FOOD("food");

    companion object {
        fun getResourceName(name: String): ResourceName {
            return when (name.toLowerCase()) {
                "gold" -> GOLD
                "iron" -> IRON
                "food" -> FOOD
                else -> throw IncorrectYamlPropertiesException(
                    name,
                    ResourceName.values().map { rsc -> rsc.rscName }.toString()
                )
            }
        }
    }
}