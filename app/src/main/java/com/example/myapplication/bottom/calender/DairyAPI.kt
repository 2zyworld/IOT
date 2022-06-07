package com.example.myapplication.bottom.calender

import android.util.Log
import android.widget.Toast
import com.example.myapplication.bottom.calender.dairy_history.dairyhistory
import com.example.myapplication.drawer.askhelp.AskHelpService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface DiaryService {
    @GET("post/")
    fun getDiaryData(
    ): Call<dairyhistory>
}

object DairyData {
    private val retrofit = Retrofit.Builder()

//        .baseUrl("http://a4edbd6056b8a453eb7b4c88034f10e8-841047717.ap-southeast-1.elb.amazonaws.com:8000/")
        .baseUrl("http://3.0.128.249:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(DiaryService::class.java)

    fun getDiaryData(callback: (dairyhistory) -> Unit) {
        service.getDiaryData()
            .enqueue(object : Callback<dairyhistory> {

                override fun onResponse( call: Call<dairyhistory>, response: Response<dairyhistory>
                )
                { if (response.isSuccessful) {
                        val data = response.body()
                        callback(data!!) } }

                    override fun onFailure(call: Call<dairyhistory>, t: Throwable) {
                    Log.d("-----", "fail", t)
                }
            })
    }
}