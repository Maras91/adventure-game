package com.app.webcalculator.view

import com.app.webcalculator.model.fight.experience.Experience

class ExperienceView {
    private val  value : Int
    private val nextLevelExp : String
    private val level : Int

    constructor(value: Int, nextLevelExp: String, level: Int) {
        this.value = value
        this.nextLevelExp = nextLevelExp
        this.level = level
    }

    constructor(experience : Experience) {
        this.value = experience.getValue()
        this.nextLevelExp = experience.nextLevelExperience()
        this.level = experience.calculateLevel(this.value)
    }

    public fun getValue() : Int {
        return value
    }

    public fun getNextLevelExp(): String {
        return nextLevelExp
    }

    public fun getLevel(): Int {
        return  level
    }
}