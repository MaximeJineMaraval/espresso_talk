package com.jine.espressotalk.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import com.jine.espressotalk.R
import com.jine.espressotalk.internal.Pokemon
import com.jine.espressotalk.internal.clickOnFavoriteItemIcon
import com.jine.espressotalk.internal.typeSearchViewText
import com.jine.espressotalk.ui.pokemonlist.xml.PokemonAdapter

class XMLListScreen {

    private val pokemonList = withId(R.id.list)

    fun scrollTo(pokemon: Pokemon) {
        val pokemonItem = hasDescendant(withText(pokemon.name))
        onView(pokemonList).perform(scrollTo<PokemonAdapter.PokemonViewHolder>(pokemonItem))
    }

    fun enterSearchText(text: String) {
        onView(withId(R.id.search)).perform(typeSearchViewText(text))
    }

    fun markAsFavorite(pokemon: Pokemon) {
        scrollTo(pokemon)
        onView(pokemonList).perform(
            RecyclerViewActions.actionOnItem<PokemonAdapter.PokemonViewHolder>(
                hasDescendant(withText(pokemon.name)),
                clickOnFavoriteItemIcon()
            )
        )
    }

    fun showOnlyFavorite() {
        onView(withId(R.id.topBarFavorite))
            .perform(click())
    }

}