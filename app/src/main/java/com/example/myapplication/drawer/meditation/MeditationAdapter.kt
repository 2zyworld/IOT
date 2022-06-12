package com.example.myapplication.drawer.meditation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.drawer.meditation.data.meditationdataItem
import com.example.myapplication.drawer.music.data.musicdataItem


class MeditationAdapter (val meditation: List<meditationdataItem>?, val direction: (meditationdataItem)->Unit)
    : RecyclerView.Adapter<MeditationAdapter.ViewHolder>() {


    lateinit var meditationlink: meditationdataItem


        inner class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind (meditation: meditationdataItem?) {

            val meditationtitle: TextView = itemView.findViewById(R.id.meditationtitle)
            meditationtitle.text = meditation!!.title


                meditationlink = meditation


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_meditation_item, parent,false)

        view.setOnClickListener {
            direction(meditationlink)

        }



        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = meditation?.get(position)

        holder.bind(data!!)
    }
    override fun getItemCount(): Int = meditation!!.size


}