package com.app.adventure.game.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class DungeonController {


    @PostMapping("/getMap")
    @ResponseBody
    fun getDungeonMap() : Array<IntArray> {
        val lineOne = intArrayOf(1,1,1,0,1)
        val lineTwo = intArrayOf(2,0,1,1,1)
        val lineThree = intArrayOf(0,1,1,0,0)
        return arrayOf(lineOne,lineTwo,lineThree)
    }

}