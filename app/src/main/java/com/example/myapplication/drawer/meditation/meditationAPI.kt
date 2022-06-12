package com.example.myapplication.drawer.meditation

import android.util.Log
import com.example.myapplication.bottom.calender.DiaryService
import com.example.myapplication.bottom.calender.dairy_history.dairyhistory
import com.example.myapplication.drawer.meditation.data.meditationdata
import com.example.myapplication.drawer.music.data.musicdata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MeditationService {
    @GET("relaxsong/")
    fun getMeditationData(
    ): Call<meditationdata>
}

object MeditationData {
    private val retrofit = Retrofit.Builder()


        .baseUrl("http://3.0.128.249:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(MeditationService::class.java)

    fun getMeditationData(callback: (meditationdata) -> Unit) {
        service.getMeditationData()
            .enqueue(object : Callback<meditationdata> {

                override fun onResponse(call: Call<meditationdata>, response: Response<meditationdata>
                )
                { if (response.isSuccessful) {
                    val data = response.body()
                    callback(data!!) } }

                override fun onFailure(call: Call<meditationdata>, t: Throwable) {
                    Log.d("-----", "fail", t)
                }
            })
    }
}