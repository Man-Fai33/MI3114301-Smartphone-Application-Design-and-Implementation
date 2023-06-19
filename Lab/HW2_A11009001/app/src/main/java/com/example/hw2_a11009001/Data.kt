package com.example.hw2_a11009001

import java.io.Serializable

class Data : Serializable {

    private  var game:String="";
    private  var score:String=""
    private  var stage:List<Stage> = listOf()
    constructor(game: Int, score: Int, stage: List<Stage>) {
        this.game = ""+game
        this.score = ""+score
        this.stage = stage
    }
    fun getStage():List<Stage>{ return stage }
    fun getScore():String{return score  }
    fun getGame():String{ return game }
    override fun toString(): String {  return "Data(game=$game, score=$score, stage=$stage)" }


}
