package com.app.adventure.game.model.fight

import com.app.adventure.game.model.characters.Player
import com.app.adventure.game.model.fight.statistics.StatisticsName
import org.springframework.stereotype.Service

@Service
class CombatSimulator {

    public fun fight(player : Player, monsterFightStats: Map<StatisticsName,Int>) :Int {
        var yourDamage  = 0
        var monsterDamage = 0
        val monsterHP :Int = monsterFightStats[StatisticsName.HP] ?: 0
        val monsterStrength :Int = monsterFightStats[StatisticsName.STRENGTH] ?: 0
        val monsterArmor :Int = monsterFightStats[StatisticsName.ARMOR] ?: 0
        val playerHP :Int = player.getStats()[StatisticsName.HP]?.getValue() ?: 0
        val playerStrength :Int = player.getStats()[StatisticsName.STRENGTH]?.getValue() ?: 0
        val playerArmor :Int = player.getStats()[StatisticsName.ARMOR]?.getValue() ?: 0
        while (monsterHP-monsterDamage>=0 && playerHP-yourDamage>=0) {
            yourDamage += attack(monsterStrength, playerArmor)
            monsterDamage += attack(playerStrength, monsterArmor)
        }
        player.getHp().takeDamage(yourDamage)
        return player.getStats()[StatisticsName.HP]?.getValue() ?: -1

    }
    private fun attack( strength :Int, armor :Int) :Int {
        return if (armor >= strength) {
            1
        } else {
            strength - armor
        }
    }
}