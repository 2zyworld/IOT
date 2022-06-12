package com.example.myapplication.drawer.music

import android.util.Log
import com.example.myapplication.bottom.calender.DiaryService
import com.example.myapplication.bottom.calender.dairy_history.dairyhistory
import com.example.myapplication.drawer.music.data.musicdata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MusicService {
    @GET("recommendsong/")
    fun getMusicData(
    ): Call<musicdata>
}

object MusicData {
    private val retrofit = Retrofit.Builder()


        .baseUrl("http://3.0.128.249:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(MusicService::class.java)

    fun getMusicData(callback: (musicdata) -> Unit) {
        service.getMusicData()
            .enqueue(object : Callback<musicdata> {

                override fun onResponse(call: Call<musicdata>, response: Response<musicdata>
                )
                { if (response.isSuccessful) {
                    val data = response.body()
                    callback(data!!) } }

                override fun onFailure(call: Call<musicdata>, t: Throwable) {
                    Log.d("-----", "fail", t)
                }
            })
    }
}