package com.app.webcalculator.model.fight

import com.app.webcalculator.model.characters.Player
import com.app.webcalculator.model.fight.statistics.FightStatsJsonObject
import org.springframework.stereotype.Service

@Service
class CombatSimulator {
    public fun fight(player : Player, opponentFightStats: FightStatsJsonObject) :Int {
        var yourDamage :Int = 0
        var opponentDamage :Int = 0

        while (opponentFightStats.getHp()-opponentDamage>=0 && player.getFightStats().getHp()-yourDamage>=0) {
            yourDamage += attack(opponentFightStats.getStrength(), player.getFightStats().getArmor())
            opponentDamage += attack(player.getFightStats().getStrength(), opponentFightStats.getArmor())
        }
        player.getFightStats().takeDamage(yourDamage)
        return player.getFightStats().getHp()

    }
    private fun attack( strength :Int, armor :Int) :Int {
        return if (armor >= strength) {
            1
        } else {
            strength - armor
        }
    }
}