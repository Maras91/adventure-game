package com.app.adventure.game.model.yml.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "level")
data class LevelYml (var experienceRequiredPerLevel :List<Int?>?,var pointsPerLevel :Int? )