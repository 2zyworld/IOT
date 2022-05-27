package com.example.myapplication.drawer.askhelp


import android.util.Log
import com.example.myapplication.drawer.askhelp.datas.askhelpdata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query



interface AskHelpService {
    @Headers("Authorization: KakaoAK b7fe6bde7366a4b3b39483ee5cc491be")
    @GET("/v2/local/search/keyword.json?y=37.514322572335935&x=127.06283102249932&radius=1000")
    fun getAskHelpData(
        @Query("query") keyword: String

    ):Call<askhelpdata>

}

object AskHelpData {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(AskHelpService::class.java)

    fun getAskHelpData(keyword: String, callback: (askhelpdata) -> Unit) {
        service.getAskHelpData(keyword)
            .enqueue(object : Callback<askhelpdata> {

                override fun onResponse(
                    call: Call<askhelpdata>,
                    response: Response<askhelpdata>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        callback(data!!)
                    }
                }

                override fun onFailure(call: Call<askhelpdata>, t: Throwable) {
                    Log.d("-----", "fail", t)
                }
            })
    }
}


