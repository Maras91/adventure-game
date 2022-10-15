package com.app.adventure.game.model.yaml.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "player.resources")
data class PlayerResourcesValuesYaml (var gold : Double?, var iron: Double?, var meat: Double?){
}