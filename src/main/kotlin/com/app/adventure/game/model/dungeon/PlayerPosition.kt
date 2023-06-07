package com.app.adventure.game.model.dungeon

class PlayerPosition(var axisX :Int, var axisY :Int) {

    fun restartPosition() {
        axisX = 0
        axisY = 0
    }
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