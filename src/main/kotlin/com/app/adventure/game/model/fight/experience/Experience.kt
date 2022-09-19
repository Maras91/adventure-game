package com.app.adventure.game.model.fight.experience

import org.springframework.beans.factory.annotation.Autowired

class Experience @Autowired constructor(val levelProperties: LevelProperties){

    private val experience :MutableList<Int> = mutableListOf(0)

    fun addExperience (experience : Int) {
        this.experience.add(experience)
    }

    fun getValue(): Int {
        return experience.reduce{x, x2->x+x2}
    }

    fun calculateLevel(experience :Int) : Int {
        for (requirementExperience in  levelProperties.experienceRequiredPerLevel) {
            if (experience < requirementExperience) {
                return levelProperties.experienceRequiredPerLevel.indexOf(requirementExperience)+1
            }
        }
        return levelProperties.experienceRequiredPerLevel.size+1
    }

    fun isNextLevel (experience :Int) :Boolean {
        return calculateLevel(getValue()) < calculateLevel(getValue() + experience)
    }

    fun nextLevelExperience() : String {
        for (requirementExperience in  levelProperties.experienceRequiredPerLevel) {
            if (getValue() < requirementExperience) {
                return requirementExperience.toString()
            }
        }
        return "Max"
    }

    fun allStatsPointsToSpend() : Int {
        return (calculateLevel(getValue()) -1) * levelProperties.pointsPerLevel
    }
}