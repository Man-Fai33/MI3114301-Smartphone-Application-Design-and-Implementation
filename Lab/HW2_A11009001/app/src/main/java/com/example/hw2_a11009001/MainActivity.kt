package com.example.hw2_a11009001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
private  var receivedDataList:List<Data> = listOf()
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.startfragment)
        val btn_Start:Button =findViewById(R.id.btn_start)
        val btn_History:Button=findViewById(R.id.btn_history)
        if( intent.getSerializableExtra("data") !=null ) {
            receivedDataList = intent.getSerializableExtra("data") as ArrayList<Data>
        }
        btn_Start.setOnClickListener {
            val intent = Intent(this, GuessFragment::class.java)

            intent.putExtra("data",ArrayList(receivedDataList))
            startActivity(intent)
        }
        btn_History.setOnClickListener {
            val intent = Intent(this, HistoryFragment::class.java)

            intent.putExtra("data",ArrayList(receivedDataList))
            startActivity(intent)
        }
    }
}