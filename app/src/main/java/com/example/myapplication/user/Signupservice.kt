package com.example.myapplication.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Signupservice{


    @POST("rest-auth/registration/")
    fun register(
        @Body user: User
    ) : retrofit2.Call<Login>

}