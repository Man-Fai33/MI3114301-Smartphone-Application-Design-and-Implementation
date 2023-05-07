package com.example.hw1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView




class ListAdapter(
    private  val context:Context,
    private val diceResults:  MutableList<Dices>) : RecyclerView.Adapter<ListAdapter.DiceResultViewHolder>() {
    // 定義Adapter

    inner class DiceResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // 定義ViewHolder

        val sum:TextView =view.findViewById(R.id.Counter)

        val innerRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewItemList)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiceResultViewHolder {
        // 創建ViewHolder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)


        return DiceResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiceResultViewHolder, position: Int) {
        var item = diceResults[position]


        holder.sum.text = item.sum.toString()
        holder.innerRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)

        holder.innerRecyclerView.adapter =ItemAdapter(item.list)
    }

    override fun getItemCount() = diceResults.size

}
