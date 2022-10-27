package com.app.adventure.game.model.characters

import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.LevelProperties
import com.app.adventure.game.model.fight.experience.StatsUp
import com.app.adventure.game.model.fight.statistics.value.Armor
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Strength
import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.model.resources.Resources
import org.junit.jupiter.api.Test
import org.springframework.test.util.AssertionErrors.assertEquals
import java.util.*

class ExperienceTests {

    private val player : Player = Player(
            Resources(0.0,0.0,0.0),
            mapOf(
                StatisticsName.STRENGTH to Strength(5),
                StatisticsName.HP to Hp(50),
                StatisticsName.ARMOR to Armor(3)
            ),
            Experience(LevelProperties(TreeSet(listOf(1000, 3000, 5000, 8000, 11000, 15000, 19000)),4)))

    @Test
    fun statsUpNotValidTest () {
        //given
        val testPlayer : Player = player
        val statsUp = StatsUp(
            mapOf(
                StatisticsName.STRENGTH to 3,
                StatisticsName.HP to 1,
                StatisticsName.ARMOR to 1
            )
        )
        //when
        testPlayer.getExperience().addExperience(1250)
        testPlayer.addStatsUp(statsUp)
        //then
        assertEquals("",5,testPlayer.getStats()[StatisticsName.STRENGTH]?.getValue())
        assertEquals("",50,testPlayer.getStats()[StatisticsName.HP]?.getValue())
        assertEquals("",3,testPlayer.getStats()[StatisticsName.ARMOR]?.getValue())
    }

    @Test
    fun statsUpValidTest() {
        //given
        val testPlayer : Player = player
        val statsUp = StatsUp(
            mapOf(
                StatisticsName.STRENGTH to 2,
                StatisticsName.HP to 1,
                StatisticsName.ARMOR to 1
            )
        )
        //when
        testPlayer.getExperience().addExperience(1250)
        testPlayer.addStatsUp(statsUp)
        //then
        assertEquals("",7,testPlayer.getStats()[StatisticsName.STRENGTH]?.getValue())
        assertEquals("",60,testPlayer.getStats()[StatisticsName.HP]?.getValue())
        assertEquals("",4,testPlayer.getStats()[StatisticsName.ARMOR]?.getValue())
    }

    @Test
    fun playerWithNotEnoughExperienceTest () {
        //given
        val testPlayer : Player = player
        val statsUp = StatsUp(
            mapOf(
                StatisticsName.STRENGTH to 2,
                StatisticsName.HP to 1,
                StatisticsName.ARMOR to 1
            )
        )
        //when
        testPlayer.addStatsUp(statsUp)
        //then
        assertEquals("",5,testPlayer.getStats()[StatisticsName.STRENGTH]?.getValue())
        assertEquals("",50,testPlayer.getStats()[StatisticsName.HP]?.getValue())
        assertEquals("",3,testPlayer.getStats()[StatisticsName.ARMOR]?.getValue())
    }

    @Test
    fun increaseHpWhenPlayerDamaged() {
        //given
        val testPlayer : Player = player
        val statsUp : StatsUp = StatsUp(
            mapOf(
                StatisticsName.STRENGTH to 1,
                StatisticsName.HP to 2,
                StatisticsName.ARMOR to 1
            )
        )
        //when
        testPlayer.getHp().takeDamage(13)
        testPlayer.getExperience().addExperience(1250)
        testPlayer.addStatsUp(statsUp)
        //then
        assertEquals("",57,testPlayer.getHp().getCurrentHp())
    }
}