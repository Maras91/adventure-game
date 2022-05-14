package com.app.adventure.game.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TopMenuController {
    @GetMapping("/")
    fun adventure(): String {
        return "adventure"
    }
    @GetMapping("/town")
    fun town(): String {
        return "town"
    }
}