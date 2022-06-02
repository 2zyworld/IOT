package com.example.myapplication.bottom.calender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.bottom.calender.dairy_history.dairyhistoryItem
import com.example.myapplication.drawer.askhelp.datas.Document


class DairyAdapter (val dairy: List<dairyhistoryItem>?,val direction: (dairyhistoryItem)->Unit)
    : RecyclerView.Adapter<DairyAdapter.ViewHolder>() {

    lateinit var dairyItem: dairyhistoryItem
    var content_main = ""

    inner class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind (dairy: dairyhistoryItem?) {

            val textView5: TextView = itemView.findViewById(R.id.textView5)
            textView5.text = dairy!!.title
            content_main = dairy!!.content.toString()

        dairyItem = dairyhistoryItem()
        }

    }
    fun setDataAtFragment(fragment: Fragment, content:String) {
        val bundle = Bundle()
        bundle.putString("cotent", content_main)

        fragment.arguments= bundle

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
//            view.findNavController().navigate(R.id.navigation_calender)

        }
        return ViewHolder(view)
    }

}


