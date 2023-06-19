package com.example.hw2_a11009001

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
private var dataList:List<Data> = listOf()
class HistoryFragment : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.historyfragment)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var recyclerView=findViewById<RecyclerView>(R.id.historyView)
        if ( intent.getSerializableExtra("data")!=null){
            dataList =intent.getSerializableExtra("data") as List<Data>
        }
        recyclerView.adapter =DataList(dataList)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
class DataList ( var data: List<Data> ): RecyclerView.Adapter<DataList.DataListHolder>() {
    inner  class DataListHolder(view: View): RecyclerView.ViewHolder(view){
        var ShowGame:TextView =view.findViewById(R.id.txthistory_game)
        var ShowScore:TextView=view.findViewById(R.id.txthistory_score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int,): DataList.DataListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.historyviewrecycler, parent, false)
        return DataListHolder(view)
    }

    override fun onBindViewHolder(holder: DataListHolder, position: Int) {
       var info= data[position]
        holder.ShowGame.setText("game: "+info.getGame())
        holder.ShowScore.setText("Score:  "+info.getScore())
    }



    override fun getItemCount()=data.size
}