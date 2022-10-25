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
import org.junit.jupiter.api.Test

import java.util.*

class CombatSystemTests {
    @Test
    fun armorMaxDamageReduceTest() {
        //given
        val combatSimulator = CombatSimulator()
        val player = Player(
            Resources(0.0,0.0,0.0),
            listOf(
                Strength(24),
                Hp(50),
                Armor(5)
            ),
            Experience(LevelProperties(TreeSet(listOf(10)),1))
        )
        val monster = Monster(
            mapOf(StatisticsName.STRENGTH to 4, StatisticsName.HP to 20, StatisticsName.ARMOR to 3),
            ResourcesView(1.0,2.0,3.0),
            50)
        //when
        combatSimulator.fight(player,monster.getStatsView());
        //then
        Assertions.assertEquals(49,player.getHp().getCurrentHp())

    }

    @Test
    fun armorDamageReducesTest() {
        //given
        val combatSimulator = CombatSimulator()
        val player = Player(
            Resources(0.0,0.0,0.0),
            listOf(
                Strength(10),
                Hp(50),
                Armor(5)
            ),
            Experience(LevelProperties(TreeSet(listOf(0)),0))
        )
        val monster = Monster(
            mapOf(StatisticsName.STRENGTH to 10, StatisticsName.HP to 1, StatisticsName.ARMOR to 3),
            ResourcesView(0.0,0.0,0.0),
            0)
        //when
        combatSimulator.fight(player,monster.getStatsView());
        //then
        Assertions.assertEquals(45,player.getHp().getCurrentHp())

    }

    @Test
    fun fightTwoRandTest() {
        //given
        val combatSimulator = CombatSimulator()
        val player = Player(
            Resources(0.0,0.0,0.0),
            listOf(
                Strength(10),
                Hp(50),
                Armor(5)
            ),
            Experience(LevelProperties(TreeSet(listOf(0)),0))
        )
        val monster = Monster(
            mapOf(StatisticsName.STRENGTH to 10, StatisticsName.HP to 10, StatisticsName.ARMOR to 3),
            ResourcesView(0.0,0.0,0.0),
            0)
        //when
        combatSimulator.fight(player,monster.getStatsView());
        //then
        Assertions.assertEquals(40,player.getHp().getCurrentHp())
    }

    @Test
    fun collectingResourcesTest(){
        //given
        val player = Player(
            Resources(0.0,0.0,0.0),
            listOf(
                Strength(1),
                Hp(1),
                Armor(1)
            ),
            Experience(LevelProperties(TreeSet(listOf(0)),0))
        )
        val monster = Monster(
            mapOf(StatisticsName.STRENGTH to 1, StatisticsName.HP to 1, StatisticsName.ARMOR to 1),
            ResourcesView(1.0,2.0,3.0),
            10)
        //when
        player.win(monster)
        //then
        Assertions.assertEquals(1.0,player.getResources().getGold())
        Assertions.assertEquals(2.0,player.getResources().getIron())
        Assertions.assertEquals(3.0,player.getResources().getMeat())
        Assertions.assertEquals(10,player.getExperience().getValue())
    }

    @Test
    fun healMaxHpTest() {
        //given
        val item = DisposableItem(
                true,
                0,
                mapOf(ItemEffects.HP_RECOVERY to 1000),
                "potion",
                2.0,
                4.0
            )
        val player = Player(
            Resources(30.0,0.0,0.0),
            listOf(
                Strength(1),
                Hp(20),
                Armor(1)
            ),
            Experience(LevelProperties(TreeSet(listOf(0)),0))
        )
        //when
        player.getHp().takeDamage(10)
        player.addStatsFromItem(item)
        //then
        Assertions.assertEquals(20,player.getHp().getCurrentHp())
    }

    @Test
    fun healHpTest() {
        //given
        val item = DisposableItem(
            true,
            0,
            mapOf(ItemEffects.HP_RECOVERY to 5),
            "potion",
            2.0,
            4.0
        )
        val player = Player(
            Resources(20.0,0.0,0.0),
            listOf(
                Strength(1),
                Hp(20),
                Armor(1)
            ),
            Experience(LevelProperties(TreeSet(listOf(0)),0))
        )
        //when
        player.getHp().takeDamage(10)
        player.addStatsFromItem(item)
        //then
        Assertions.assertEquals(15,player.getHp().getCurrentHp())
    }
}