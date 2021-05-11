package com.app.webcalculator.model.characters

import com.app.webcalculator.model.fight.experience.Experience
import com.app.webcalculator.model.fight.experience.StatsUp
import com.app.webcalculator.model.fight.statistics.FightStats
import com.app.webcalculator.model.fight.statistics.value.Armor
import com.app.webcalculator.model.fight.statistics.value.Hp
import com.app.webcalculator.model.fight.statistics.value.Strength
import com.app.webcalculator.model.resources.Resources
import org.junit.jupiter.api.Test
import org.springframework.test.util.AssertionErrors.assertEquals


class PlayerTests {

    private val player : Player = Player(
            Resources(0.0,0.0,0.0),
            FightStats(
                    Strength(5,0,0),
                    Hp(50,0,0,0),
                    Armor(3,0,0)
            ),
            Experience())

    @Test
    fun statsUpNotValidTest () {
        //given
        val testPlayer : Player = player
        testPlayer.getExperience().addExperience(1250)
        val statsUp : StatsUp = StatsUp(3,10,1)
        //when
        testPlayer.addStatsUp(statsUp)
        //then
        assertEquals("",5,testPlayer.getFightStats().getStrength().getValue())
        assertEquals("",50,testPlayer.getFightStats().getHp().getValue())
        assertEquals("",3,testPlayer.getFightStats().getArmor().getValue())
    }

    @Test
    fun statsUpValidTest() {
        //given
        val testPlayer : Player = player
        testPlayer.getExperience().addExperience(1250)
        val statsUp : StatsUp = StatsUp(2,10,1)
        //when
        testPlayer.addStatsUp(statsUp)
        //then
        assertEquals("",7,testPlayer.getFightStats().getStrength().getValue())
        assertEquals("",60,testPlayer.getFightStats().getHp().getValue())
        assertEquals("",4,testPlayer.getFightStats().getArmor().getValue())
    }

    @Test
    fun playerWithNotEnoughExperienceTest () {
        //given
        val testPlayer : Player = player
        val statsUp : StatsUp = StatsUp(2,10,1)
        //when
        testPlayer.addStatsUp(statsUp)
        //then
        assertEquals("",5,testPlayer.getFightStats().getStrength().getValue())
        assertEquals("",50,testPlayer.getFightStats().getHp().getValue())
        assertEquals("",3,testPlayer.getFightStats().getArmor().getValue())
    }
}