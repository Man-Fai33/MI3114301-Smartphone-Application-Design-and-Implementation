package com.example.hw2_a11009001

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Resultlist_item ( var data: Data ): RecyclerView.Adapter<Resultlist_item.resultHolder>() {
    inner  class resultHolder(view: View):RecyclerView.ViewHolder(view){
        var showStage:TextView =view.findViewById(R.id.showStage)
        var showAns:TextView=view.findViewById(R.id.showAns)
        var showTries:TextView=view.findViewById(R.id.showTries)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int,): Resultlist_item.resultHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.resultrecycleview, parent, false)
        return resultHolder(view)
    }

    override fun onBindViewHolder(holder: Resultlist_item.resultHolder, position: Int) {
       var stages =data.getStage()
        var stage =stages[position]


        holder.showStage.setText("Stage: "+stage.getStage())
        holder.showAns.setText("Ans:"+stage.getAns())
        holder.showTries.setText("Tries: "+stage.getTries())

    }

    override fun getItemCount()=data.getStage().size
}