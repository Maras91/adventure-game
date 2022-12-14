package com.app.adventure.game.model.fight

import com.app.adventure.game.model.characters.Player
import com.app.adventure.game.model.fight.statistics.StatisticsName
import org.springframework.stereotype.Service

@Service
class CombatSimulator {

    fun fight(player : Player, monsterFightStats: Map<StatisticsName,Int>){
        var yourDamage  = 0
        var monsterDamage = 0
        val monsterHP :Int = monsterFightStats[StatisticsName.HP] ?: 0
        val monsterStrength :Int = monsterFightStats[StatisticsName.STRENGTH] ?: 0
        val monsterArmor :Int = monsterFightStats[StatisticsName.ARMOR] ?: 0
        val playerHP :Int = player.getCharacterStats().getStats()[StatisticsName.HP]?.getValue() ?: 0
        val playerStrength :Int = player.getCharacterStats().getStats()[StatisticsName.STRENGTH]?.getValue() ?: 0
        val playerArmor :Int = player.getCharacterStats().getStats()[StatisticsName.ARMOR]?.getValue() ?: 0

        while (monsterHP-monsterDamage>=0 && playerHP-yourDamage>=0) {
            yourDamage += attack(monsterStrength, playerArmor)
            monsterDamage += attack(playerStrength, monsterArmor)
        }
        player.getCharacterStats().getHp().takeDamage(yourDamage)
    }
    private fun attack( strength :Int, armor :Int) :Int {
        return if (armor >= strength) {
            1
        } else {
            strength - armor
        }
    }
}