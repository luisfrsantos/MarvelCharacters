package com.example.luisfernandorodrigues.apimarvel.model

import com.google.gson.annotations.SerializedName

data class UrlsItem(

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)