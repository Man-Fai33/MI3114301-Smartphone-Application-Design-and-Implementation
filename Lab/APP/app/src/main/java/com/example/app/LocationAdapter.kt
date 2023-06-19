package com.example.finaltest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.apptest.model.Info

class LocationAdapter (private var locationList: List<Info>) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val locationName: TextView = view.findViewById(R.id.locationName)
        val locationDescription: TextView = view.findViewById(R.id.locationDescription)
        val locationAddress: TextView = view.findViewById(R.id.locationAdd)
        val locationTime: TextView = view.findViewById(R.id.locationTime)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_location, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = locationList[position]
        holder.locationName.text = location.Name
        holder.locationDescription.text = location.Description
        holder.locationAddress.text = location.Add
        holder.locationTime.text = location.Opentime


    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    fun updateData(newData: List<Info>) {
        locationList = newData.toMutableList()
        notifyDataSetChanged()
    }



}