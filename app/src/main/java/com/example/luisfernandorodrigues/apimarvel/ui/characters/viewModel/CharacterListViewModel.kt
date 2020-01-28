package com.example.luisfernandorodrigues.apimarvel.ui.characters.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import com.example.luisfernandorodrigues.apimarvel.R
import com.example.luisfernandorodrigues.apimarvel.helper.HashGenerate
import com.example.luisfernandorodrigues.apimarvel.model.CharacterResponse
import com.example.luisfernandorodrigues.apimarvel.repository.CharacterRepository
import com.example.luisfernandorodrigues.apimarvel.repository.ResponseInterface

class CharacterListViewModel : ViewModel(), ResponseInterface<List<CharacterResponse>> {

    val characterListObserver = MutableLiveData<List<CharacterResponse>>()
    val errorObserver = MutableLiveData<String>()
    private var characterRepository: CharacterRepository? = null

    init {
        characterRepository = CharacterRepository(this, null)
    }

    fun getCharacters(context: Context) {
        val hashGenerate = HashGenerate()
        val ts = hashGenerate.ts
        if (!ts.isBlank()) {
            val hash: String? = hashGenerate.getHash(ts, context.getString(R.string.privatekey), context.getString(R.string.apikey))
            characterRepository!!.getCharacters(context, ts, context.getString(R.string.apikey), hash!!)
        }
    }

    override fun success(response: List<CharacterResponse>) {
        characterListObserver.postValue(response)
    }

    override fun error(message: String) {
        errorObserver.postValue(message)
    }
}