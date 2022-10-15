package com.app.adventure.game.model.yaml.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "items")
data class NotDisposableItemsYaml (var notDisposable: List<NotConsumableItemsYaml?>?) {
    data class NotConsumableItemsYaml(
        var name: String?,
        var bayCost: Double?,
        var sellCost: Double?,
        var timeInTurns: Int?,
        var attributes: Map<String, Int>?,
        var itemType: String?
    ) {
        constructor() : this("", 0.0, 0.0, 0, emptyMap(),"")
    }
}