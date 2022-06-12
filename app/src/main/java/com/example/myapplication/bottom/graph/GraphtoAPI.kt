package com.example.myapplication.bottom.graph

import android.util.Log
import com.example.myapplication.bottom.calender.DiaryService
import com.example.myapplication.bottom.calender.dairy_history.dairyhistory
import com.example.myapplication.bottom.graph.data.graphdata
import com.example.myapplication.bottom.graph.datato.datatoItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GraphtoService {
    @GET("graphMonth/")
    fun getGraphtoData(
    ): Call<datatoItem>
}

object GraphtoData {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://3.0.128.249:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(GraphtoService::class.java)

    fun getGraphData(callback: (datatoItem) -> Unit) {
        service.getGraphtoData()
            .enqueue(object : Callback<datatoItem> {

                override fun onResponse(
                    call: Call<datatoItem>,
                    response: Response<datatoItem>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        callback(data!!)
                    }
                }

                override fun onFailure(call: Call<datatoItem>, t: Throwable) {
                    Log.d("-----", "fail", t)
                }
            })
    }
}