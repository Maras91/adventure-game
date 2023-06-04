package com.app.adventure.game.model.dungeon.services

import com.app.adventure.game.model.dungeon.DungeonMapField
import com.app.adventure.game.model.dungeon.TunnelField
import com.app.adventure.game.model.dungeon.WallField
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class RandomMapGeneratorService {
    final val seed = Random.nextLong()
    val randomGenerator = Random(seed)
    lateinit var dungeonMap : Array<Array<DungeonMapField>>

    fun generateRandomMap(height: Int, width: Int): Array<Array<DungeonMapField>> {
        //TODO change to log function
        println("seed for rundom dungeon map : "+seed)
        dungeonMap = Array(height){Array(width){WallField()}}
        dungeonMap[0][0] = setRandomTunnel()
        drawOnes(0, 0)
        return dungeonMap
    }

    fun drawOnes( y: Int, x: Int) {
        if(!isPointOnTheBound(y,x)) {
            return;
        }
        val randomChoice = randomGenerator.nextInt(5);
        if (randomChoice == 0 || randomChoice == 1) {
            if (isPointOnTheBound(y,x+1) && !isBox2x2FillInOnes(y,x+1)) {
                dungeonMap[y][x+1] = setRandomTunnel();
                drawOnes(y,x+1);
            }
            if (isPointOnTheBound(y+1,x)&& !isBox2x2FillInOnes(y+1,x)) {
                dungeonMap[y+1][x] = setRandomTunnel();
                drawOnes(y+1,x);
            }
        }
        if (randomChoice == 2) {
            if (isPointOnTheBound(y,x+1) && !isBox2x2FillInOnes(y,x+1)) {
                dungeonMap[y][x+1] = setRandomTunnel();
                drawOnes(y,x+1);
            }
        }
        if (randomChoice == 3) {
            if (isPointOnTheBound(y+1,x)&& !isBox2x2FillInOnes(y+1,x)) {
                dungeonMap[y+1][x] = setRandomTunnel();
                drawOnes(y+1,x);
            }
        }
        if (randomChoice == 4){
            drawOnes(y,x);
            if (isPointOnTheBound(y,x-1)&& !isBox2x2FillInOnes(y,x-1) && dungeonMap[y][x-1] is WallField) {
                dungeonMap[y][x-1] = setRandomTunnel();
                drawOnes(y,x-1);
            }
        }
    }

    fun isPointOnTheBound(y: Int,x: Int): Boolean{
        if(dungeonMap.size <=y || y<0) {
            return false;
        } else {
            return dungeonMap[y].size>x && x>=0;
        }
    }

    fun isBox2x2FillInOnes(y: Int,x: Int) :Boolean{
        return isPointOnTheBound(y-1,x) && dungeonMap[y-1][x] !is WallField &&
                isPointOnTheBound(y,x-1) && dungeonMap[y][x-1] !is WallField &&
                isPointOnTheBound(y-1,x-1) && dungeonMap[y-1][x-1] !is WallField ||

                isPointOnTheBound(y-1,x) && dungeonMap[y-1][x] !is WallField &&
                isPointOnTheBound(y,x+1) && dungeonMap[y][x+1] !is WallField &&
                isPointOnTheBound(y-1,x+1) && dungeonMap[y-1][x+1] !is WallField ||

                isPointOnTheBound(y,x+1) && dungeonMap[y][x+1] !is WallField &&
                isPointOnTheBound(y+1,x+1) && dungeonMap[y+1][x+1] !is WallField &&
                isPointOnTheBound(y+1,x) && dungeonMap[y+1][x] !is WallField ||

                isPointOnTheBound(y+1,x) && dungeonMap[y+1][x] !is WallField &&
                isPointOnTheBound(y+1,x-1) && dungeonMap[y+1][x-1] !is WallField &&
                isPointOnTheBound(y,x-1) && dungeonMap[y][x-1] !is WallField;
    }

    fun setRandomTunnel() : TunnelField {
        return TunnelField("goblin")
    }
}