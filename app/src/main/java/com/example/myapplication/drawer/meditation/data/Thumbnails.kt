package com.example.myapplication.drawer.meditation.data


import com.google.gson.annotations.SerializedName

data class Thumbnails(
    @SerializedName("default")
    val default: Default? = null,
    @SerializedName("high")
    val high: High? = null,
    @SerializedName("medium")
    val medium: Medium? = null
)