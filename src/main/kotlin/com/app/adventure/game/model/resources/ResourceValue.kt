package com.app.adventure.game.model.resources

class  ResourceValue (startValue: Double){
    private val value: MutableList<Double> = mutableListOf(startValue);

    fun getValue() :Double {
        return value.sum()
    }

    fun pay(prise :Double) {
        value.add(prise*(-1))
    }

    fun addValue(amount :Double) {
        value.add(amount)
    }
}