package com.example.myapplication.drawer.music.data


import com.google.gson.annotations.SerializedName

data class musicdataItem(
    @SerializedName("link")
    val link: String? = null,
    @SerializedName("title")
    val title: String? = null
)