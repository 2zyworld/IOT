package com.example.myapplication.bottom.graph

import android.util.Log
import com.example.myapplication.bottom.calender.DiaryService
import com.example.myapplication.bottom.calender.dairy_history.dairyhistory
import com.example.myapplication.bottom.graph.data.graphdata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GraphService {
    @GET("graph/")
    fun getGraphData(
    ): Call<graphdata>
}

object GraphData {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://http://3.0.128.249:8002/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(GraphService::class.java)

    fun getGraphData(callback: (graphdata) -> Unit) {
        service.getGraphData()
            .enqueue(object : Callback<graphdata> {

                override fun onResponse(
                    call: Call<graphdata>,
                    response: Response<graphdata>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        callback(data!!)
                    }
                }

                override fun onFailure(call: Call<graphdata>, t: Throwable) {
                    Log.d("-----", "fail", t)
                }
            })
    }
}