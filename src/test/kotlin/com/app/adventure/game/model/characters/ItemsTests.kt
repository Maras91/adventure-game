package com.app.adventure.game.model.characters

import com.app.adventure.game.controller.ItemsController
import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.LevelProperties
import com.app.adventure.game.model.fight.statistics.CharacterStats
import com.app.adventure.game.model.fight.statistics.StatisticsFactory
import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.Inventory
import com.app.adventure.game.model.item.ItemEffects
import com.app.adventure.game.model.resources.ResourceValue
import com.app.adventure.game.model.resources.ResourceName
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.util.*

class ItemsTests {
    private val player = Player(
        mutableMapOf(
            ResourceName.GOLD to ResourceValue(30.0),
        ), CharacterStats(
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

    @ParameterizedTest
    @CsvSource(
        "5,5.0,15,25",
        "5,50.0,10,30",
        "50,5.0,20,25"
    )
    fun singleUseItemTests(hpRecovery: Int,
                   bayCost:Double,
                   expectedHp: Int,
                   expectedGold: Double) {
        //given
        val itemName = "potion"
        val potion = DisposableItem(
            true,
            0,
            mapOf(
                ItemEffects.HP_RECOVERY to hpRecovery
            ),
            itemName,
            bayCost,
            4.0
        )
        val itemController = ItemsController(player, Inventory(mutableMapOf( itemName to potion)))
        //when
        player.getCharacterStats().getHp().takeDamage(10)
        itemController.buyItems(itemName)
        itemController.useItem(itemName)
        //then
        Assertions.assertEquals(expectedHp,player.getCharacterStats().getHp().getCurrentHp())
        Assertions.assertEquals(expectedGold,player.getResources()[ResourceName.GOLD]?.getValue())
    }

    @ParameterizedTest
    @CsvSource(
        "potion,potion,potion,15,15",
        "sword,potion,potion,10,30",
        "potion,sword,potion,10,30",
        "potion,potion,sword,10,15"
    )
    fun wrongItemNameTests(itemCreateName: String,
                           itemBuyName: String,
                           itemBuyUse: String,
                           expectedHp: Int,
                           expectedGold: Double) {
        //given
        val potion = DisposableItem(
            true,
            0,
            mapOf(
                ItemEffects.HP_RECOVERY to 5
            ),
            itemCreateName,
            15.0,
            4.0
        )
        val itemController = ItemsController(player, Inventory(mutableMapOf(itemCreateName to potion)))
        //when
        player.getCharacterStats().getHp().takeDamage(10)
        itemController.buyItems(itemBuyName)
        itemController.useItem(itemBuyUse)
        //then
        Assertions.assertEquals(expectedHp,player.getCharacterStats().getHp().getCurrentHp())
        Assertions.assertEquals(expectedGold,player.getResources()[ResourceName.GOLD]?.getValue())
    }
}