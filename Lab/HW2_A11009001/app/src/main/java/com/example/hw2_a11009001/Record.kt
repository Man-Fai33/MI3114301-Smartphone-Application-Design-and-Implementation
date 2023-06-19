package com.example.hw2_a11009001

import java.io.Serializable

class Record : Serializable {
    private var record:String="";
    private var input:String="";
    private var result:String="";

    constructor(record: String, input: String, result: String) {
        this.record = record
        this.input = input
        this.result = result
    }

    fun getInpute():String{
        return input
    }
    fun getResult():String{
        return result
    }
    fun getRecord():String{
        return record
    }
}