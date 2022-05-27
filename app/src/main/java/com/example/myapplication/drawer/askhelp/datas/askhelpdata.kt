package com.example.myapplication.drawer.askhelp.datas


import com.google.gson.annotations.SerializedName

data class askhelpdata(
    @SerializedName("documents")
    val documents: List<Document>? = null,
    @SerializedName("meta")
    val meta: Meta? = null
)