package com.example.hw2_a11009001

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Recordlist_item(private var records:  List<Record>) : RecyclerView.Adapter<Recordlist_item.RecordListViewHolder>() {
    inner  class RecordListViewHolder(view: View):RecyclerView.ViewHolder(view){
      var userans:TextView =view.findViewById(R.id.txt_userans)
     var trips:TextView =view.findViewById(R.id.txt_trips)


    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int,): RecordListViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.guess_records, parent, false)
        return RecordListViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecordListViewHolder, position: Int) {
        var record =records[position]
        holder.userans.setText(record.getInpute())
        holder.trips.setText(record.getResult())

    }

    override fun getItemCount() =records.size


}