package com.example.hw1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


    class ItemAdapter(private var diceList: List<DiceList>) : RecyclerView.Adapter<ItemAdapter.DiceRollViewHolder>() {
        // 定義內層Adapter
        class DiceRollViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            // 定義ViewHolder
            val diceImage: ImageView = itemView.findViewById(R.id.diceimage)
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiceRollViewHolder {
            // 創建ViewHolder
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            return DiceRollViewHolder(view)
        }

        override fun onBindViewHolder(holder: DiceRollViewHolder, position: Int) {
            // 綁定資料到ViewHolder
         val item = diceList[position]
        holder.diceImage.setImageResource(item.imageResourceId )
        }
        override fun getItemCount() = diceList.size

    }

