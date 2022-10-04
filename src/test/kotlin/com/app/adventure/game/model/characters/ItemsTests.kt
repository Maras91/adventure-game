package com.app.adventure.game.model.characters

import com.app.adventure.game.controller.ItemsController
import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.LevelProperties
import com.app.adventure.game.model.fight.statistics.FightStats
import com.app.adventure.game.model.fight.statistics.value.Armor
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Strength
import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.ItemAttribute
import com.app.adventure.game.model.resources.Resources
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class ItemsTests {

    @Test
    fun itemPurchased() {
        //given
        val potion = DisposableItem(
            true,
            0,
            mapOf(
                ItemAttribute.HP_RECOVERY to 5
            ),
            "potion",
            5.0,
            4.0
        )
        val player = Player(
            Resources(30.0,0.0,0.0),
            FightStats(
                Strength(1),
                Hp(20),
                Armor(1)
            ),
            Experience(LevelProperties(TreeSet(listOf(0)),0))
        )
        val itemController = ItemsController(player, mapOf("potion" to potion))
        //when
        player.getFightStats().takeDamage(10)
        itemController.buyItems("potion")
        //then
        Assertions.assertEquals(15,player.getFightStats().getHp().getValue())
        Assertions.assertEquals(25.0,player.getResources().getGold())
    }

    @Test
    fun notEnoughGold() {
        //given
        val potion = DisposableItem(
            true,
            0,
            mapOf(
                ItemAttribute.HP_RECOVERY to 5
            ),
            "potion",
            50.0,
            4.0
        )
        val player = Player(
            Resources(30.0,0.0,0.0),
            FightStats(
                Strength(1),
                Hp(20),
                Armor(1)
            ),
            Experience(LevelProperties(TreeSet(listOf(0)),0))
        )
        val itemController = ItemsController(player, mapOf("potion" to potion))
        //when
        player.getFightStats().takeDamage(10)
        itemController.buyItems("potion")
        //then
        Assertions.assertEquals(10,player.getFightStats().getHp().getValue())
        Assertions.assertEquals(30.0,player.getResources().getGold())
    }
    @Test
    fun itemNotFound() {
        //given
        val potion = DisposableItem(
            true,
            0,
            mapOf(
                ItemAttribute.HP_RECOVERY to 5
            ),
            "potion",
            50.0,
            4.0
        )
        val player = Player(
            Resources(30.0,0.0,0.0),
            FightStats(
                Strength(1),
                Hp(20),
                Armor(1)
            ),
            Experience(LevelProperties(TreeSet(listOf(0)),0))
        )
        val itemController = ItemsController(player, mapOf("potion" to potion))
        //when
        player.getFightStats().takeDamage(10)
        itemController.buyItems("another item")
        //then
        Assertions.assertEquals(10,player.getFightStats().getHp().getValue())
        Assertions.assertEquals(30.0,player.getResources().getGold())
    }
}