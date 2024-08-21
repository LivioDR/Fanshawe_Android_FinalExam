package com.example.project01.reinoso1165606.finalexampractical

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng

class Adapter(val data:ArrayList<PositionDataModel>):RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = data[position].title.toString()

        // handling the cell press event
        holder.name.setOnClickListener {
            val bundle = Bundle()
            // retrieving the data from the PositionDataModel
            bundle.putString("title",data[position].title.toString())
            bundle.putString("lat",data[position].latitude.toString())
            bundle.putString("lon",data[position].longitude.toString())

            val intent = Intent(it.context, PlaceActivity::class.java)
            intent.putExtras(bundle)
            it.context.startActivity(intent)
        }

    }

}