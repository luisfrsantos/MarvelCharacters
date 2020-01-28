package com.example.luisfernandorodrigues.apimarvel.model

import com.google.gson.annotations.SerializedName

data class Comics(

    @field:SerializedName("collectionURI")
    val collectionURI: String? = null,

    @field:SerializedName("available")
    val available: Int? = null,

    @field:SerializedName("returned")
    val returned: Int? = null,

    @field:SerializedName("items")
    val items: List<Item>? = null
)