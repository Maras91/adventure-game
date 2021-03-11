package com.app.webcalculator.model.fight.statistics

class FightStats(startStrength: Int, startHp: Int, startArmor: Int, experience: Int) {
    private val strength :MutableList<Int> = mutableListOf(startStrength)
    private val hp :MutableList<Int> = mutableListOf(startHp)
    private val armor :MutableList<Int> = mutableListOf(startArmor)
    private val experience :MutableList<Int> = mutableListOf(experience)



    public fun takeDamage(damage : Int) {
        hp.add(damage*(-1))
    }

    public fun hpRecovery(hpRecovery :Int) {
        hp.add(hpRecovery)
    }

    public fun addExperience (experience : Int) {
        experience.and(experience)
    }

    fun getExperience(): Int {
        return experience.reduce{x, x2->x+x2}
    }

    public fun getStrength() : Int {
        return strength.reduce{x, x2->x+x2}
    }

    public fun getHp(): Int {
        return  hp.reduce{x, x2->x+x2}
    }

    public fun getArmor(): Int {
        return  armor.reduce{x, x2->x+x2}
    }


}