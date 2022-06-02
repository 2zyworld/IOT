package com.example.myapplication.drawer.meditation.data


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("etag")
    val etag: String? = null,
    @SerializedName("id")
    val id: Id? = null,
    @SerializedName("kind")
    val kind: String? = null,
    @SerializedName("snippet")
    val snippet: Snippet? = null
)