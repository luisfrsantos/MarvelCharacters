package com.example.luisfernandorodrigues.apimarvel.ui.characters.activities

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.example.luisfernandorodrigues.apimarvel.R
import com.example.luisfernandorodrigues.apimarvel.model.CharacterResponse
import com.example.luisfernandorodrigues.apimarvel.model.Item
import com.example.luisfernandorodrigues.apimarvel.ui.characters.adapters.ComicsAdapter
import com.example.luisfernandorodrigues.apimarvel.ui.characters.viewModel.CharacterViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character.*

class CharacterActivity : AppCompatActivity() {

    var model: CharacterViewModel? = null
    var characterId: Int? = null
    private var items: ArrayList<Item>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        init()
        observer()
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        characterId = intent.getIntExtra(CHARACTERID, 0)
        if (characterId != 0) {
            model!!.getCharacters(applicationContext, characterId!!)
        }
        cardHeader.visibility = View.GONE
        tvMessage.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun init() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        model = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        recycler_view.adapter = ComicsAdapter(applicationContext, items!!)
    }

    fun observer() {
        model!!.characterListObserver.observe(this, Observer { character -> updateView(character!!) })
        model!!.errorObserver.observe(this, Observer { message -> error(message!!) })
    }

    private fun updateView(character: CharacterResponse) {
        Picasso.get()
                .load(character.thumbnail!!.path + "." + character.thumbnail.extension)
                .into(image)
        tvNameValue.setText(character.name)
        items!!.addAll(character.comics!!.items!!)
        recycler_view.adapter?.notifyDataSetChanged()
        cardHeader.visibility = View.VISIBLE
        progressBar.visibility = View.GONE

        if (character.comics.items!!.size > 0) {
            tvMessage.visibility = View.GONE
        } else {
            tvMessage.visibility = View.VISIBLE
        }
    }

    private fun error(message: String) {
        tvMessage.visibility = View.VISIBLE
        tvMessage.text = message
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        var CHARACTERID = "characterId"
    }
}
