package com.example.myapplication.drawer.meditation.data


import com.google.gson.annotations.SerializedName

data class data(
    @SerializedName("etag")
    val etag: String? = null,
    @SerializedName("items")
    val items: List<Item>? = null,
    @SerializedName("kind")
    val kind: String? = null,
    @SerializedName("nextPageToken")
    val nextPageToken: String? = null,
    @SerializedName("pageInfo")
    val pageInfo: PageInfo? = null,
    @SerializedName("regionCode")
    val regionCode: String? = null
)