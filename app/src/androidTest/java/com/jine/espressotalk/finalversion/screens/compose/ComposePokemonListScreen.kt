package com.jine.espressotalk.finalversion.screens.compose

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import com.jine.espressotalk.finalversion.internal.Pokemon
import com.jine.espressotalk.tests.TestTags

class ComposePokemonListScreen(private val composeRule: AndroidComposeTestRule<*, *>) {

    private val pokemonList = hasTestTag(TestTags.PokemonList)

    fun scrollTo(pokemon: Pokemon) {
        composeRule.onNode(pokemonList).performScrollToNode(hasText(pokemon.name))
    }

    fun clickTo(pokemon: Pokemon) {
        composeRule.onNode(hasText(pokemon.name)).performClick()
    }

}