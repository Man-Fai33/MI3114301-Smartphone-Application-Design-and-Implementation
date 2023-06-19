package com.example.hw2_a11009001

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class ResultFragment : AppCompatActivity(){
    private lateinit var bnt_nextgame:Button
    private lateinit var bnt_history:Button
    private lateinit var bnt_home:Button
    private lateinit var ShowGameScore:TextView
    private  var receivedDataList:List<Data> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resultfragment)
        bnt_nextgame=findViewById(R.id.btn_nextgame)
        bnt_history=findViewById(R.id.btn_history)
        bnt_home=findViewById(R.id.btn_home)
        ShowGameScore=findViewById(R.id.ShowGameScore)
        bnt_nextgame.setOnClickListener {nextGame()}
        bnt_history.setOnClickListener {history()}
        bnt_home.setOnClickListener {home()}
        var recyclerView=findViewById<RecyclerView>(R.id.Result_List)
         receivedDataList = intent.getSerializableExtra("data") as ArrayList<Data>

        ShowGameScore.setText("Score: "+receivedDataList.last().getScore())
        recyclerView.adapter=Resultlist_item(receivedDataList.last())
    }

    fun nextGame(){
        val intent = Intent(this, GuessFragment::class.java)
        startActivity(intent)
    }
    fun history(){

        val intent = Intent(this, HistoryFragment::class.java)
        intent.putExtra("data",ArrayList(receivedDataList))
        startActivity(intent)
    }
    fun home (){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("data",ArrayList(receivedDataList))
        startActivity(intent)
    }

}