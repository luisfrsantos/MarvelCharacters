package com.example.luisfernandorodrigues.apimarvel.ui.characters.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import com.example.luisfernandorodrigues.apimarvel.R
import com.example.luisfernandorodrigues.apimarvel.helper.HashGenerate
import com.example.luisfernandorodrigues.apimarvel.model.CharacterResponse
import com.example.luisfernandorodrigues.apimarvel.repository.CharacterRepository
import com.example.luisfernandorodrigues.apimarvel.repository.ResponseInterface

class CharacterViewModel : ViewModel(), ResponseInterface<List<CharacterResponse>> {

    val characterListObserver = MutableLiveData<CharacterResponse>()
    val errorObserver = MutableLiveData<String>()
    private var characterRepository: CharacterRepository? = null

    init {
        characterRepository = CharacterRepository(this, null)
    }

    fun getCharacters(context: Context, charactersId: Int) {
        val hashGenerate: HashGenerate? = HashGenerate()
        val ts: String? = hashGenerate!!.ts
        if (!ts.isNullOrBlank()) {
            val hash = hashGenerate.getHash(ts, context.getString(R.string.privatekey), context.getString(R.string.apikey))
            characterRepository?.getCharacter(context, charactersId, ts, context.getString(R.string.apikey), hash)
        }
    }

    override fun success(response: List<CharacterResponse>) {
        characterListObserver.postValue(response[0])
    }

    override fun error(message: String) {
        errorObserver.postValue(message)
    }
}