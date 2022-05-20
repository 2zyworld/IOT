package com.example.myapplication.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService{

    @FormUrlEncoded
    @POST("/app_login/")
    fun requestLogin(
        @Field("username") username:String,
        @Field("password") password:String
    ) : retrofit2.Call<Login>

}