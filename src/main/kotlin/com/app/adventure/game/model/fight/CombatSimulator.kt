package com.app.adventure.game.model.fight

import com.app.adventure.game.model.yml.BattleConfig
import com.app.adventure.game.model.characters.Player
import com.app.adventure.game.view.FightStatsView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CombatSimulator {

    @Autowired
    private lateinit var monsters: BattleConfig

    public fun fight(player : Player, opponentFightStats: FightStatsView) :Int {
        var yourDamage :Int = 0
        var opponentDamage :Int = 0

        while (opponentFightStats.getHp()-opponentDamage>=0 && player.getFightStats().getHpNumber()-yourDamage>=0) {
            yourDamage += attack(opponentFightStats.getStrength(), player.getFightStats().getArmorNumber())
            opponentDamage += attack(player.getFightStats().getStrengthNumber(), opponentFightStats.getArmor())
        }
        player.getFightStats().takeDamage(yourDamage)
        return player.getFightStats().getHpNumber()

    }
    private fun attack( strength :Int, armor :Int) :Int {
        return if (armor >= strength) {
            1
        } else {
            strength - armor
        }
    }
}