package com.example.luisfernandorodrigues.apimarvel.ui.characters.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.luisfernandorodrigues.apimarvel.R
import com.example.luisfernandorodrigues.apimarvel.model.CharacterResponse
import com.example.luisfernandorodrigues.apimarvel.ui.characters.adapters.CharactersAdapter
import com.example.luisfernandorodrigues.apimarvel.ui.characters.viewModel.CharacterListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class CharactersActivity : AppCompatActivity(), UpdateListInterface {

    lateinit var model: CharacterListViewModel
    lateinit var recyclerView: RecyclerView
    private lateinit var characterAdapter: CharactersAdapter
    private lateinit var charactersLsit: ArrayList<CharacterResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        observers()

        model.getCharacters(applicationContext)
        progressBar.visibility = View.VISIBLE
    }

    private fun init() {
        model = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)
        recyclerView = recycler_view
        recyclerView.layoutManager = LinearLayoutManager(this)
        charactersLsit = ArrayList()
        characterAdapter = CharactersAdapter(applicationContext, charactersLsit, this)
        recyclerView.adapter = characterAdapter
    }

    private fun observers() {
        model.characterListObserver.observe(this, Observer { characters -> updateList(characters!!) })
        model.errorObserver.observe(this, Observer {
             AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog)
                     .setTitle(getString(R.string.title_error))
                     .setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
                     .setMessage(it)
                     .show()
        })
    }

    private fun updateList(characters: List<CharacterResponse>) {
        charactersLsit.addAll(characters)
        characterAdapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
    }

    override fun onUpdate(exce: Boolean) {
        model.getCharacters(applicationContext)
        // progressBar.visibility = View.VISIBLE
    }
}
