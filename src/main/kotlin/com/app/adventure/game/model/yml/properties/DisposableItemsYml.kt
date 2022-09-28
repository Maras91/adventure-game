package com.app.adventure.game.model.yml.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "items")
data class DisposableItemsYml (var disposable: List<ConsumableItemsYaml?>?) {
    data class ConsumableItemsYaml (
        var name: String?,
        var bayCost: Double?,
        var sellCost: Double?,
        var hasPermanentEffect : Boolean?,
        var timeInTurns: Int?,
        var attributes: Map<String,Int>?) {
        constructor() : this("",0.0,0.0,false,0, emptyMap())
    }
}