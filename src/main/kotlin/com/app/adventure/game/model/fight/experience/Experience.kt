package com.app.adventure.game.model.fight.experience

class Experience {
    private val pointsPerLevel = 4
    private val levelExperienceRequired: List<Int> = listOf( 0, 1000, 3000, 5000, 8000, 11000, 15000, 19000, 24000, 30000, 38000)
    private val experience :MutableList<Int> = mutableListOf(0)

    public fun addExperience (experience : Int) {
        this.experience.add(experience)
    }

    public fun getValue(): Int {
        return experience.reduce{x, x2->x+x2}
    }

    public fun calculateLevel(experience :Int) : Int {
        for (requirementExperience in  levelExperienceRequired) {
            if (experience < requirementExperience) {
                return levelExperienceRequired.indexOf(requirementExperience)
            }
        }
        return levelExperienceRequired.size
    }

    fun isNextLevel (experience :Int) :Boolean {
        return calculateLevel(getValue()) < calculateLevel(getValue() + experience)
    }

    fun nextLevelExperience() : String {
        for (requirementExperience in  levelExperienceRequired) {
            if (getValue() < requirementExperience) {
                return requirementExperience.toString()
            }
        }
        return "Max"
    }

    public fun allStatsPointsToSpend() : Int {
        return (calculateLevel(getValue()) -1) * pointsPerLevel
    }
}