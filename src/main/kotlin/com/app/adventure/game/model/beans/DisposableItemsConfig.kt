package com.app.adventure.game.model.beans

import com.app.adventure.game.model.exceptions.IncorrectYamlPropertiesException
import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.ItemEffects
import com.app.adventure.game.model.yaml.properties.DisposableItemsYaml
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DisposableItemsConfig @Autowired constructor(val disposableItemsYaml : DisposableItemsYaml){

    @Bean
    fun createDisposableItemsItems(): Map<String,DisposableItem>{
        return disposableItemsYaml.disposable
            ?.filter { monster -> monster?.name != null }
            ?.associate { itemYml -> itemYml?.name!! to DisposableItem(
                itemYml.hasPermanentEffect ?: false,
                itemYml.timeInTurns ?: 0,
                itemYml.effects?.mapKeys {
                    ymlAt -> ItemEffects.values().find {
                        it.effectName == ymlAt.key.toLowerCase()
                    }?: throw IncorrectYamlPropertiesException(
                            ymlAt.key, ItemEffects.values().map { it.effectName }.toString()
                        )
                } ?: emptyMap(),
                itemYml.name ?: "",
                itemYml.bayCost ?: 0.0,
                itemYml.sellCost ?: 0.0
            )} ?: emptyMap()
    }
}