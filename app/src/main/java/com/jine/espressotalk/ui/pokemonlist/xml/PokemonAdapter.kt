package com.jine.espressotalk.ui.pokemonlist.xml

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jine.espressotalk.data.model.PokemonModel
import com.jine.espressotalk.databinding.ItemPokemonBinding

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

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
        }
    }
}