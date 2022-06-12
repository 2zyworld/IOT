package com.example.myapplication.bottom.graph

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.bottom.calender.DairyAdapter
import com.example.myapplication.bottom.calender.dairy_history.dairyhistoryItem
import com.example.myapplication.bottom.graph.data.graphdataItem

class GraphtoAdapter (val graph: List<graphdataItem>?)
    : RecyclerView.Adapter<GraphtoAdapter.ViewHolder>() {

    inner class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind (graph: graphdataItem?) {

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GraphtoAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_graph, parent,false)

        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = graph?.get(position)

        holder.bind(data)
    }
    override fun getItemCount(): Int = graph!!.size


}