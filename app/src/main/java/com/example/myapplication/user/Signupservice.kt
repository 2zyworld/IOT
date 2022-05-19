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

//    @FormUrlEncoded         // email=xxx&password=xxxx...
//    @POST("signup/")
//    fun register(
//        @Field("email") Email:String,
//        @Field("password") password:String,
//        @Field("name") name:String,
//        @Field("phonenum") phonenum:String,
//        @Field("address") address:String,
//    ) : retrofit2.Call<Login>

    @POST("rest-auth/registration/")
    fun register(
        @Body user: User
    ) : retrofit2.Call<Login>

}