package com.jine.espressotalk.finalversion.screens.xml

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import com.jine.espressotalk.R
import com.jine.espressotalk.finalversion.internal.Pokemon
import com.jine.espressotalk.ui.trainercreator.xml.PokemonAdapter

class XMLPokemonListScreen {

    private val pokemonList = withId(R.id.list)

    fun scrollTo(pokemon: Pokemon) {
        val pokemonItem = hasDescendant(withText(pokemon.name))
        onView(pokemonList).perform(scrollTo<PokemonAdapter.PokemonViewHolder>(pokemonItem))
    }

    fun clickTo(pokemon: Pokemon) {
        val pokemonItem = withText(pokemon.name)
        onView(pokemonItem).perform(click())
    }

}