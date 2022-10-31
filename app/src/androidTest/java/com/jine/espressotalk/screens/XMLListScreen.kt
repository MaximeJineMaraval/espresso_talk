package com.jine.espressotalk.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import com.jine.espressotalk.R
import com.jine.espressotalk.internal.Pokemon
import com.jine.espressotalk.ui.trainercreator.xml.PokemonAdapter

class XMLListScreen {

    private val pokemonList = withId(R.id.list)

    fun scrollTo(pokemon: Pokemon) {
        val pokemonItem = hasDescendant(withText(pokemon.name))
        onView(pokemonList).perform(scrollTo<PokemonAdapter.PokemonViewHolder>(pokemonItem))
    }

}