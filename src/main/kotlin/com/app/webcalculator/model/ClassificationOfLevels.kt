package com.app.webcalculator.model

class ClassificationOfLevels {
    private val levelExperienceRequired :Map<Int,Int> = mapOf(2 to 1000, 3 to 3000, 4 to 5000, 5 to 8000)

    fun isLevelUp(level :Int, experience : Int) : Boolean {
        return if (level <= 4) {
            experience > levelExperienceRequired.getValue(level + 1)
        } else {
            false
        }
    }
}