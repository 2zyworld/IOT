package com.example.myapplication.drawer.meditation.data


import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("kind")
    val kind: String? = null,
    @SerializedName("videoId")
    val videoId: String? = null
)