package com.example.myapplication.drawer.music

import android.system.Os.link
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.drawer.askhelp.AskHelpAdapter
import com.example.myapplication.drawer.askhelp.datas.Document
import com.example.myapplication.drawer.music.data.musicdata
import com.example.myapplication.drawer.music.data.musicdataItem


class MusicAdapter (val music: List<musicdataItem>?, val direction: (musicdataItem)->Unit)
    : RecyclerView.Adapter<MusicAdapter.ViewHolder>() {


    lateinit var musiclink: musicdataItem


        inner class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind (music: musicdataItem?) {

            val musicname: TextView = itemView.findViewById(R.id.musicname)
            musicname.text = music!!.title

                musiclink = music


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_music_list, parent,false)

        view.setOnClickListener {
            direction(musiclink)

        }



        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = music?.get(position)

        holder.bind(data!!)
    }
    override fun getItemCount(): Int = music!!.size


}