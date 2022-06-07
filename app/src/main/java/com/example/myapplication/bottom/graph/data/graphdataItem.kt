package com.example.myapplication.bottom.graph.data


import com.google.gson.annotations.SerializedName

data class graphdataItem(
    @SerializedName("angry")
    val angry: Double? = null,
    @SerializedName("anticipation")
    val anticipation: Double? = null,
    @SerializedName("disgust")
    val disgust: Double? = null,
    @SerializedName("fear")
    val fear: Double? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("joy")
    val joy: Double? = null,
    @SerializedName("sadness")
    val sadness: Double? = null,
    @SerializedName("surprise")
    val surprise: Double? = null,
    @SerializedName("trust")
    val trust: Double? = null
)