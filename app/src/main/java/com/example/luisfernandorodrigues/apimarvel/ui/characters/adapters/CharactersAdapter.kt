package com.example.luisfernandorodrigues.apimarvel.ui.characters.adapters

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.luisfernandorodrigues.apimarvel.R
import com.example.luisfernandorodrigues.apimarvel.model.CharacterResponse
import com.example.luisfernandorodrigues.apimarvel.ui.characters.activities.CharacterActivity
import com.example.luisfernandorodrigues.apimarvel.ui.characters.activities.UpdateListInterface
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_character.view.*

const val CHARACTER_ID = "characterId"

class CharactersAdapter(
    private val context: Context,
    private val characterList: List<CharacterResponse>,
    private val response: UpdateListInterface
) : RecyclerView.Adapter<CharactersAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_character, parent, false))
    }

    override fun getItemCount(): Int = if (characterList.isNotEmpty()) characterList.size else 0

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val characterResponse: CharacterResponse? = characterList[position]

        holder.tvName.text = characterResponse!!.name
        Picasso.get()
                .load(characterResponse.thumbnail!!.path + "." + characterResponse.thumbnail.extension)
                .error(R.drawable.default_image)
                .into(holder.image)

        if (position == characterList.size - 1) {
            holder.progressBar.visibility = View.VISIBLE
            response.onUpdate(true)
        } else {
            holder.progressBar.visibility = View.GONE
        }

        holder.cardView.setOnClickListener {
            val intent = Intent(context, CharacterActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra(CHARACTER_ID, characterResponse.id)
            context.startActivity(intent)
        }
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.tv_name_value
        var image: ImageView = view.image
        var progressBar: ProgressBar = view.progressBar
        var cardView: CardView = view.cardHeader
    }
}