package com.example.hw2_a11009001

import java.io.Serializable
import java.util.LinkedList

class Stage : Serializable {
    private var stage:String=""
    private var ans:String=""
    private var tries:String=""
    private  var record: List<Record> = listOf()

    constructor(stage: Int, ans: String, tries: Int, record: List<Record>) {
        this.stage = ""+stage
        this.ans = ans
        this.tries = ""+tries
        this.record = record
    }

    fun getStage():String{
        return stage
    } fun getAns():String{
        return ans
    } fun getTries():String{
        return tries
    } fun getRecord(): List<Record>{
        return record
    }

}