package com.app.adventure.game.model.characters

import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.LevelProperties
import com.app.adventure.game.model.fight.experience.StatsUp
import com.app.adventure.game.model.fight.statistics.CharacterStats
import com.app.adventure.game.model.fight.statistics.StatisticsFactory
import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.model.resources.ResourceValue
import com.app.adventure.game.model.resources.ResourceName
import org.junit.jupiter.api.Test
import org.springframework.test.util.AssertionErrors.assertEquals
import java.util.*

class ExperienceTests {

    private val player : Player = Player(
        mutableMapOf(
            ResourceName.GOLD to ResourceValue(30.0),
        ), CharacterStats(
            mapOf(
                StatisticsName.STRENGTH.attributeName to 5,
                StatisticsName.HP.attributeName to 50,
                StatisticsName.ARMOR.attributeName to 3
            ),
            StatisticsFactory()
        ),
        mutableMapOf(),
        Experience(LevelProperties(TreeSet(listOf(1000, 3000, 5000, 8000, 11000, 15000, 19000)),4))
    )

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
        assertEquals("",5,testPlayer.getCharacterStats().getStats()[StatisticsName.STRENGTH]?.getValue())
        assertEquals("",50,testPlayer.getCharacterStats().getStats()[StatisticsName.HP]?.getValue())
        assertEquals("",3,testPlayer.getCharacterStats().getStats()[StatisticsName.ARMOR]?.getValue())
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
        assertEquals("",7,testPlayer.getCharacterStats().getStats()[StatisticsName.STRENGTH]?.getValue())
        assertEquals("",60,testPlayer.getCharacterStats().getStats()[StatisticsName.HP]?.getValue())
        assertEquals("",4,testPlayer.getCharacterStats().getStats()[StatisticsName.ARMOR]?.getValue())
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
        assertEquals("",5,testPlayer.getCharacterStats().getStats()[StatisticsName.STRENGTH]?.getValue())
        assertEquals("",50,testPlayer.getCharacterStats().getStats()[StatisticsName.HP]?.getValue())
        assertEquals("",3,testPlayer.getCharacterStats().getStats()[StatisticsName.ARMOR]?.getValue())
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
        testPlayer.getCharacterStats().getHp().takeDamage(13)
        testPlayer.getExperience().addExperience(1250)
        testPlayer.addStatsUp(statsUp)
        //then
        assertEquals("",57,testPlayer.getCharacterStats().getHp().getCurrentHp())
    }
}