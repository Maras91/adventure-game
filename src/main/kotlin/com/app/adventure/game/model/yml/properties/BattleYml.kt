package com.app.adventure.game.model.yml.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "battle")
data class BattleYml (var monsters: List<MonsterYml>?) {


    data class MonsterYml (var statistics: MonsterStatisticsValues?, var resources: MonsterResourcesValues?,
                           var name: String?, var experience: Int?){
        constructor() : this(null,null,"",0)
        data class MonsterResourcesValues (var gold : Double?, var iron: Double?, var meat: Double?) {
            constructor() : this(0.0,0.0,0.0)
        }
        data class MonsterStatisticsValues (var strength: Int?, var armor: Int?, var maxHP: Int?) {
            constructor() : this(0,0,0)
        }
    }
}