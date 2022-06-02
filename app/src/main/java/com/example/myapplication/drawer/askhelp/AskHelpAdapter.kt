package com.example.myapplication.drawer.askhelp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.drawer.askhelp.datas.Document


class AskHelpAdapter (val documents: List<Document>?, val direction: (Document)->Unit)
    : RecyclerView.Adapter<AskHelpAdapter.ViewHolder>() {

    private val documentList:List<Document> = listOf()
//    # var latitude: String? = null
//    var longitude: String? = null
    lateinit var document: Document

//    private fun bindViews (data: Document){
//        binding.setOnClickListener{
//            direction(data)
//        }
//    }

    inner class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(documents: Document?,) {
            val hospitalitem: TextView = itemView.findViewById(R.id.hospitalitem)
            hospitalitem.text = documents!!.placeName

            val hopitalNum: TextView = itemView.findViewById(R.id.hopitalNum)
            hopitalNum.text = documents!!.phone

            val hospitalAdr: TextView = itemView.findViewById(R.id.hospitalAdr)
            hospitalAdr.text = documents!!.roadAddressName

            val hospitalAdrr: TextView = itemView.findViewById(R.id.hospitalAdrr)
            hospitalAdrr.text = documents!!.placeUrl

            // latilatitude = documents!!.x
            // longitude = documents!!.x
            // longitude = documents!!.y
            document = documents


        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_askhelp_listitem, parent,false)

        view.setOnClickListener {
            direction(document)



        }
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = documents?.get(position)
        holder.bind(data)
    }
    override fun getItemCount(): Int = documents!!.size }

