package com.app.adventure.game.model.characters

import com.app.adventure.game.model.fight.CombatSimulator
import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.LevelProperties
import com.app.adventure.game.model.fight.statistics.FightStats
import com.app.adventure.game.model.fight.statistics.value.Armor
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Strength
import com.app.adventure.game.model.resources.Resources
import com.app.adventure.game.view.FightStatsView
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
            FightStats(
                Strength(24,0,0),
                Hp(50,0,0,0),
                Armor(5,0,0)
            ),
            Experience(LevelProperties(TreeSet(listOf(10)),1))
        )
        val monster = Monster(
            FightStatsView(4,20,20,3),
            ResourcesView(1.0,2.0,3.0),
            50)
        //when
        combatSimulator.fight(player,monster.getFightStats());
        //then
        Assertions.assertEquals(49,player.getFightStats().getHp().getValue())

    }

    @Test
    fun armorDamageReducesTest() {
        //given
        val combatSimulator = CombatSimulator()
        val player = Player(
            Resources(0.0,0.0,0.0),
            FightStats(
                Strength(10,0,0),
                Hp(50,0,0,0),
                Armor(5,0,0)
            ),
            Experience(LevelProperties(TreeSet(listOf(0)),0))
        )
        val monster = Monster(
            FightStatsView(10,1,1,3),
            ResourcesView(0.0,0.0,0.0),
            0)
        //when
        combatSimulator.fight(player,monster.getFightStats());
        //then
        Assertions.assertEquals(45,player.getFightStats().getHp().getValue())

    }

    @Test
    fun fightTwoRandTest() {
        val combatSimulator = CombatSimulator()
        val player = Player(
            Resources(0.0,0.0,0.0),
            FightStats(
                Strength(10,0,0),
                Hp(50,0,0,0),
                Armor(5,0,0)
            ),
            Experience(LevelProperties(TreeSet(listOf(0)),0))
        )
        val monster = Monster(
            FightStatsView(10,10,10,3),
            ResourcesView(0.0,0.0,0.0),
            0)
        //when
        combatSimulator.fight(player,monster.getFightStats());
        //then
        Assertions.assertEquals(40,player.getFightStats().getHp().getValue())
    }

    @Test
    fun collectingResourcesTest(){
        //given
        val player = Player(
            Resources(0.0,0.0,0.0),
            FightStats(
                Strength(1,0,0),
                Hp(1,0,0,0),
                Armor(1,0,0)
            ),
            Experience(LevelProperties(TreeSet(listOf(0)),0))
        )
        val monster = Monster(
            FightStatsView(1,1,1,1),
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
        val player = Player(
            Resources(0.0,0.0,0.0),
            FightStats(
                Strength(1,0,0),
                Hp(20,0,0,0),
                Armor(1,0,0)
            ),
            Experience(LevelProperties(TreeSet(listOf(0)),0))
        )
        //when
        player.getFightStats().takeDamage(10)
        player.getFightStats().hpRecovery(1000)
        //then
        Assertions.assertEquals(20,player.getFightStats().getHp().getValue())
    }

    @Test
    fun healHpTest() {
        //given
        val player = Player(
            Resources(0.0,0.0,0.0),
            FightStats(
                Strength(1,0,0),
                Hp(20,0,0,0),
                Armor(1,0,0)
            ),
            Experience(LevelProperties(TreeSet(listOf(0)),0))
        )
        //when
        player.getFightStats().takeDamage(10)
        player.getFightStats().hpRecovery(5)
        //then
        Assertions.assertEquals(15,player.getFightStats().getHp().getValue())
    }
}