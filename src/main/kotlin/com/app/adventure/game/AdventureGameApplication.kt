package com.app.adventure.game

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebCalculatorApplication

fun main(args: Array<String>) {
	runApplication<WebCalculatorApplication>(*args) {
		setBannerMode(Banner.Mode.OFF)
	}
}
