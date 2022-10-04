package com.app.adventure.game.model.beans

import com.app.adventure.game.model.item.DisposableItem
import com.app.adventure.game.model.item.ItemAttribute
import com.app.adventure.game.model.yml.properties.DisposableItemsYml
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ItemsConfig @Autowired constructor(val disposableItemsYml :DisposableItemsYml){

    @Bean
    fun createItems(): Map<String,DisposableItem>{
        //TODO add log when item attribute is not define
        return disposableItemsYml.disposable
            ?.filter { monster -> monster?.name != null }
            ?.associate { itemYml -> itemYml?.name!! to DisposableItem(
                itemYml.hasPermanentEffect ?: false,
                itemYml.timeInTurns ?: 0,
                itemYml.attributes?.mapKeys {
                    ymlAt -> ItemAttribute.values().find {
                        it.attributeName == ymlAt.key.toLowerCase()
                    }?: ItemAttribute.NOT_DEFINE
                } ?: emptyMap(),
                itemYml.name ?: "",
                itemYml.bayCost ?: 0.0,
                itemYml.sellCost ?: 0.0
            )} ?: emptyMap()
    }
}