package com.app.adventure.game.model.yml

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "player.statistics")
data class PlayerStartStatisticsValues (var strength: Int?, var armor: Int?, var maxHP: Int?)