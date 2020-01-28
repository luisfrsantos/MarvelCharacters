package com.example.luisfernandorodrigues.apimarvel.repository

import android.content.Context
import com.example.luisfernandorodrigues.apimarvel.R
import com.example.luisfernandorodrigues.apimarvel.model.CharacterResponse
import com.example.luisfernandorodrigues.apimarvel.model.Response
import com.example.luisfernandorodrigues.apimarvel.services.CharacterService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback

private const val interval: Int = 20

class CharacterRepository(val responseList: ResponseInterface<List<CharacterResponse>>? = null, val response: ResponseInterface<CharacterResponse>? = null) : BaseRepository<CharacterService, JsonObject>() {
    var offset: Int? = 0

    init {
        init(CharacterService::class.java)
    }

    fun getCharacters(context: Context, ts: String, apikey: String, hash: String) {
        offset = offset!! + interval
        getService().getCharacters(ts, apikey, hash, offset!!).enqueue(object : Callback<Response<List<CharacterResponse>>> {
            override fun onResponse(call: Call<Response<List<CharacterResponse>>>?, response: retrofit2.Response<Response<List<CharacterResponse>>>?) {
                if (response!!.body() == null ||
                        response.body()!!.data.total.isNullOrBlank() ||
                        offset == response.body()!!.data.total!!.toInt()) {
                            responseList?.error(context.getString(R.string.message_characters))
                } else {
                    responseList?.success(response.body()!!.data.results)
                }
            }

            override fun onFailure(call: Call<Response<List<CharacterResponse>>>?, t: Throwable) {
                responseList?.error(t.message!!)
            }
        })
    }

    fun getCharacter(context: Context, charactersId: Int, ts: String, apikey: String, hash: String) {
        getService().getCharacter(charactersId, ts, apikey, hash).enqueue(object : Callback<Response<List<CharacterResponse>>> {
            override fun onResponse(call: Call<Response<List<CharacterResponse>>>?, response: retrofit2.Response<Response<List<CharacterResponse>>>?) {
                if (response!!.body() == null || response.body()!!.data.total.isNullOrBlank() || offset == response.body()!!.data.total!!.toInt()) {
                    responseList?.error(context.getString(R.string.message_character_error))
                } else {
                    responseList?.success(response.body()!!.data.results)
                }
            }

            override fun onFailure(call: Call<Response<List<CharacterResponse>>>?, t: Throwable) {
                response!!.error(t.message!!)
            }
        })
    }
}