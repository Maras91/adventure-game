package com.app.adventure.game.model.characters

import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.LevelProperties
import com.app.adventure.game.model.fight.statistics.CharacterStats
import com.app.adventure.game.model.fight.statistics.StatisticsFactory
import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.model.item.ItemType
import com.app.adventure.game.model.item.NotDisposableItem
import com.app.adventure.game.model.resources.ResourceName
import com.app.adventure.game.model.resources.ResourceValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class NotDisposableItemsTests {
    private val player = Player(
        mutableMapOf( ResourceName.GOLD to ResourceValue(30.0)),
        CharacterStats(
            mapOf(
                StatisticsName.STRENGTH.attributeName to 1,
                StatisticsName.HP.attributeName to 20,
                StatisticsName.ARMOR.attributeName to 1
            ),
            StatisticsFactory()
        ),
        mutableMapOf(),
        Experience(LevelProperties(TreeSet(listOf(0)),0))
    )

    @Test
    fun putOnItem() {
        //given
        val item  = NotDisposableItem(
            ItemType.SWORD,
            mapOf(StatisticsName.STRENGTH to 10, StatisticsName.ARMOR to 1),
            "sword",
            10.0,
            5.0
        )
        //when
        player.inventory.addItem(item)
        player.putOn(item)
        //then
        Assertions.assertEquals(11,player.getCharacterStats().getStats()[StatisticsName.STRENGTH]?.getValue())
        Assertions.assertEquals(20,player.getCharacterStats().getHp().getValue())
        Assertions.assertEquals(2,player.getCharacterStats().getStats()[StatisticsName.ARMOR]?.getValue())
    }

    @Test
    fun changeItem() {
        //given
        val item  = NotDisposableItem(
            ItemType.SWORD,
            mapOf(StatisticsName.STRENGTH to 100, StatisticsName.ARMOR to 10),
            "sword",
            10.0,
            5.0
        )

        val item2  = NotDisposableItem(
            ItemType.SWORD,
            mapOf(StatisticsName.STRENGTH to 10, StatisticsName.ARMOR to 1),
            "sword",
            10.0,
            5.0
        )
        //when
        player.inventory.addItem(item)
        player.inventory.addItem(item2)
        player.putOn(item)
        player.putOn(item2)
        //then
        Assertions.assertEquals(11,player.getCharacterStats().getStats()[StatisticsName.STRENGTH]?.getValue())
        Assertions.assertEquals(20,player.getCharacterStats().getHp().getValue())
        Assertions.assertEquals(2,player.getCharacterStats().getStats()[StatisticsName.ARMOR]?.getValue())
    }

    @Test
    fun getItemWithDifferentType() {
        //given
        val item  = NotDisposableItem(
            ItemType.SHIELD,
            mapOf(StatisticsName.STRENGTH to 100, StatisticsName.ARMOR to 10),
            "shield",
            10.0,
            5.0
        )

        val item2  = NotDisposableItem(
            ItemType.SWORD,
            mapOf(StatisticsName.STRENGTH to 10, StatisticsName.ARMOR to 1),
            "sword",
            10.0,
            5.0
        )
        //when
        player.inventory.addItem(item)
        player.inventory.addItem(item2)
        player.putOn(item)
        player.putOn(item2)
        //then
        Assertions.assertEquals(111,player.getCharacterStats().getStats()[StatisticsName.STRENGTH]?.getValue())
        Assertions.assertEquals(20,player.getCharacterStats().getHp().getValue())
        Assertions.assertEquals(12,player.getCharacterStats().getStats()[StatisticsName.ARMOR]?.getValue())
    }
}