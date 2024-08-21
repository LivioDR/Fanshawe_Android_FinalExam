package com.example.project01.reinoso1165606.finalexampractical

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val name = view.findViewById<TextView>(R.id.cellTitle)
}