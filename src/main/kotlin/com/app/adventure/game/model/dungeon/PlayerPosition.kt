package com.app.adventure.game.model.dungeon

class PlayerPosition(var axisX :Int, var axisY :Int) {
    fun goUp() {
        axisY--
    }

    fun goDown() {
        axisY++
    }

    fun goRight() {
        axisX++
    }

    fun goLeft() {
        axisX--
    }
}