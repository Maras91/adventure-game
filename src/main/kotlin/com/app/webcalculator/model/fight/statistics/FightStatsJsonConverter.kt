package com.app.webcalculator.model.fight.statistics

import org.springframework.stereotype.Service

@Service
class FightStatsJsonConverter {

    fun convertFromFightStatsToJson(fightStats : FightStats) : FightStatsJsonObject {
        return FightStatsJsonObject (fightStats.getStrengthNumber(),fightStats.getHpNumber(), fightStats.getHp().getMaxHp(),fightStats.getArmorNumber())
    }
}