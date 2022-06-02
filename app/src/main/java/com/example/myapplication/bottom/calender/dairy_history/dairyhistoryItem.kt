package com.example.myapplication.bottom.calender.dairy_history


import com.google.gson.annotations.SerializedName

data class dairyhistoryItem(
    @SerializedName("angry")
    val angry: Any? = null,
    @SerializedName("anticipation")
    val anticipation: Any? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("color")
    val color: Any? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("disgust")
    val disgust: Any? = null,
    @SerializedName("dt_created")
    val dtCreated: String? = null,
    @SerializedName("dt_modified")
    val dtModified: String? = null,
    @SerializedName("fear")
    val fear: Any? = null,
    @SerializedName("joy")
    val joy: Any? = null,
    @SerializedName("sadness")
    val sadness: Any? = null,
    @SerializedName("surprise")
    val surprise: Any? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("trust")
    val trust: Any? = null
)