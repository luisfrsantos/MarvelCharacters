package com.example.luisfernandorodrigues.apimarvel.model

import com.google.gson.annotations.SerializedName

data class Item(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("resourceURI")
    val resourceURI: String? = null
)