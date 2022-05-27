package com.example.myapplication.drawer.askhelp.datas


import com.google.gson.annotations.SerializedName

data class Document(
    @SerializedName("address_name")
    val addressName: String? = null,
    @SerializedName("category_group_code")
    val categoryGroupCode: String? = null,
    @SerializedName("category_group_name")
    val categoryGroupName: String? = null,
    @SerializedName("category_name")
    val categoryName: String? = null,
    @SerializedName("distance")
    val distance: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("place_name")
    val placeName: String? = null,
    @SerializedName("place_url")
    val placeUrl: String? = null,
    @SerializedName("road_address_name")
    val roadAddressName: String? = null,
    @SerializedName("x")
    val x: String? = null,
    @SerializedName("y")
    val y: String? = null
)