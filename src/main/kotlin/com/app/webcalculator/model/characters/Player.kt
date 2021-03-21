package com.app.webcalculator.model.characters

import com.app.webcalculator.model.fight.statistics.FightStats
import com.app.webcalculator.model.resources.Resources

class Player(private var resources: Resources, private var fightStats: FightStats) {

    private val levelExperienceRequired: List<Int> = listOf( 0, 1000, 3000, 5000, 8000, 11000, 15000, 19000, 24000, 30000, 38000)
    
    private val experience :MutableList<Int> = mutableListOf(0)

    public fun getResources() : Resources {
        return resources
    }

    public fun getFightStats() : FightStats {
        return fightStats
    }

    public fun addExperience (experience : Int) {
        this.experience.add(experience)
    }

    fun getExperience(): Int {
        return experience.reduce{x, x2->x+x2}
    }

    fun calculateLevel(experience :Int) : Int {
        for (requirementExperience in  levelExperienceRequired) {
            if (experience < requirementExperience) {
                return levelExperienceRequired.indexOf(requirementExperience)
            }
        }
        return levelExperienceRequired.size
    }

    fun isNextLevel (experience :Int) :Boolean {
        return calculateLevel(getExperience()) < calculateLevel(getExperience() + experience)
    }

    fun nextLevelExperience() : String {
        for (requirementExperience in  levelExperienceRequired) {
            if (getExperience() < requirementExperience) {
                return requirementExperience.toString()
            }
        }
        return "Max"
    }

    fun levelUp() {
        getFightStats().getStrength().addWhenLevelUp(2)
        getFightStats().getHp().addWhenLevelUp(10)
        getFightStats().getArmor().addWhenLevelUp(1)
    }
}