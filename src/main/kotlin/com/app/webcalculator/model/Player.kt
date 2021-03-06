package com.app.webcalculator.model

class Player(private var resources: Resources, private var fightStats: FightStats) {
    public fun fight(opponentFightStats: FightStats) :Int {
        var yourDamage :Int = 0
        var opponentDamage :Int = 0

        while (opponentFightStats.getHp()-opponentDamage>=0 && fightStats.getHp()-yourDamage>=0) {
            yourDamage += attack(opponentFightStats.getStrength(), fightStats.getArmor())
            opponentDamage += attack(fightStats.getStrength(), opponentFightStats.getArmor())
        }
        fightStats.takeDamage(yourDamage)
        return fightStats.getHp()

    }
    private fun attack( strength :Int, armor :Int) :Int {
        return if (armor >= strength) {
            1
        } else {
            strength - armor
        }
    }

    public fun getResources() : Resources {
        return resources
    }

    public fun getFightStats() : FightStats {
        return fightStats
    }


}