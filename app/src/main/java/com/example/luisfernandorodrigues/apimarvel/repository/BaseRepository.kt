package com.example.luisfernandorodrigues.apimarvel.repository

import com.example.luisfernandorodrigues.apimarvel.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseRepository<T, J> {
    var retrofit: Retrofit? = null
    var myClass: Class<T>? = null

    fun init(myClass: Class<T>) {
        this.myClass = myClass
        retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getService(): T {
        return retrofit!!.create(myClass!!)
    }
}