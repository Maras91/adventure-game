package com.app.adventure.game.model.beans

import com.app.adventure.game.model.exceptions.IncorrectYamlPropertiesException
import com.app.adventure.game.model.fight.statistics.StatisticsName
import com.app.adventure.game.model.item.*
import com.app.adventure.game.model.yaml.properties.DisposableItemsYaml
import com.app.adventure.game.model.yaml.properties.NotDisposableItemsYaml
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AllItemsConfig @Autowired constructor(val disposableItemsYaml: DisposableItemsYaml, val notDisposableItemsYaml: NotDisposableItemsYaml) {

    @Bean
    fun getAllItems(): Inventory {
        return Inventory(yamlDisposableItemsToMap().plus(yamlNotDisposableItemsToMap()).toMutableMap())
    }


    fun yamlDisposableItemsToMap(): Map<String, DisposableItem>{
        return disposableItemsYaml.disposable
            ?.filter { item -> item?.name != null }
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
            )
            } ?: emptyMap()
    }

    fun yamlNotDisposableItemsToMap(): Map<String, NotDisposableItem>{
        return notDisposableItemsYaml.notDisposable
            ?.filter { monster -> monster?.name != null }
            ?.associate { itemYml -> itemYml?.name!! to NotDisposableItem(
                ItemType.values().find { it.name == itemYml.itemType?.toUpperCase() }
                    ?: throw IncorrectYamlPropertiesException(itemYml.itemType ?:"", ItemType.values().map { it.name }.toString()),
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