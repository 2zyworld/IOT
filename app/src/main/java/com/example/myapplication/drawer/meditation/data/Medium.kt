package com.example.myapplication.drawer.meditation.data


import com.google.gson.annotations.SerializedName

data class Medium(
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("width")
    val width: Int? = null
)