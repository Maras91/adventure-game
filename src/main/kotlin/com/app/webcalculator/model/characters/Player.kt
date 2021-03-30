package com.app.webcalculator.model.characters

import com.app.webcalculator.model.fight.experience.Experience
import com.app.webcalculator.model.fight.statistics.FightStats
import com.app.webcalculator.model.resources.Resources

class Player(private var resources: Resources, private var fightStats: FightStats, private var experience: Experience) {

    public fun getResources() : Resources {
        return resources
    }

    public fun getFightStats() : FightStats {
        return fightStats
    }

    public fun getExperience() : Experience {
        return experience
    }

    fun levelUp() {
        getFightStats().getStrength().addWhenLevelUp(2)
        getFightStats().getHp().addWhenLevelUp(10)
        getFightStats().getArmor().addWhenLevelUp(1)
    }

    public fun win(monster : Monster) {
        getResources().addResources(monster.getResources())
        if (getExperience().isNextLevel(monster.getExperience())) {
            levelUp()
        }
        getExperience().addExperience(monster.getExperience())
    }
}