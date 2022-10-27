package com.app.adventure.game.model.characters

import com.app.adventure.game.controller.ItemsController
import com.app.adventure.game.model.fight.experience.Experience
import com.app.adventure.game.model.fight.experience.LevelProperties
import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.model.fight.statistics.value.Armor
import com.app.adventure.game.model.fight.statistics.value.Hp
import com.app.adventure.game.model.fight.statistics.value.Strength
import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.ItemEffects
import com.app.adventure.game.model.resources.Resources
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.util.*

class ItemsTests {
    private val player = Player(
        Resources(30.0,0.0,0.0),
        mapOf(
            StatisticsName.STRENGTH to Strength(1),
            StatisticsName.HP to Hp(20),
            StatisticsName.ARMOR to Armor(1)
        ),
        Experience(LevelProperties(TreeSet(listOf(0)),0))
    )

    @ParameterizedTest
    @CsvSource(
        "5,5.0,potion,15,25",
        "5,50.0,potion,10,30",
        "5,5.0,not potion,10,30",
        "50,5.0,potion,20,25"
    )
    fun potionsTests(hpRecovery: Int,
                   bayCost:Double,
                   itemName: String,
                   expectedHp: Int,
                   expectedGold: Double) {
        //given
        val potion = DisposableItem(
            true,
            0,
            mapOf(
                ItemEffects.HP_RECOVERY to hpRecovery
            ),
            "potion",
            bayCost,
            4.0
        )
        val itemController = ItemsController(player, mapOf(itemName to potion), emptyMap())
        //when
        player.getHp().takeDamage(10)
        itemController.buyItems("potion")
        //then
        Assertions.assertEquals(expectedHp,player.getHp().getCurrentHp())
        Assertions.assertEquals(expectedGold,player.getResources().getGold())
    }
}