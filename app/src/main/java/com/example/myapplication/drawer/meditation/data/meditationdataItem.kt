package com.example.myapplication.drawer.meditation.data


import com.google.gson.annotations.SerializedName

data class meditationdataItem(
    @SerializedName("link")
    val link: String? = null,
    @SerializedName("title")
    val title: String? = null
)