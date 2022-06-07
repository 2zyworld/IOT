package com.example.myapplication.bottom.calender

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.bottom.calender.dairy_history.dairyhistoryItem
import com.example.myapplication.drawer.askhelp.datas.Document


class DairyAdapter (val dairy: List<dairyhistoryItem>?,val direction: (dairyhistoryItem)->Unit)
    : RecyclerView.Adapter<DairyAdapter.ViewHolder>() {



    inner class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var dairyItem: dairyhistoryItem
        init {

            itemView.setOnClickListener {

                direction(dairyItem)
//            view.findNavController().navigate(R.id.navigation_calender)

            }
        }
        fun bind (dairy: dairyhistoryItem?) {

            val textView5: TextView = itemView.findViewById(R.id.textView5)
            textView5.text = dairy!!.title
            val historydates: TextView = itemView.findViewById(R.id.historydates)
            historydates.text = dairy!!.dtCreated



            dairyItem = dairy
        }
    }



    override fun onBindViewHolder(holder: DairyAdapter.ViewHolder, position: Int) {
        val data = dairy?.get(position)
        holder.bind(data)
//

    }

    override fun getItemCount(): Int = dairy!!.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DairyAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_dairy_listitem, parent,false)


        return ViewHolder(view)
    }
}


