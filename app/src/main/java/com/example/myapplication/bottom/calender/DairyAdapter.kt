package com.example.myapplication.bottom.calender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.bottom.calender.dairy_history.dairyhistoryItem
import com.example.myapplication.drawer.askhelp.AskHelpFragment
import com.example.myapplication.drawer.askhelp.datas.Document
import java.util.logging.Logger.global


class DairyAdapter (val dairy: List<dairyhistoryItem>?,val direction: (dairyhistoryItem)->Unit)
    : RecyclerView.Adapter<DairyAdapter.ViewHolder>() {

    lateinit var dairyItem: dairyhistoryItem


    inner class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind (dairy: dairyhistoryItem?) {

            val textView5: TextView = itemView.findViewById(R.id.textView5)
            textView5.text = dairy!!.title



        dairyItem = dairyhistoryItem()
        }


    }






    override fun onBindViewHolder(holder: DairyAdapter.ViewHolder, position: Int) {
        val data = dairy?.get(position)

            holder.bind(data)

    }

    override fun getItemCount(): Int = dairy!!.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DairyAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_dairy_listitem, parent,false)

        view.setOnClickListener {

        direction(dairyItem)


        }
        return ViewHolder(view)
    }


}


