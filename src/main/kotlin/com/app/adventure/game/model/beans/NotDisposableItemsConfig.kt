package com.app.adventure.game.model.beans

import com.app.adventure.game.model.exceptions.IncorrectYamlPropertiesException
import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.model.item.ItemType
import com.app.adventure.game.model.item.NotDisposableItem
import com.app.adventure.game.model.yaml.properties.NotDisposableItemsYaml
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NotDisposableItemsConfig @Autowired constructor(val notDisposableItemsYaml : NotDisposableItemsYaml){

    @Bean
    fun createNotDisposableItems(): Map<String, NotDisposableItem>{
        return notDisposableItemsYaml.notDisposable
            ?.filter { monster -> monster?.name != null }
            ?.associate { itemYml -> itemYml?.name!! to NotDisposableItem(
                ItemType.values().find { it.name == itemYml.itemType?.toUpperCase() }
                    ?: throw IncorrectYamlPropertiesException(itemYml.itemType ?:"",ItemType.values().map { it.name }.toString()),
                itemYml.attributes?.mapKeys {
                        ymlAt -> StatisticsName.values().find {
                    it.attributeName == ymlAt.key.toLowerCase()
                }?:  throw IncorrectYamlPropertiesException(
                    ymlAt.key, StatisticsName.values().map { it.attributeName }.toString()
                    )
                } ?: emptyMap(),
                itemYml.name ?: "",
                itemYml.bayCost ?: 0.0,
                itemYml.sellCost ?: 0.0
            )
            } ?: emptyMap()
    }
}