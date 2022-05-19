package com.example.myapplication.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
                val username: String,
                val password1: String,
                val password2: String,
                var email: String,
               )
