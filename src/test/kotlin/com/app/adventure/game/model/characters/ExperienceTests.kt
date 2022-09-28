package com.app.adventure.game.model.characters

import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.LevelProperties
import com.app.adventure.game.model.fight.experience.StatsUp
import com.app.adventure.game.model.fight.statistics.FightStats
import com.app.adventure.game.model.fight.statistics.value.Armor
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Strength
import com.app.adventure.game.model.resources.Resources
import org.junit.jupiter.api.Test
import org.springframework.test.util.AssertionErrors.assertEquals
import java.util.*

class ExperienceTests {

    private val player : Player = Player(
            Resources(0.0,0.0,0.0),
            FightStats(
                    Strength(5,0,0),
                    Hp(50,0,0,0),
                    Armor(3,0,0)
            ),
            Experience(LevelProperties(TreeSet(listOf(1000, 3000, 5000, 8000, 11000, 15000, 19000)),4)))

    @Test
    fun statsUpNotValidTest () {
        //given
        val testPlayer : Player = player
        val statsUp : StatsUp = StatsUp(3,10,1)
        //when
        testPlayer.getExperience().addExperience(1250)
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
        val statsUp : StatsUp = StatsUp(2,10,1)
        //when
        testPlayer.getExperience().addExperience(1250)
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

    @Test
    fun increaseHpWhenPlayerDamaged() {
        //given
        val testPlayer : Player = player
        val statsUp : StatsUp = StatsUp(1,20,1)
        //when
        testPlayer.getFightStats().takeDamage(13)
        testPlayer.getExperience().addExperience(1250)
        testPlayer.addStatsUp(statsUp)
        //then
        assertEquals("",57,testPlayer.getFightStats().getHp().getValue())
    }
}