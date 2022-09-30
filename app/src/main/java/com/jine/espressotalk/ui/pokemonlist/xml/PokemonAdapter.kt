package com.jine.espressotalk.ui.pokemonlist.xml

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jine.espressotalk.R
import com.jine.espressotalk.data.model.PokemonModel
import com.jine.espressotalk.databinding.ItemPokemonBinding

class PokemonAdapter(
    val onPokemonClicked: (pokemonNumber: Int) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    var pokemons: List<PokemonModel> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        pokemons.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    inner class PokemonViewHolder(
        private val binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(pokemon: PokemonModel) {
            binding.name.text = pokemon.name
            binding.details.text = "${pokemon.type}\n${pokemon.heightAndWeight}"
            binding.image.load(pokemon.imageUrl) {
                crossfade(true)
            }
            binding.favorite.apply {
                setOnClickListener { onPokemonClicked(pokemon.number) }
                when (pokemon.isFavorite()) {
                    true -> {
                        setImageResource(R.drawable.ic_favorite_filled)
                        setColorFilter(ContextCompat.getColor(context, R.color.pokemon_red_dark))
                    }
                    false -> {
                        setImageResource(R.drawable.ic_favorite_outlined)
                        setColorFilter(ContextCompat.getColor(context, android.R.color.black))
                    }
                }
            }

            binding.favorite.setImageResource(
                if (pokemon.isFavorite()) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_outlined
            )
        }
    }
}