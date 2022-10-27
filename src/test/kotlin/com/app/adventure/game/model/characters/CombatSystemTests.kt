package com.app.adventure.game.model.characters

import com.app.adventure.game.model.fight.CombatSimulator
import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.LevelProperties
import com.app.adventure.game.model.fight.statistics.value.Armor
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Strength
import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.ItemEffects
import com.app.adventure.game.model.resources.Resources
import com.app.adventure.game.view.ResourcesView
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.util.*

class CombatSystemTests {
    private lateinit var player : Player;
    private val combatSimulator = CombatSimulator()
    @BeforeEach
    fun init(){
        player = Player(
            Resources(30.0,0.0,0.0),
            mapOf(
                StatisticsName.STRENGTH to Strength(10),
                StatisticsName.HP to Hp(50),
                StatisticsName.ARMOR to Armor(10)
            ),
            Experience(LevelProperties(TreeSet(listOf(1000, 3000, 5000, 8000, 11000, 15000, 19000)),4)))
    }

    @Test
    fun armorMinDamageReduceTest() {
        //given
        val monster = Monster(
            mapOf(StatisticsName.STRENGTH to 5, StatisticsName.HP to 5, StatisticsName.ARMOR to 3),
            ResourcesView(1.0,2.0,3.0),
            50)
        //when
        combatSimulator.fight(player,monster.getStatsView());
        //then
        Assertions.assertEquals(49,player.getHp().getCurrentHp())

    }

    @Test
    fun armorDamageReducesTest() {
        val monster = Monster(
            mapOf(StatisticsName.STRENGTH to 15, StatisticsName.HP to 1, StatisticsName.ARMOR to 3),
            ResourcesView(0.0,0.0,0.0),
            0)
        //when
        combatSimulator.fight(player,monster.getStatsView());
        //then
        Assertions.assertEquals(45,player.getHp().getCurrentHp())

    }

    @Test
    fun fightTwoRandTest() {
        val monster = Monster(
            mapOf(StatisticsName.STRENGTH to 15, StatisticsName.HP to 10, StatisticsName.ARMOR to 3),
            ResourcesView(0.0,0.0,0.0),
            0)
        //when
        combatSimulator.fight(player,monster.getStatsView());
        //then
        Assertions.assertEquals(40,player.getHp().getCurrentHp())
    }

    @Test
    fun collectingResourcesTest(){
        val monster = Monster(
            mapOf(StatisticsName.STRENGTH to 60, StatisticsName.HP to 5, StatisticsName.ARMOR to 5),
            ResourcesView(10.0,2.0,3.0),
            10)
        //when
        player.win(monster)
        //then
        Assertions.assertEquals(40.0,player.getResources().getGold())
        Assertions.assertEquals(2.0,player.getResources().getIron())
        Assertions.assertEquals(3.0,player.getResources().getMeat())
        Assertions.assertEquals(10,player.getExperience().getValue())
    }

    @Test
    fun lostCombatTest(){
        //given
        val monster = Monster(
            mapOf(StatisticsName.STRENGTH to 60, StatisticsName.HP to 500, StatisticsName.ARMOR to 15),
            ResourcesView(10.0,20.0,30.0),
            10)
        //when
        combatSimulator.fight(player,monster.getStatsView())
        //then
        Assertions.assertEquals(30.0,player.getResources().getGold())
        Assertions.assertEquals(0.0,player.getResources().getIron())
        Assertions.assertEquals(0.0,player.getResources().getMeat())
        Assertions.assertEquals(0,player.getExperience().getValue())
    }
}