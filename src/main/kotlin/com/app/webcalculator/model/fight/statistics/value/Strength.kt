package com.app.webcalculator.model.fight.statistics.value

class Strength(private val startValue : Int, private var items: Int, private var level :Int ) {

    fun getValue() : Int {
        return items + startValue + level
    }

    public fun addWhenLevelUp (points : Int) {
        level += points
    }

    public fun addFromItem (points : Int) {
        items = points
    }

    public fun getPointsFromLevelUp () : Int {
        return level
    }
}